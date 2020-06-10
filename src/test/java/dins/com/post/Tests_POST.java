package dins.com.post;

import com.google.gson.Gson;
import dins.com.endpoints.Endpoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
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

    private int id;

    @Test
    @Parameters({"field", "valueField"})
    public void test_POST_createUser_withRequiredField_positive(String field, String valueField) {
        String bodyOfRequest = getJsonString(field ,valueField);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(bodyOfRequest).
                when().
                post(Endpoint.users).
                then().
                assertThat().
                statusCode(201).and().
                body(field, equalTo(valueField));
    }

    @AfterMethod
    public void delete_testing_items(){
        Response e = given().get(Endpoint.users);
        List<Integer> a = e.jsonPath().getList("id");
        when().
            delete(Endpoint.users + "/" + a.get(a.size()-1));
        System.out.println("Was deleted");
    }

    private String getJsonString(String field, Object valueField){
        Gson gson = new Gson();
        Map<String, Object> mapa = new HashMap<>();
        mapa.put(field, valueField);
        return gson.toJson(mapa);
    }
}
