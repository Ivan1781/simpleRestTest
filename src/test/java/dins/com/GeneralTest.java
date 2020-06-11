package dins.com;

import dins.com.endpoints.Endpoint;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.*;

public class GeneralTest {

    protected static final Logger logger = LogManager.getLogger();
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
