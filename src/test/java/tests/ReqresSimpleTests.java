package tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresSimpleTests extends TestBase {

    @Test
    void getSingleUserTest() {
        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"));
    }

    @Test
    void singleUserNotFoundTest() {
        given()

                .when()
                .get("/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void createUserTest() {
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .body(requestBody)
                .header("Content-Type", "application/json")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    void updateUserWithPatchTest() {
        String requestBody = "{ \"job\": \"zion resident\" }";

        given()
                .body(requestBody)
                .header("Content-Type", "application/json")
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo("zion resident"));
    }

    @Test
    void deleteUserTest() {
        given()

                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }


    @Test
    void updateUserWithPutTest() {
        String requestBody = "{ \"job\": \"zion resident\" }";

        given()
                .body(requestBody)
                .header("Content-Type", "application/json")
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .body("job", equalTo("zion resident"));
    }
}
