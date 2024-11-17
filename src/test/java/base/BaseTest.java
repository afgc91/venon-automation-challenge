package base;

import config.ApiConfig;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseTest extends ApiConfig {
    protected RequestSpecification request;

    public BaseTest() {
        request = given()
                .contentType("application/json")
                .accept("application/json");
    }
}