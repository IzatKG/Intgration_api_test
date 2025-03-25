import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.request.UserRequestBody;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class CreateUserTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createUserTest(){
        log.info("STARTED POST REQUEST");
        RestAssured.baseURI = "https://regres.in/api/";

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body("{\"name\": \"Meder\", \"job\": \"qa\"}")
                .when()
                .post("users")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void createUserWithMapTest() throws JsonProcessingException {
        Map<String,String> requestBody = new HashMap<>();
        requestBody.put("name", "Meder");
        requestBody.put("job", "qa engineer");

        String jsonBody = objectMapper.writeValueAsString(requestBody);

        RestAssured.baseURI = "https://reqres.in/api/";
       Response response = given()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(jsonBody)
                .when()
                .post("users")
                .then()
                .log().body()
                .statusCode(201)
               .extract()
               .response();


        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.getString("id");
        System.out.println(id);
    }

    @Test
    public void createUserDtoTest() throws JsonProcessingException {
        UserRequestBody userRequestBody = new UserRequestBody("Ermek","AQA");
        String jsonBody = objectMapper.writeValueAsString(userRequestBody);

        RestAssured.baseURI = "https://regres.in/api/";

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(jsonBody)
                .when()
                .post("users")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void createUserWithFileTest() throws JsonProcessingException {
        RestAssured.baseURI = "https://regres.in/api/";

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(new File("C:\\Users\\kerim\\IdeaProjects\\integration_api_autotests\\src\\test\\java\\user.json"))
                .when()
                .post("users")
                .then()
                .log().body()
                .statusCode(201);
    }
}
