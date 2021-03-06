package sob.com.userPost;

import com.google.gson.Gson;
import sob.com.GeneralTest;
import sob.com.endpoints.Endpoint;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestUserPost extends GeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(TestUserPost.class);

    @Test
    @Parameters({"requiredField", "valueField"})
    public void testPostCreateUserWithRequiredFieldPositive(String field, String valueField) {
        logger.info("TEST_POST_CREATE_USER positive starts");
        String bodyOfRequest = getJsonString(field ,valueField);
        logger.info("body of request is {}", bodyOfRequest);
        given().
               spec(requestSpec).
               body(bodyOfRequest).
        when().
               post(Endpoint.users).
        then().
               assertThat().
               statusCode(201).and().
               body(field, equalTo(valueField));
        logger.info("user {} was added", valueField);
    }

    @Test
    @Parameters({"nonRequiredField", "valueField"})
    public void testPostCreateUserWithNonRequiredFieldNegative(String field, String valueField){
        logger.warn("TEST_POST_CREATE_USER_WITH_NON_REQUIRED_FIELD negative starts");
        String bodyOfRequest = getJsonString(field ,valueField);
        logger.info("body of request is {}", bodyOfRequest);
        given().
                spec(requestSpec).
                body(bodyOfRequest).
        when().
                post(Endpoint.users).
        then().
                assertThat().
                statusCode(400).and().
                body(containsString("firstName"));
        logger.info("user {} was not added", valueField);
    }

    @AfterClass
    public void deleteTestingItems(){
        logger.warn("deleting of test_user starts");
        Response response = given().get(Endpoint.users);
        List<Integer> isersId = response.jsonPath().getList("id");
        when().
               delete(Endpoint.users + "/" + isersId.get(isersId.size()-1));
        logger.info(Endpoint.users);
        logger.warn("test_user was deleted");
    }

    private String getJsonString(String field, Object valueField){
        Gson gson = new Gson();
        Map<String, Object> body = new HashMap<>();
        body.put(field, valueField);
        return gson.toJson(body);
    }
}
