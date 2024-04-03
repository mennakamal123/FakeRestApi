package books;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetBook {
    String baseUrl = "https://fakerestapi.azurewebsites.net";

    @Test
    public void testGetAllBooks(){
        given().baseUri(baseUrl).
                when().get("/api/v1/Books")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L));



    }
    @Test
    public void testGetSingleBook(){
        given().baseUri(baseUrl)
                .when().get("/api/v1/Books/3")
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
                .assertThat().body("id",equalTo(3)
                        ,"title",equalTo("Book 3")
                        ,"description",equalTo("Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\n")
                , "pageCount",equalTo(300),
                      "excerpt",equalTo("Lorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\nLorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\nLorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\nLorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\nLorem lorem lorem. Lorem lorem lorem. Lorem lorem lorem.\n")

);

    }
}
