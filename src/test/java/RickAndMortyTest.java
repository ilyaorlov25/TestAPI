import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class RickAndMortyTest {
    String rickAndMortyURL = "https://rickandmortyapi.com/api";

    @Test
    public void testGetCharacters() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .get(rickAndMortyURL + "/character")
                .then()
                .statusCode(200)
                .assertThat()
                .body("info.count", equalTo(826));
    }
}
