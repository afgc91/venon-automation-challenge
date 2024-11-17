package config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import io.github.cdimascio.dotenv.Dotenv;

public class ApiConfig {
    public static final String PET_PATH = "/pet";

    @BeforeAll
    public static void setup() {
        Dotenv env = Dotenv.configure().directory("./").load();
        RestAssured.baseURI = env.get("BASE_URI");
        RestAssured.basePath = env.get("BASE_PATH");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}