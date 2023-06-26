package org.sel.spotify.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.sel.spotify.base.APIBaseTest;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;


import static io.restassured.RestAssured.given;

public class APIStepDefinitions extends APIBaseTest {
    private String ACCESS_TOKEN;
    protected Properties prop;
    private String artist;
    private Response response;
    private String uri;
    protected String clientUser;
    protected String clientSecret;

    @Given("I have obtained the access token")
    public void obtainAccessToken() {
        clientUser = System.getProperty("clientUser");
        clientSecret = System.getProperty("clientSecret");
//        loadProperties();
        System.out.println(clientUser +":"+ clientSecret);

        response = given()
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

        ACCESS_TOKEN = response.jsonPath().getString("access_token");
        System.out.println(ACCESS_TOKEN);

    }

    @When("I retrieve a track with ID {string}")
    public void retrieveTrack(String trackId) {
        RestAssured.baseURI = loadBaseURI();
        response = given()
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .when()
                .get("/tracks/{id}", trackId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Assert specific fields in the response
//        logger.info(response.jsonPath().getString("album.name"));

        artist = response.jsonPath().getString("artists[0].name");
        logger.info("artist:"+ artist);
    }

    @Then("the track artist should be {string}")
    public void verifyArtist(String expectedArtist) {
//        response.then().body("name", CoreMatchers.equalTo("Paradise"));
        response.then().body("artists[0].name", CoreMatchers.equalTo(expectedArtist));
//        response.then().body("album.name", CoreMatchers.equalTo("Mylo Xyloto"));
    }

    @When("I search for tracks by the same artist")
    public void searchTracksByArtist() {
        RestAssured.baseURI = loadBaseURI();
        RestAssured.filters(new RequestLoggingFilter(logRequestDetails()));
        logger.info(":::"+artist);
        Response response = given()
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .queryParam("q","artist:"+artist)
                .queryParam("type", "artist")
                .when()
                .get("/search" )
                .then()
                .statusCode(200)
                .extract()
                .response();
//        logger.info(response.getBody().print());

        // Perform assertions and further processing
    }

    private PrintStream logRequestDetails() {
        try {
            return new PrintStream("request.log");
        } catch (Exception e) {
            e.printStackTrace();
            return System.out; // Fall back to console if logging fails
        }
    }

    @And("the search results should include tracks by the artist")
    public void verifySearchResults() {
        // Perform assertions on search results
        response.getBody().print();
    }

    @When("I create a new playlist with name {string}")
    public void createPlaylist(String playlistName) {
        String requestBody = "{ \"name\": \"" + playlistName + "\" }";

        response = given()
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/playlists")
                .then()
                .statusCode(201)
                .extract()
                .response();

        String playlistId = response.jsonPath().getString("id");
        System.out.println("New Playlist ID: " + playlistId);
    }

    @Then("I should see a new playlist ID")
    public void verifyPlaylistCreation() {
        // Perform assertions on playlist creation
        response.getBody().print();

    }

    private String loadBaseURI() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties");
            properties.load(fis);
            uri = properties.getProperty("apiUrl");
            System.out.println(uri);
            return uri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No URI";
    }
}

