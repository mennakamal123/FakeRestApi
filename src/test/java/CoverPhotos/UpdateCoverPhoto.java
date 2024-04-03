package CoverPhotos;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCoverPhoto {
    @Test
    public void testUpdateCoverPhoto(){
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        String body="{\n" +
                "  \"id\": 6,\n" +
                "  \"idBook\": 8,\n" +
                "  \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 3&w=250&h=350\"\n" +
                "}\n";

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put("/api/v1/CoverPhotos/6")
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
                .assertThat().body("id",equalTo(6)
                        ,"idBook",equalTo(8)
                        ,"url",equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 3&w=250&h=350")
                );
    }
}
