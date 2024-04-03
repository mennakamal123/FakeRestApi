package CoverPhotos;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DeleteCoverPhoto {
    @Test
    public static void testDeleteCoverPhoto(){
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        given().baseUri(baseUrl)
                .when().delete("/api/v1/CoverPhotos/1")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L));
    }
}
