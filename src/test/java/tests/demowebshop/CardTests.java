package tests.demowebshop;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CardTests extends TestBased {
    @Test
    void addCardTest(){
        String authCookieKey = "NOPCOMMERCE.AUTH";
        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", login)
                .formParam("Password", password)
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieKey);

        String data = "product_attribute_72_5_18=53" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";


        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8" )
                .cookie(authCookieKey,authCookieValue)
                .body(data)
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is (true))
                .body("message", is ("The product has been added to your <a href=\"/cart\">shopping cart</a>"));

    }
    @Test
    void addToCardAsAnonymTest(){
        String data = "product_attribute_72_5_18=53" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";


        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8" )
                .body(data)
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is (true))
                .body("message", is ("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is ("(1)"));

    }
}
