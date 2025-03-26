package controllers;

import io.restassured.response.Response;
import rest.ApiRequests;
import utils.Data;
import utils.JsonConvertor;

import static endpoints.UserApiEndpoints.USERS;

public class UserController extends ApiRequests {
    public UserController(String baseUrl) {
        super(baseUrl);
    }

    public Response createNewUser(){
        return  super.post( getEndpoint(USERS), JsonConvertor.convertJavaObjectTojson(Data.createRequestBody()));
    }
}
