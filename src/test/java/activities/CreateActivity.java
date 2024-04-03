package activities;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateActivity {

    @Test
    public static void testCreateActivity()

    {
        String baseUrl = "https://fakerestapi.azurewebsites.net";
        String body ="{\n" +
                "  \"id\": 18,\n" +
                "  \"title\": \"string\",\n" +
                "  \"dueDate\": \"2024-03-09T11:50:17.873Z\",\n" +
                "  \"completed\": false\n" +
                "}";
         given().baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/Activities")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body("id",isA(Integer.class),
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
                .assertThat().body("id",equalTo(18)
                        ,"title",equalTo("string")
                ,"dueDate",equalTo("2024-03-09T11:50:17.873Z")
                        ,"completed",equalTo(false)
                );
              //  .extract().response();
       // int id= response.jsonPath().getInt("id");
    }
}
