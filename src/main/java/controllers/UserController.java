package controllers;

import entities.request.GoRequestBody;
import io.restassured.response.Response;
import rest.ApiRequests;
import utils.Data;
import utils.JsonConvertor;

import static endpoints.UserApiEndpoints.USERS;
import static utils.Data.createRequestBody;

public class UserController extends ApiRequests {
    public UserController(String baseUrl) {
        super(baseUrl);
    }

    public Response createNewUser(){
        return  super.post( getEndpoint("v2/" + USERS), JsonConvertor.convertJavaObjectTojson(Data.createRequestBody()));
    }

    public static void main(String[] args) {
        System.out.println(config.baseUrl());
    }
}
