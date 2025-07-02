package tests.demowebshop;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBased {

        String login = "qa@qa.guru";
        String password = "qa@qa.guru1";

        @BeforeAll
        static void setup() {
            Configuration.baseUrl = "https://demowebshop.tricentis.com";
            RestAssured.baseURI = "https://demowebshop.tricentis.com";
        }
}
