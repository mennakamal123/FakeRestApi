package books;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class DeleteBook {
    @Test
    public static void testDeleteBook(){
        String baseUrl = "https://fakerestapi.azurewebsites.net";


        given().baseUri(baseUrl)
                .when().delete("/api/v1/Books/7")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L));
    }
}
