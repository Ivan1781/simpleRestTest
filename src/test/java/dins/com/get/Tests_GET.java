package dins.com.get;

import dins.com.GeneralTest;
import dins.com.endpoints.Endpoint;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class Tests_GET extends GeneralTest {

    @Test
    public void test_GET_getUsers_positive(){

        given().
                spec(requestSpec).
        when().
                get(Endpoint.users).
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    @Parameters({"id"})
    public void test_GET_getUsersById_negative(String id){
        given().
                spec(requestSpec).
        when().
                get(Endpoint.users + "/" + id).
        then().
                assertThat().
                statusCode(400);
    }
}
