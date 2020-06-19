package sob.com.userGet;

import sob.com.GeneralTest;
import sob.com.endpoints.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class TestUserGet extends GeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(TestUserGet.class);

    @Test
    public void testGetGetUsersPositive(){
        logger.info("TEST_GET_USERS positive starts");
        given().
                spec(requestSpec).
        when().
                get(Endpoint.users).
        then().
                assertThat().
                statusCode(200);
        logger.info("list of users received");
    }

    @Test
    @Parameters({"id"})
    public void testGetGetUsersByIdNegative(String id){
        logger.info("TEST_GET_USER_BY_ID negative starts");
        given().
                spec(requestSpec).
        when().
                get(Endpoint.users + "/" + id).
        then().
                assertThat().
                statusCode(400);
        logger.info("The value of field is not correct");
    }
}
