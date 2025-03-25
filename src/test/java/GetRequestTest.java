import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class GetRequestTest {

    //curl --location 'https://reqres.in/api/users/2'
    @Test
    public void checkGetMethod() {
        RestAssured.baseURI = "https://reqres.in/api";
        given()
                .log().all()
                .when()
                .get("/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("support.text", containsString("Tired of writing endless social media content?"));
    }

    //curl --location 'https://reqres.in/api/users?page=2
    @Test
    public void checkListOdUsersTest() {
        RestAssured.baseURI = "https://reqres.in/api/";
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("page", 2)
                .when().get("users")
                .then()
                .log().all()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data[2].id", equalTo(9));
    }

    @Test
    @Tag("TEST-123")
    public void checkListOfUsersTest() {
        log.info("INFORMATION");
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/users";

        given()
                .log().uri()
                .queryParam("page", 2)
                .when()
                .get()
                .then()
                .log().body()
                .statusCode(200)
                .body("page", equalTo(2));
    }
}



