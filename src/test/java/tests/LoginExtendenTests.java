package tests;

import lombok.LoginBodyLombokModel;
import lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyModel;
import models.pojo.LoginResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExtendenTests {
 /*
    1. Make request (POST) to https://reqres.in/api/login
        with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
  */

    @Test
    void successfulLoginLombokTest() {
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        LoginResponseLombokModel response = given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);
        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }

    @Test
    void unsuccessfulLogin400Test() {
        String authData = "";

        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void userNotFoundTest() {
        String authData = "{\"email\": \"eveasdas.holt@reqres.in\", \"password\": \"cda\"}";

        given()
                .body(authData)
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    void missingPasswordTest() {
        String authData = "{\"email\": \"eveasdas.holt@reqres.in\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }


    @Test
    void missingLoginTest() {
        String authData = "{\"password\": \"cda\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void wrongBodyTest() {
        String authData = "%}";

        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

    @Test
    void unsuccessfulLogin415Test() {
        given()
                .log().uri()
                .header("x-api-key", "reqres-free-v1")
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }
}