package activities;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class GetActivity {

    String baseUrl = "https://fakerestapi.azurewebsites.net";

    @Test
    public void testGetAllActivities(){
given().baseUri(baseUrl).
        when().get("/api/v1/Activities")
        .then().log().all()
        .assertThat().statusCode(200)
        .assertThat().time(lessThan(3000L))
        .extract().response();


    }


    @Test
    public void testGetSingleActivity(){
        given().baseUri(baseUrl)
                .when().get("/api/v1/Activities/3")
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
                .assertThat().body("id",equalTo(3)
                ,"title",equalTo("Activity 3")
                ,"completed",equalTo(false)
                        );


    }
}
