package dins.com.post;

import com.google.gson.Gson;
import dins.com.endpoints.Endpoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Tests_POST {

    private static final Logger logger = LogManager.getLogger();

    @Test
    @Parameters({"requiredField", "valueField"})
    public void test_POST_createUser_withRequiredField_positive(String field, String valueField) {
        logger.warn("test_POST_createUser" + "starts");
        String bodyOfRequest = getJsonString(field ,valueField);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(bodyOfRequest).
                when().
                post(Endpoint.users).
                then().
                assertThat().
                statusCode(201).and().
                body(field, equalTo(valueField));
        logger.warn(valueField + " was added");
    }

    @Test
    @Parameters({"nonRequiredField", "valueField"})
    public void test_POST_createUser_withNonRequiredField_negative(String field, String valueField){
        String bodyOfRequest = getJsonString(field ,valueField);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(bodyOfRequest).
                when().
                post(Endpoint.users).
                then().
                assertThat().
                body(field, equalTo(valueField));
    }

    @AfterMethod
    public void delete_testing_items(){
        logger.warn("deleting of test_user" + " starts");
        Response e = given().get(Endpoint.users);
        List<Integer> a = e.jsonPath().getList("id");
        when().
                delete(Endpoint.users + "/" + a.get(a.size()-1));
        logger.warn("was deleted");
    }

    private String getJsonString(String field, Object valueField){
        Gson gson = new Gson();
        Map<String, Object> mapa = new HashMap<>();
        mapa.put(field, valueField);
        return gson.toJson(mapa);
    }
}
