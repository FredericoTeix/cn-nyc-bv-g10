package ul.fc.mei.cn.server.grpc

import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ul.fc.cn.proto.BusinessOuterClass.BusinessError.BusinessErrorType
import ul.fc.cn.proto.BusinessOuterClass.DELETE_BUSINESS_RESULT
import ul.fc.cn.proto.BusinessServiceGrpcKt
import ul.fc.cn.proto.business
import ul.fc.cn.proto.businessId
import ul.fc.cn.proto.updateBusinessRequest
import ul.fc.mei.cn.core.model.Business
import ul.fc.mei.cn.core.repository.BusinessMongoDBRepository


@SpringBootTest
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BusinessGrpcServiceTest {

    @Autowired
    lateinit var dataRepo: BusinessMongoDBRepository

    lateinit var stub: BusinessServiceGrpcKt.BusinessServiceCoroutineStub

    @BeforeAll
    fun initialization() {
        stub = BusinessServiceGrpcKt.BusinessServiceCoroutineStub(
            ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build()
        )
        val business = Business("3", "Some Bizz", "Some Street n1", "New York", 70.0, 150.0)
        val otherBiz = Business("4", "Some Other Bizz", "Some Street n1", "New York", 70.0, 150.0)
        dataRepo.addBusiness(business)
        dataRepo.addBusiness(otherBiz)
    }

    @Test
    fun `Non existing resource should not exist`() = runBlocking {
        val getResult = stub.getBusiness(businessId {
            id = "-1"
        })

        assert(getResult.hasError())
        val error = getResult.error
        assert(error.type == BusinessErrorType.NOT_FOUND)
    }


    @Test
    suspend fun `Existing resource should be returned`() = runBlocking {
        val getResult = stub.getBusiness(businessId {
            id = "4"
        })

        assert(getResult.hasBusiness())
        val business = getResult.business
        assert(business.id.id == "4")
        assert(business.name == "Some Bizz")
    }

    @Test
    fun `Attempting to update non existing resource should lead to error`() = runBlocking {
        val updateResult = stub.updateBusiness(
            updateBusinessRequest {
                id = businessId {
                    id = "50"
                }
                business = business {
                    id = businessId {
                        id = "-1"
                    }
                    name = "Some Other Updated Bizz"
                    address = "Some Street n1"
                    city = "New York"
                    latitude = 70.0
                    longitude = 150.0
                }

            }
        )
        assert(updateResult.hasError())
        val error = updateResult.error
        println(error.message)
        assert(error.type == BusinessErrorType.NOT_FOUND)
    }

    @Test
    fun `Attempting to delete non existing resource should lead to error`() = runBlocking {
        val deleteResult = stub.deleteBusiness(
            businessId { id = "-1" }
        )
        assert(deleteResult.result == DELETE_BUSINESS_RESULT.ERROR)
    }

    @Test
    fun `Attempting to update with invalid coordinates should lead to error`() = runBlocking {
        val updateResult = stub.updateBusiness(
            updateBusinessRequest {
                id = businessId {
                    id = "50"
                }
                business = business {
                    id = businessId {
                        id = "-1"
                    }
                    name = "Some Other Updated Bizz"
                    address = "Some Street n1"
                    city = "New York"
                    latitude = 1000.0
                    longitude = 9999.0
                }
            }
        )
        assert(updateResult.hasError())
        val error = updateResult.error
        assert(error.type == BusinessErrorType.INVALID_PARAMS)
    }

    @Test
    fun `Attempting to update an existing resource should be successful`() = runBlocking {
        val bizId = businessId { id = "4" }
        val newName = "Some Other Updated Bizz"

        val result = stub.getBusiness(bizId)
        assert(result.hasBusiness())

        val retrievedBusiness = result.business
        val updateResult = stub.updateBusiness(
            updateBusinessRequest {
                id = bizId
                business = business {
                    id = bizId
                    name = newName
                    address = retrievedBusiness.address
                    name = newName
                    city = retrievedBusiness.city
                    latitude = retrievedBusiness.latitude
                    longitude = retrievedBusiness.longitude
                }
            }
        )
        assert(updateResult.hasBusiness())
        val updatedBusiness = updateResult.business
        assert(updatedBusiness.name == newName)
    }

    @AfterAll
    fun cleanUp() {
        dataRepo.deleteBusiness("3")
        dataRepo.deleteBusiness("4")
        println("Clean up executed")
    }
}
