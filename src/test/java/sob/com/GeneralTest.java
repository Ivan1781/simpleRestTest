package sob.com;

import sob.com.endpoints.Endpoint;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;

public class GeneralTest {

    protected static RequestSpecification requestSpec;

    @BeforeClass
    public static void setupRequestSpecBuilder() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(Endpoint.host)
                .setPort(Endpoint.port)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }
}
