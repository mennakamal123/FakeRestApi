package authors;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetAuthor {
    String baseUrl = "https://fakerestapi.azurewebsites.net";

    @Test
    public void testGetAllAuthors() {

        given().baseUri(baseUrl).
                when().get("/api/v1/Authors")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .extract().response();


    }

    @Test
    public void testGetSingleAuthor() {
        given().baseUri(baseUrl)
                .when().get("/api/v1/Authors/3")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L)).
                assertThat().body("id", isA(Integer.class),
                        "idBook", isA(Integer.class),
                        "firstName", isA(String.class),
                        "lastName", isA(String.class))
                .assertThat().body("$", hasKey("id"),
                        "$", hasKey("idBook"),
                        "$", hasKey("firstName"),
                        "$", hasKey("lastName"))
                .assertThat().body("id", notNullValue()
                        , "idBook", notNullValue()
                        , "firstName", notNullValue()
                        , "lastName", notNullValue())
                .assertThat().body("id", equalTo(3)
                        , "idBook", anyOf(equalTo(1), equalTo(2))
                        , "firstName", equalTo("First Name 3")
                        , "lastName", equalTo("Last Name 3")
                );


    }

    @Test
    public void testGetAuthorWithBookId() {
        given().baseUri(baseUrl)
                .when().get("/api/v1/Authors/authors/books/6")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L)).
                assertThat().body("[0].idBook", equalTo(6),
                        "[1].idBook", equalTo(6)

                        );
    }

}