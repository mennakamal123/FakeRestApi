package books;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBooks {
    @Test
    public static void testPutAuthor() {
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        String body = "{\n" +
                "    \"id\": 9,\n" +
                "    \"title\": \"New Title\",\n" +
                "    \"description\": \"string\",\n" +
                "    \"pageCount\": 6,\n" +
                "    \"excerpt\": \"string\",\n" +
                "    \"publishDate\": \"2024-03-09T15:25:28.235Z\"\n" +
                "}";
        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put("/api/v1/Books/9")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L)).
                assertThat().body("id",isA(Integer.class),
                        "title",isA(String.class),
                        " description",isA(String.class),
                        "pageCount",isA(Integer.class),
                        " excerpt",isA(String.class),
                        " publishDate",isA(String.class)
                )
                .assertThat().body("$",hasKey("id"),
                        "$",hasKey("title"),
                        "$",hasKey("description"),
                        "$",hasKey("pageCount"),
                        "$",hasKey("excerpt")
                        ,"$",hasKey("publishDate")
                )
                .assertThat().body("id",notNullValue()
                        ,"title",notNullValue()
                        ,"description",notNullValue()
                        ,"pageCount",notNullValue()
                        ,"excerpt",notNullValue(),
                        "publishDate",notNullValue() )
                .assertThat().body("id",equalTo(9)
                        ,"title",equalTo("New Title")
                        ,"description",equalTo("string")
                        , "pageCount",equalTo(6),
                        "excerpt",equalTo("string"),
                "publishDate",equalTo("2024-03-09T15:25:28.235Z")

                );
}}
