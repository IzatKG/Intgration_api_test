package apiTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockTest {

    static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/user/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 1, \"name\": \"Meder\", \"email\": \"meder@test.com\" }")));
    }

    @Test
    public void testMockedEndpoint() {
        Response response = given()
                .when()
                .get("http://localhost:8080/user/1")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Response: " + response.asPrettyString());

        // Пример проверок
        Assertions.assertEquals(1, response.jsonPath().getInt("id"));
        Assertions.assertEquals("Meder", response.jsonPath().getString("name"));
    }

    @AfterAll
    public static void tearDown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
}
}
