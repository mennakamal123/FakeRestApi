package authors;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateAuthor {
    @Test
    public static void testPutAuthor() {
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        String body = "{\n" +
                "  \"id\": 10,\n" +
                "  \"idBook\": 0,\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\"\n" +
                "}";
        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put("/api/v1/Authors/9")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("id", isA(Integer.class),
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
                .assertThat().body("id", equalTo(10)
                        , "idBook", anyOf(equalTo(0))
                        , "firstName", equalTo("string")
                        , "lastName", equalTo("string")
                );
    }
}
