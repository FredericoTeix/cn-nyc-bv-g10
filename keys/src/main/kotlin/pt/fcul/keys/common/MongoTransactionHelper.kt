package pt.fcul.keys.common

import com.mongodb.ClientSessionOptions
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import com.mongodb.client.ClientSession
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pt.fcul.keys.exceptions.EnvVarNotFoundException

private const val MONGO_DB_VAR = "MONGO_DATABASE_NAME"

@Component
class MongoTransactionHelper(
    mongoClient: MongoClient
) {
    private val log = LoggerFactory.getLogger(MongoTransactionHelper::class.java)

    private val mongo: MongoClient = mongoClient
    private final val db: MongoDatabase

    private val sessionCache = ThreadLocal<ClientSession>()
    // TODO adapt for coroutines

    init {
        val mongoDbName = System.getenv(MONGO_DB_VAR) ?: throw EnvVarNotFoundException(MONGO_DB_VAR)
        log.info("Found environment variable $MONGO_DB_VAR = $mongoDbName")

        db = mongoClient.getDatabase(mongoDbName)
            .withReadConcern(ReadConcern.MAJORITY)
            .withWriteConcern(WriteConcern.MAJORITY)
    }

    fun <T : Any> runTransaction(
        handler: (ClientSession, MongoDatabase) -> T
    ): T = withSession { session, db ->
        if (session.hasActiveTransaction()) {
            handler(session, db)
        } else {
            session.withTransaction {
                handler(session, db)
            }
        }
    }

    fun <T : Any> withSession(
        handler: (ClientSession, MongoDatabase) -> T
    ): T {
        val session = sessionCache.get()
        if (session != null) {
            return handler(session, db)
        }

        val options = ClientSessionOptions.builder().causallyConsistent(true).build()
        val clientSession = mongo.startSession(options)

        sessionCache.set(clientSession)
        try {
            return clientSession.use {
                handler(it, db)
            }
        } finally {
            sessionCache.remove()
        }
    }
}

fun <T : Any> ClientSession.useTransaction(handler: () -> T) =
    if (hasActiveTransaction()) handler()
    else withTransaction { handler() }