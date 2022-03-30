package ul.fc.mei.cn.grpc

import org.lognet.springboot.grpc.GRpcService
import ul.fc.cn.proto.BusinessOuterClass.*
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.business
import ul.fc.cn.proto.businessId
import ul.fc.cn.proto.deleteBusinessResponse
import ul.fc.cn.proto.searchBusinessResponse
import ul.fc.mei.cn.business.BusinessService
import ul.fc.mei.cn.business.model.BusinessInputModel
import ul.fc.mei.cn.business.model.toProtobuf
import ul.fc.mei.cn.business.utils.NotFoundException

@GRpcService
class BusinessRPCService(val service: BusinessService) : BusinessServiceGrpcKt.BusinessServiceCoroutineImplBase() {
    override suspend fun addBusiness(request: Business): BusinessId {
        val input = BusinessInputModel(request.name, request.address, request.city, request.latitude, request.longitude)
        val businessId = service.addBusiness(input)
        return businessId { id = businessId }
    }

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
        val newBusiness = BusinessInputModel(
            businessInput.name,
            businessInput.address,
            businessInput.city,
            businessInput.latitude,
            businessInput.longitude
        )
        val updatedBusiness = service.updateBusiness(businessId, newBusiness)

        return business {
            id = request.id
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
        val searchResults =
            service.searchBusinesses(request.latitude, request.longitude, request.radius, request.limit, request.skip)
        return searchBusinessResponse {
            results.addAll(searchResults.map { it.toProtobuf() })
        }
    }
}