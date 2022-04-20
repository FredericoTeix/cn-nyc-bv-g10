package ul.fc.mei.cn.grpc

<<<<<<< HEAD
=======
import com.mongodb.MongoClientException
>>>>>>> business-dev
import org.lognet.springboot.grpc.GRpcService
import ul.fc.cn.proto.BusinessOuterClass.*
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.business
import ul.fc.cn.proto.businessId
import ul.fc.cn.proto.businessList
import ul.fc.cn.proto.deleteBusinessResponse
import ul.fc.cn.proto.searchBusinessResponse
import ul.fc.mei.cn.core.BusinessService
import ul.fc.mei.cn.core.model.ModelBusiness
import ul.fc.mei.cn.core.model.toProtobuf
import ul.fc.mei.cn.core.common.BadRequestException
import ul.fc.mei.cn.core.common.NotFoundException



@GRpcService
class BusinessRPCService(val service: BusinessService) : BusinessServiceGrpcKt.BusinessServiceCoroutineImplBase() {

    override suspend fun getBusiness(request: BusinessId): Business {
        val businessId = request.id
        val business = service.getBusiness(businessId)
        return business {
            businessId { id = business.id }
            address = business.address
            city = business.city
            latitude = business.latitude
            longitude = business.longitude
        }
    }

    override suspend fun updateBusiness(request: UpdateBusinessRequest): Business {
        val businessInput = request.business
        val businessId = request.id.id
        val newBusiness = ModelBusiness(businessId,
            businessInput.name,
            businessInput.address,
            businessInput.city,
            businessInput.latitude,
            businessInput.longitude
        )
        val updatedBusiness = service.updateBusiness(businessId, newBusiness)

        return business {
            id = businessId {
                updatedBusiness.id
            }
            name = updatedBusiness.name
            address = updatedBusiness.address
            city = updatedBusiness.city
            longitude = updatedBusiness.longitude
            latitude = updatedBusiness.latitude
        }
    }

    override suspend fun deleteBusiness(request: BusinessId): DeleteBusinessResponse {
        val businessId = request.id
        return try {
            service.deleteBusiness(businessId)
            deleteBusinessResponse { result = DELETE_BUSINESS_RESULT.SUCCESS }
        } catch (exc: NotFoundException) {
            deleteBusinessResponse { result = DELETE_BUSINESS_RESULT.ERROR }
        }
    }

    override suspend fun searchBusiness(request: SearchBusinessRequest): SearchBusinessResponse {
        try {
            val searchResults =
                service.searchBusinesses(request.latitude, request.longitude, request.radius, request.limit, request.skip)
            return searchBusinessResponse {
                results = businessList {
                    results.addAll(searchResults.map { it.toProtobuf() })
                }
            }
        } catch (exc: BadRequestException) {
            return searchBusinessResponse {
                error = SearchBusinessResponse.SearchBusinessError.INVALID_PARAMS
            }
        } catch (exc: MongoClientException) {
            return searchBusinessResponse {
                error = SearchBusinessResponse.SearchBusinessError.DB_ACCESS_ERROR
            }
        } catch (exc: Exception) {
            return searchBusinessResponse {
                error = SearchBusinessResponse.SearchBusinessError.UNKNOWN
            }
        }
    }
}