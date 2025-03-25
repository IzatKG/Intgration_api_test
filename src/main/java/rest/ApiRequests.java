package rest;

import config.ConfigProperties;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
@Slf4j
public abstract class ApiRequests {

   public static ConfigProperties config = ConfigFactory.create(ConfigProperties.class);
    private static final String SLASH = "/";
    protected String baseUrl;
    protected Header headers;
    protected RequestSpecification requestSpec;
    protected Response response;

    public ApiRequests(String baseUrl){
        this.baseUrl = baseUrl;
        headers = new Header("Authorization", "Bearer " + config.token() );
        this.requestSpec = given()
                .header(headers)
               // .contentType(ContentType.JSON)
               // .accept(ContentType.JSON)
                .baseUri("https://gorest.co.in/public/v2/");
    }

    public Response post(String endpoint, String body){
        log.info("Send Post {}", endpoint);
        log.info("Body {}", body);

        this.response = given()
                .spec(this.requestSpec)
                .header(headers)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(endpoint);
               // .then()
               // .extract().response();
        GetLogResponse();
        return response;
    }
    public static String getEndpoint(String... args) {
        StringBuilder endPoint = new StringBuilder();
        for (String arg : args) {
            endPoint.append(arg).append(SLASH);
        }
        return endPoint.substring(0, endPoint.length() - 1);
    }
    public void GetLogResponse(){
        log.warn("Response is : {}", response.prettyPrint());
        log.warn("Status code is : {}", response.getStatusCode());
    }
}
