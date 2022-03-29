package ul.fc.mei.cn.grpc

import org.springframework.stereotype.Component
import ul.fc.cn.proto.BusinessOuterClass
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.businessId
import ul.fc.mei.cn.business.BusinessService
import ul.fc.mei.cn.business.model.BusinessInputModel

@Component
class BusinessRPCService(val service: BusinessService) : BusinessServiceGrpcKt.BusinessServiceCoroutineImplBase() {
    override suspend fun addBusiness(request: BusinessOuterClass.Business): BusinessOuterClass.BusinessId {
        val input = BusinessInputModel(request.name, request.address, request.latitude, request.longitude)
        val businessId = service.addBusiness(input)
        return businessId { id = businessId.toLong() }
    }

    override suspend fun getBusiness(request: BusinessOuterClass.BusinessId): BusinessOuterClass.Business {
        return super.getBusiness(request)
    }

    override suspend fun updateBusiness(request: BusinessOuterClass.UpdateBusinessRequest): BusinessOuterClass.Business {
        return super.updateBusiness(request)
    }

    override suspend fun deleteBusiness(request: BusinessOuterClass.BusinessId): BusinessOuterClass.DeleteBusinessResponse {
        return super.deleteBusiness(request)

    }

    override suspend fun searchBusiness(request: BusinessOuterClass.SearchBusinessRequest): BusinessOuterClass.DeleteBusinessResponse {
        return super.searchBusiness(request)
    }


}