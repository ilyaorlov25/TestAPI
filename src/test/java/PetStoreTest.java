import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class PetStoreTest {
    String petstoreURL = "https://petstore.swagger.io/v2/pet";
    int petID = 1337322;
    String name = "ilyucha";

    String getJson(int id, String name) {
        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
    }

    @Test
    public void testDeletePet() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(getJson(petID, name))
                .post(petstoreURL)
                .then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .get(petstoreURL + "/" + petID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo(name));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(petstoreURL + "/" + petID)
                .then()
                .statusCode(200);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .get(petstoreURL + "/" + petID)
                .then()
                .statusCode(404);
    }
}
