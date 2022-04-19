package ul.fc.mei.cn.core.repository

import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.InsertOneOptions
import com.mongodb.client.model.ReturnDocument
import org.litote.kmongo.MongoOperator.geometry
import org.litote.kmongo.MongoOperator.near
import org.litote.kmongo.MongoOperator.type
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValueOnInsert
import org.litote.kmongo.upsert
import org.springframework.stereotype.Component
import ul.fc.mei.cn.core.model.Business
import ul.fc.mei.cn.core.model.DBBusiness
import ul.fc.mei.cn.core.model.ModelBusiness
import ul.fc.mei.cn.core.model.toBusiness
import ul.fc.mei.cn.core.model.toDBModel
import ul.fc.mei.cn.web.utils.NotFoundException

@Component
class BusinessMongoDBRepository(private val collection: CoroutineCollection<DBBusiness>) : BusinessRepository {

    override suspend fun getBusiness(bid: String): Business {
        return collection.findOneById(bid)?.toBusiness()
            ?: throw NotFoundException("Business with ID: $bid not found")

    }

    override suspend fun deleteBusiness(bid: String) {
        val result = collection.deleteOneById(bid)
        if (result.deletedCount == 0L)
            throw NotFoundException("Business with ID: $bid not found")
    }

    override suspend fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Int,
        limit: Int,
        skip: Int
    ): List<ModelBusiness> {
        return collection.find(
            radiusFilter(latitude, longitude, radius.toDouble())
        ).skip(skip).limit(limit).toList().map { it.toBusiness() }
    }

    override suspend fun updateBusiness(business: Business): Business {
        return collection.findOneAndUpdate(
            Business::id eq business.id,
            setValueOnInsert(business.toDBModel()),
            FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        )?.toBusiness() ?: throw NotFoundException("Business with ID: ${business.id} not found")
    }

    override suspend fun addBusiness(business: Business): Boolean {
        return collection.updateOneById(
            business.id,
            business.toDBModel(),
            upsert()
        ).wasAcknowledged()
    }

    private fun radiusFilter(latitude: Double, longitude: Double, radius: Double): String = "{location: {$near:  " +
            "{ $geometry: { $type: \"Point\", coordinates: [$longitude, $latitude] },\$maxDistance: $radius}}}".trimIndent()
}