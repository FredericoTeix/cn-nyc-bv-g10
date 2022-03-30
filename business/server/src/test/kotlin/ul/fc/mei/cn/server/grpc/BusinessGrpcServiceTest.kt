package ul.fc.mei.cn.server.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ul.fc.cn.proto.BusinessOuterClass
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.businessId


@SpringBootTest
class BusinessGrpcServiceTest {


    companion object {
        private val stub = BusinessServiceGrpcKt.BusinessServiceCoroutineStub(
            ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build()
        )
    }

    @Test
    suspend fun testDelete() {
        val result = stub.deleteBusiness(businessId { id = 1 })
        assert(result.result == BusinessOuterClass.DELETE_BUSINESS_RESULT.SUCCESS)
    }
}
