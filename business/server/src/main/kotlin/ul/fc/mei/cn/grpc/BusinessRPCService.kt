package ul.fc.mei.cn.grpc

import com.mongodb.MongoClientException
import org.lognet.springboot.grpc.GRpcService
import ul.fc.cn.proto.BusinessOuterClass.*
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.business
import ul.fc.cn.proto.businessError
import ul.fc.cn.proto.businessId
import ul.fc.cn.proto.businessList
import ul.fc.cn.proto.businessResult
import ul.fc.cn.proto.deleteBusinessResponse
import ul.fc.cn.proto.searchBusinessResponse
import ul.fc.mei.cn.core.BusinessService
import ul.fc.mei.cn.core.model.ModelBusiness
import ul.fc.mei.cn.core.model.toProtobuf
import ul.fc.mei.cn.core.common.BadRequestException
import ul.fc.mei.cn.core.common.NotFoundException


@GRpcService
class BusinessRPCService(val service: BusinessService) : BusinessServiceGrpcKt.BusinessServiceCoroutineImplBase() {

    override suspend fun getBusiness(request: BusinessId): BusinessResult = handleBusinessExceptions {
        val businessId = request.id
        val biz = service.getBusiness(businessId)
        businessResult {
            business = business {
                businessId { id = biz.id }
                address = biz.address
                city = biz.city
                latitude = biz.latitude
                longitude = biz.longitude
            }
        }
    }

    override suspend fun updateBusiness(request: UpdateBusinessRequest): BusinessResult =
        handleBusinessExceptions {
            val businessInput = request.business
            val businessId = request.id.id
            val newBusiness = ModelBusiness(
                id = businessId,
                name = businessInput.name,
                address = businessInput.address,
                city = businessInput.city,
                latitude = businessInput.latitude,
                longitude = businessInput.longitude
            )
            val updatedBusiness = service.updateBusiness(businessId, newBusiness)

            businessResult {
                business = business {
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
                service.searchBusinesses(
                    request.latitude,
                    request.longitude,
                    request.radius,
                    request.limit,
                    request.skip
                )
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

    fun handleBusinessExceptions(block: () -> BusinessResult): BusinessResult {
        return try {
            block()
        } catch (exc: BadRequestException) {
            businessResult {
                error = businessError {
                    message = exc.message ?: "Invalid Params"
                    type = BusinessError.BusinessErrorType.INVALID_PARAMS
                }
            }
        } catch (exc: NotFoundException) {
            businessResult {
                error = businessError {
                    message = exc.message ?: "Not Found Exception"
                    type = BusinessError.BusinessErrorType.NOT_FOUND
                }
            }
        } catch (exc: MongoClientException) {
            businessResult {
                error = businessError {
                    message = exc.message ?: "MongoDB Exception"
                    type = BusinessError.BusinessErrorType.DB_ACCESS_ERROR
                }
            }
        } catch (exc: Exception) {
            businessResult {
                error = businessError {
                    message = exc.message ?: "An Exception was thrown"
                    type = BusinessError.BusinessErrorType.UNKNOWN
                }
            }
        }

    }
}