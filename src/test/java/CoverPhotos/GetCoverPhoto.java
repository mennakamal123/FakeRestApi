package CoverPhotos;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetCoverPhoto {
    String baseUrl = "https://fakerestapi.azurewebsites.net";
    @Test
    public void testGetAllCoverPhotos(){
        given().baseUri(baseUrl).
                when().get("/api/v1/CoverPhotos")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L));

    }
    @Test
    public void testGetCoverPhoto(){
        given().baseUri(baseUrl)
                .when().get("/api/v1/CoverPhotos/3")
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
                .assertThat().body("id",equalTo(3)
                        ,"idBook",equalTo(3)
                        ,"url",equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 3&w=250&h=350")
                );
    }
    @Test
    public void testGetCoverPhotoWithBookId(){
        given().baseUri(baseUrl).
                when().get("/api/v1/CoverPhotos/books/covers/7")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("[0].id",isA(Integer.class),
                        "[0].idBook",isA(Integer.class),
                        "[0].url",isA(String.class))
                .assertThat().body("[0]",hasKey("id"),
                        "[0]",hasKey("idBook"),
                        "[0]",hasKey("url"))
                .assertThat().body("[0].id",notNullValue()
                        ,"[0].idBook",notNullValue()
                        ,"[0].url",notNullValue())
                .assertThat().body("[0].id",equalTo(7)
                        ,"[0].idBook",equalTo(7)
                        ,"[0].url",equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 7&w=250&h=350")
                );

    }
}
