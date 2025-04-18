package apiTests;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WiremockPost {
    static WireMockServer wireMockServer;
    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        // Мокаем POST /user
        wireMockServer.stubFor(post(urlEqualTo("/user"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(matchingJsonPath("$.name"))
                .withRequestBody(matchingJsonPath("$.job"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 101, \"name\": \"Meder\", \"job\": \"tester\", \"createdAt\": \"2025-04-16T10:00:00Z\" }")));
    }

    @Test
    public void testMockedCreateUser() {
        String requestBody = """
        {
            "name": "Meder",
            "job": "tester"
        }
        """;

        Response response = given()
                .baseUri("http://localhost:8080")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());

        Assertions.assertEquals("Meder", response.jsonPath().getString("name"));
        Assertions.assertEquals("tester", response.jsonPath().getString("job"));
    }
}
