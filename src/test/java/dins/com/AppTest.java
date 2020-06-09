package dins.com;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Unit test for simple App.
 */
public class AppTest{

    private final String URI = "http://localhost:8080/users";

    @Test
    public void test_POST() {
//        get(URI + "/1").
//                then().assertThat().
//                    statusCode(200).and().body("id", equalTo(1)).body("firstName", equalTo("John"));
        Gson gson = new Gson();
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("id", 3);
        mapa.put("firstName", "Kirk");
        mapa.put("lastName", "Hamm");
        String newstr = gson.toJson(mapa);
        given().contentType(ContentType.JSON).accept(ContentType.JSON).
                body(newstr).
                when().
                post(URI).
                then().
                statusCode(201);
        System.out.println(newstr);

    }
}
