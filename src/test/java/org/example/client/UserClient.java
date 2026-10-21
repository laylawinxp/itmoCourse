package org.example.client;

import io.restassured.response.Response;
import org.example.models.CreateUserRequest;

import static io.restassured.RestAssured.given;
import static org.example.specs.Specifications.requestSpec;

public class UserClient {

    public Response getUser(int id) {
        return given().spec(requestSpec())
                .pathParam("id", id)
                .when().get("/users/{id}");
    }

    public Response createUser(CreateUserRequest request) {
        return given().spec(requestSpec())
                .body(request)
                .when().post("/users/add");
    }

    public Response deleteUser(int id) {
        return given().spec(requestSpec())
                .pathParam("id", id)
                .when().delete("/users/{id}");
    }
}