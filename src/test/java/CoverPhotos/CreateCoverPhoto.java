package CoverPhotos;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateCoverPhoto {
    @Test
    public void testCreateCoverPhoto(){
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        String body="{\n" +
                "    \"id\": 1,\n" +
                "    \"idBook\": 0,\n" +
                "    \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 3&w=250&h=350\"\n" +
                "}";

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/CoverPhotos")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L)).
                assertThat().body("id",isA(Integer.class),
                        "idBook",isA(Integer.class),
                        "url",isA(String.class))
                .assertThat().body("$",hasKey("id"),
                        "$",hasKey("idBook"),
                        "$",hasKey("url"))
                .assertThat().body("id",notNullValue()
                        ,"idBook",notNullValue()
                        ,"url",notNullValue())
                .assertThat().body("id",equalTo(1)
                        ,"idBook",equalTo(0)
                        ,"url",equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 3&w=250&h=350")
                );
    }
}
