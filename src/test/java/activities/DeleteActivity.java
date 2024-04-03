package activities;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DeleteActivity {
@Test
    public static void testDeleteActivity(){
    String baseUrl = "https://fakerestapi.azurewebsites.net";


    given().baseUri(baseUrl)
            .when().delete("/api/v1/Activities/6")
            .then().log().all()
            .assertThat().statusCode(200)
            .assertThat().time(lessThan(3000L));

//  int id= PostRequest.testCreateActivity();

}
}
