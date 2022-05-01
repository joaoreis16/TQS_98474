package tqs;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AppTest {

    private final String URL = "https://jsonplaceholder.typicode.com/todos";
    
    
    @Test
    void Tests(){
        String title = "et porro tempora";

        given().when().get(this.URL).then().assertThat().statusCode(200);

        given().when().get(this.URL +"/4").then().assertThat().body("title", equalTo(title));

        given().when().get(this.URL).then().assertThat().body("id", hasItems(198, 199));
    }


}
