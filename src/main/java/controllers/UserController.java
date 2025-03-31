package controllers;

import entities.response.UserResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import rest.ApiRequests;
import utils.Data;
import utils.JsonConvertor;

import static endpoints.UserApiEndpoints.USER;
import static endpoints.UserApiEndpoints.USERS;

public class UserController extends ApiRequests {
    public UserController(String baseUrl) {
        super(baseUrl);
    }

    @Step("Create a new user")
    public Response createNewUser() {
        return super.post(getEndpoint(USERS), JsonConvertor.convertJavaObjectTojson(Data.createRequestBody()));
    }

    @Step("Get a new user")
    public Response getNewUser() {
        return super.get(getEndpoint(USERS));
    }
    @Step("Get a new user by id")
    public Response getNewUserById(int id) {
        return super.get(getEndpoint(USER + id));
    }
}
