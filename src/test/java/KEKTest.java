import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class KEKTest {
    String petid = "20020804";
    @Test
    public void test() {
        RestAssured.given().log().all().contentType(ContentType.JSON).body(
                "{\n" +
                        "  \"id\": 20020804,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"ilya\",\n" +
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
                        "}"
        ).post("https://petstore.swagger.io/v2/pet").then().log().all();
    }

    @Test
    public void testGET() {
        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/pet/" + petid)
                .then()
                .log().all();
    }

    @Test
    public void testPUT() {
        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(
                        "{\n" +
                                "  \"id\": 20020804,\n" +
                                "  \"category\": {\n" +
                                "    \"id\": 0,\n" +
                                "    \"name\": \"string\"\n" +
                                "  },\n" +
                                "  \"name\": \"ilya_updated\",\n" +
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
                                "}"
                )
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .log().all();
    }
}
