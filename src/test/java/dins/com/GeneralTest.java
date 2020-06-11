package dins.com;

import dins.com.endpoints.Endpoint;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.logging.LogManager;

import static io.restassured.RestAssured.*;

public class GeneralTest {

    protected static final Logger logger = LoggerFactory.getLogger(GeneralTest.class);
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
