package ul.fc.mei.cn.server

import net.devh.boot.grpc.client.inject.GrpcClient
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ul.fc.mei.cn.proto.BusinessOuterClass
import ul.fc.mei.cn.proto.BusinessServiceGrpcKt
import ul.fc.mei.cn.proto.businessID


@SpringBootTest
class ServerApplicationTests {


    @GrpcClient("BusinessService")
    private val deleteStub: BusinessServiceGrpcKt.BusinessServiceCoroutineStub? = null

    @Test
    suspend fun testDelete() {
        val result = deleteStub?.deleteBusiness(businessID { id = "1" })
        assert(result?.result == BusinessOuterClass.DELETE_BUSINESS_RESULT.SUCCESS )
    }
}
