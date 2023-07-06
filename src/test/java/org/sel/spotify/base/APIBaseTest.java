package org.sel.spotify.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.sel.spotify.factory.DriverFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class APIBaseTest {

    public Logger logger = LogManager.getLogger(APIBaseTest.class);

    protected Properties prop;
    protected String ACCESS_TOKEN;

    protected DriverFactory df;

    @Parameters({"clientUser", "clientSecret"})
    @BeforeTest
    public void setupAPI(String clientUser,String clientSecret) {
        df = new DriverFactory();
        prop = df.initProp();
        prop.setProperty("clientUser", clientUser);
        prop.setProperty("clientSecret", clientSecret);

        System.out.println("clientUser"+ clientUser);
        System.out.println("clientSecret"+ clientSecret);

        ACCESS_TOKEN = createAccessToken(clientUser, clientSecret);

    }

    private String createAccessToken(String clientUser, String clientSecret) {


        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", clientUser)
                .formParam("client_secret", clientSecret)
                .when()
                .post("https://accounts.spotify.com/api/token")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String accessToken = response.jsonPath().getString("access_token");
        System.out.println("Access Token: " + accessToken);
        return  accessToken;
    }

    private String encodeBase64(String value) {
        return java.util.Base64.getEncoder().encodeToString(value.getBytes());
    }
    public String setup() {
        return prop.getProperty("apiUrl").trim();
    }
    }

