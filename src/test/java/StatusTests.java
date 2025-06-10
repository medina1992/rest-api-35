import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;


public class StatusTests {
/*
1. request to https://selenoid.autotests.cloud/status
2. Get response ({
  "total": 5,
  "used": 0,
  "queued": 0,
  "pending": 0
  3. Check total is 5
*/
@Test
    void checkTotal5(){

    get("https://selenoid.autotests.cloud/status")
            .then()
            .body("total" , is (5));
}




}
