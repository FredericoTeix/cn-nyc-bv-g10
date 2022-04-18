package ul.fc.mei.cn.business.repository

import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.InsertOneOptions
import com.mongodb.client.model.ReturnDocument
import org.litote.kmongo.MongoOperator.lte
import org.litote.kmongo.MongoOperator.match
import org.litote.kmongo.MongoOperator.pow
import org.litote.kmongo.MongoOperator.subtract
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValueOnInsert
import org.springframework.stereotype.Component
import ul.fc.mei.cn.business.model.Business
import ul.fc.mei.cn.business.utils.NotFoundException

@Component
class BusinessMongoDBRepository(private val database: CoroutineDatabase) : BusinessRepository {

    private val collection = database.getCollection<Business>()

    override suspend fun getBusiness(bid: Long): Business {
        return collection.findOneById(bid)
            ?: throw NotFoundException("Business with ID: $bid not found")
    }

    override suspend fun deleteBusiness(bid: Long) {
        val result = database.getCollection<Business>().deleteOneById(bid)
        if (result.deletedCount == 0L)
            throw NotFoundException("Business with ID: $bid not found")
    }

    override suspend fun searchBusinesses(
        latitude: Double,
        longitude: Double,
        radius: Double,
        limit: Int,
        skip: Int
    ): List<Business> {
        return collection.find(
            radiusFilter(latitude, longitude, radius)
        ).skip(skip).limit(limit).toList()
    }

    override suspend fun updateBusiness(business: Business): Business {
        return collection.findOneAndUpdate(
            Business::id eq business.id,
            setValueOnInsert(business),
            FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        ) ?: throw NotFoundException("Business with ID: ${business.id} not found")
    }

    override suspend fun addBusiness(business: Business): Boolean {
        return collection.insertOne(
            business,
            InsertOneOptions()
        ).wasAcknowledged()
    }

    //TODO need correct way to check coordinates are inside radius (2 point distance is not suitable)
    //TODO: need to test this
    private fun radiusFilter(latitude: Double, longitude: Double, radius: Double): String = """
        {$match:{
            $lte: [
                {$subtract:[
                    {
                        $pow: [{
                            $subtract: ["longitude", $longitude]
                        }, 2]
                    }, {
                        $pow: [{
                            $subtract: ["latitude", $latitude]
                        }, 2]
                    }
                ]}, 
                { $pow: [$radius, 2] }]
                }}""".trimMargin()
}