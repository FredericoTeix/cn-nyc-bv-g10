package pt.fcul.keys.repository

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.set
import org.litote.kmongo.setTo
import org.litote.kmongo.setValueOnInsert
import org.litote.kmongo.upsert
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository
import pt.fcul.keys.exceptions.DuplicateKeyException
import pt.fcul.keys.exceptions.KeyDoesntExistException
import pt.fcul.keys.model.KeyInfo

@Primary
@Repository
class MongoKeyRepository(
    val db: MongoDatabase
) : KeyRepository {

    override fun createKey(info: KeyInfo): KeyInfo {
        val collection = db.getCollection<KeyInfo>("keys")

        val upsertedId = collection.updateOne(
            KeyInfo::key eq info.key,
            setValueOnInsert(info),
            upsert()
        ).upsertedId ?: throw DuplicateKeyException(info.key)
        // if id is null, upsert resulted in an update, which means it already exists

        return collection.findOneById(upsertedId)!!
    }

    override fun readKey(key: String): KeyInfo {
        val collection = db.getCollection<KeyInfo>("keys")
        return collection.findOne(
            KeyInfo::key eq key
        ) ?: throw KeyDoesntExistException(key)
    }

    override fun updateKey(key: String, info: KeyInfo) {
        val collection = db.getCollection<KeyInfo>("keys")

        val updateResult = collection.updateOne(
            KeyInfo::key eq key,
            set(
                KeyInfo::quota setTo info.quota,
                KeyInfo::used setTo info.used,
                KeyInfo::key setTo info.key,
                KeyInfo::contact setTo info.contact,
                KeyInfo::scope setTo info.scope,
            )
        )

        if (updateResult.matchedCount <= 0) throw KeyDoesntExistException(info.key)
    }

    override fun deleteKey(key: String) {
        val collection = db.getCollection<KeyInfo>("keys")

        val deleteResult = collection.deleteOne(
            KeyInfo::key eq key
        )

        if (deleteResult.deletedCount <= 0) throw KeyDoesntExistException(key)
    }

}