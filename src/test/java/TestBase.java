import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";


        RequestSpecification spec = new RequestSpecBuilder()
                .addHeader("x-api-key", "reqres-free-v1")
                .build();

        RestAssured.requestSpecification = spec;

    }


}
