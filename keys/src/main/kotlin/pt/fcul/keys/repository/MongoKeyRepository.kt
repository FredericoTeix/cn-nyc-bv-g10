package pt.fcul.keys.repository

import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.inc
import org.litote.kmongo.set
import org.litote.kmongo.setTo
import org.litote.kmongo.setValueOnInsert
import org.litote.kmongo.upsert
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import pt.fcul.keys.common.MongoTransactionHelper
import pt.fcul.keys.exceptions.DuplicateKeyException
import pt.fcul.keys.exceptions.KeyDoesntExistException
import pt.fcul.keys.model.KeyInfo

@Primary
@Repository
class MongoKeyRepository(
    val mth: MongoTransactionHelper
) : KeyRepository {

    override fun createKey(info: KeyInfo): KeyInfo = mth.withSession { session, db ->
        val collection = db.getCollection<KeyInfo>("keys")

        val upsertedId = collection.updateOne(
            session,
            KeyInfo::key eq info.key,
            setValueOnInsert(info),
            upsert()
        ).upsertedId ?: throw DuplicateKeyException(info.key)
        // if id is null, upsert resulted in an update, which means it already exists

        collection.findOneById(session, upsertedId)!!
    }

    override fun readKey(key: String) = mth.withSession { session, db ->
        val collection = db.getCollection<KeyInfo>("keys")
        collection.findOne(
            session,
            KeyInfo::key eq key
        ) ?: throw KeyDoesntExistException(key)
    }

    override fun updateKey(info: KeyInfo): KeyInfo = mth.withSession { session, db ->
        val collection = db.getCollection<KeyInfo>("keys")

        val updateResult = collection.updateOne(
            session,
            KeyInfo::key eq info.key,
            set(
                KeyInfo::quota setTo info.quota,
                KeyInfo::used setTo info.used,
                KeyInfo::contact setTo info.contact,
                KeyInfo::scope setTo info.scope,
            )
        )

        if (updateResult.matchedCount <= 0) throw KeyDoesntExistException(info.key)
        readKey(info.key)
    }

    override fun deleteKey(key: String) = mth.runTransaction { session, db ->
        val collection = db.getCollection<KeyInfo>("keys")

        val deleteResult = collection.deleteOne(
            session,
            KeyInfo::key eq key
        )

        if (deleteResult.deletedCount <= 0) throw KeyDoesntExistException(key)
    }

    override fun refreshKey(key: String, newKey: String): KeyInfo = mth.withSession { session, db ->
        val collection = db.getCollection<KeyInfo>("keys")

        val updateResult = collection.updateOne(
            session,
            KeyInfo::key eq key,
            set(
                KeyInfo::key setTo newKey,
            )
        )

        if (updateResult.matchedCount <= 0) throw KeyDoesntExistException(key)
        readKey(newKey)
    }

    override fun consumeKey(key: String) = mth.withSession { session, db ->
        val collection = db.getCollection<KeyInfo>("keys")

        val updateResult = collection.updateOne(
            session,
            KeyInfo::key eq key,
            inc(KeyInfo::used, 1)
        )

        if (updateResult.matchedCount <= 0) throw KeyDoesntExistException(key)
    }
}