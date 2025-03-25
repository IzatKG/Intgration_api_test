import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static endpoints.UserApiEndpoints.USERS;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoRestTest {
    protected static RequestSpecification requestSpecification;
    public ObjectMapper objectMapper = new ObjectMapper();
    public Faker faker = new Faker();
    public static int userId;
    public static String token = "65e15f9cba95109a5ef7e50a810469534312db8898d51c99f587551bbdf52297";

    public static final String SLASH = "/";
    @BeforeAll
    public static void setUp(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2/")
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    @Test
    @Order(0)
    @DisplayName("Create a new user")
    public void createUserGoRest() throws JsonProcessingException {
        Map<String, String> requestBody = new LinkedHashMap<>();
        requestBody.put("name", faker.name().firstName());
        requestBody.put("email", faker.internet().emailAddress());
        requestBody.put("gender", "male");
        requestBody.put("status", "active");

        String jsonBody = objectMapper.writeValueAsString(requestBody);

      userId = given()
                .log().body()
                .spec(requestSpecification)
                .body(jsonBody)
                .when()
                .post(USERS)
                .then()
                .statusCode(201)
                .log().body()
                .extract()
               .jsonPath().getInt("id");

     //  userId = userResponse.getId();

      //  JsonPath jsonPath = userResponse.;
        // userId = jsonPath.getString("id");
    }

    @Test
    @Order(1)
    @DisplayName("Get a new user by userID")
    public void checkGetGoRest() {
        given()
                .log().body()
                .spec(requestSpecification)
                .when()
                .get(USERS + SLASH + userId)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Order(2)
    @DisplayName("Delete a new user by userId")
    public void deleteUserGoRest() {
        given()
                .log().body()
                .spec(requestSpecification)
                .when()
                .delete(USERS + SLASH + userId)
                .then()
                .statusCode(204)
                .log().body();
    }
}

