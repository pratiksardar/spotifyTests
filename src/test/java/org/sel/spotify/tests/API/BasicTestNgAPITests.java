package org.sel.spotify.tests.API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.sel.spotify.base.APIBaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BasicTestNgAPITests extends APIBaseTest {

    private String artist;

    @BeforeClass
    public void testSetup() {
        RestAssured.baseURI =  prop.getProperty("apiUrl").trim();
    }

    @Test(priority = 2)
    public void testGetTrack() {
        Response response = given()
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .when()
                .get("/tracks/{id}", "6nek1Nin9q48AVZcWs9e9D")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Assert specific fields in the response
//        logger.info(response.jsonPath().getString("album.name"));
        response.then().body("name", equalTo("Paradise"));
        response.then().body("artists[0].name", equalTo("Coldplay"));
        response.then().body("album.name", equalTo("Mylo Xyloto"));
        artist = response.jsonPath().getString("artists[0].name");
        logger.info("artist:"+ artist);

    }

    @Test(dependsOnMethods = "testGetTrack", priority = 1)
        public void searchSongsFromSameArtist(){
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
        logger.info(response.getBody().print());


    }

    @DataProvider(name = "songIds")
    public Object[][] provideSongIds() {
        return new Object[][]{
                {"6y0igZArWVi6Iz0rj35c1Y"},
                {"another-song-id"},
                {"yet-another-song-id"}
        };
    }

}
