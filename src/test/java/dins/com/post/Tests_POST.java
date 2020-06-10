package dins.com.post;

import com.google.gson.Gson;
import dins.com.endpoints.Endpoint;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Tests_POST {

    @Test
    public void test_POST() {
        String bodyOfRequest = getJsonString("Arkady");
        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(bodyOfRequest).
                when().
                post(Endpoint.users).
                then().
                assertThat().
                statusCode(201).and().
                body("id", equalTo(10));
    }

    private String getJsonString(String firstNameValue){
        Gson gson = new Gson();
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("firstName", firstNameValue);
        return gson.toJson(mapa);
    }
}
