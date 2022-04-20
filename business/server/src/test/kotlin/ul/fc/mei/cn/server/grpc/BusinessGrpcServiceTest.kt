package ul.fc.mei.cn.server.grpc

import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import ul.fc.cn.proto.BusinessOuterClass
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.business
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
    fun testDelete() {
        runBlocking {
            val result = stub.deleteBusiness(businessId { id = 0 })
            assert(result.result == BusinessOuterClass.DELETE_BUSINESS_RESULT.ERROR)
        }
    }

    @Test
    fun getBusinessAfterCreate() {
        val createdId = businessId { id = 0 }
        runBlocking {
            val result = stub.addBusiness(business {
                id = createdId
                name= "someBiz"
                address = "asdasd"
                latitude = 25.0
                longitude = 25.0
            })
            assert(result.id.toInt() == 2)

            val business = stub.getBusiness(result)
        }
    }


    //TODO: Create Add Business Response
    //@Test
    fun badRequestTestGrpc() {
        val createdId = businessId { id = 0 }
        runBlocking {
            val result = stub.addBusiness(business {
                id = createdId
                name= ""
                address = ""
                latitude = 25.0
                longitude = 25.0
            })
        }

    }
}
