package activities;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UpdateActivity {
    @Test
    public void testUpdateActivity(){
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        String body = "{\n" +
                "  \"id\": 4,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2024-03-09T11:50:17.873Z\",\n" +
                "  \"completed\": false\n" +
                "}";

        given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when().put("/api/v1/Activities/4")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L)).
                assertThat().body("id",isA(Integer.class),
                        "title",isA(String.class),
                        "dueDate",isA(String.class),
                        "completed",isA(Boolean.class))
                .assertThat().body("$",hasKey("id"),
                        "$",hasKey("title"),
                        "$",hasKey("dueDate"),
                        "$",hasKey("completed"))
                .assertThat().body("id",notNullValue()
                        ,"title",notNullValue()
                        ,"dueDate",notNullValue()
                        ,"completed",notNullValue())
                .assertThat().body("id",equalTo(4)
                        ,"title",equalTo("string")
                        ,"dueDate",equalTo("2024-03-09T11:50:17.873Z")
                        ,"completed",equalTo(false)
                );

    }
}
