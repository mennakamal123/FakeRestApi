package authors;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DeleteAuthor {
    @Test
    public static void testDeleteAuthor(){
        String baseUrl = "https://fakerestapi.azurewebsites.net";


        given().baseUri(baseUrl)
                .when().delete("/api/v1/Authors/5")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L));
}}
