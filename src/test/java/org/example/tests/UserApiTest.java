package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import net.datafaker.Faker;
import org.example.client.UserClient;
import org.example.models.CreateUserRequest;
import org.example.models.User;
import org.example.specs.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.example.specs.Specifications.responseSpecOK200;
import static org.example.specs.Specifications.responseSpecStatus;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Owner("QA Engineer")
public class UserApiTest {
    private final Faker faker = new Faker();
    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("GET: Получение пользователя по ID (Positive)")
    @Description("Проверка статус-кода, валидации по JSON Schema и данных в POJO")
    public void testGetUserSuccess() {
        Response response = userClient.getUser(2);

        response.then().spec(responseSpecOK200())
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));

        User user = response.as(User.class);
        assertNotNull(user);
        assertEquals(2, user.getId());
    }

    @Test
    @DisplayName("GET: Пользователь не найден (Negative - 404)")
    public void testGetUserNotFound() {
        Response response = userClient.getUser(9999);

        response.then().spec(responseSpecStatus(404));
    }

    @Test
    @DisplayName("POST: Создание пользователя (Positive)")
    public void testCreateUserSuccess() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        CreateUserRequest request = new CreateUserRequest(firstName, lastName);
        Response response = userClient.createUser(request);

        response.then()
                .spec(responseSpecStatus(201))
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("DELETE: Удаление пользователя (Positive)")
    public void testDeleteUser() {
        Response response = userClient.deleteUser(2);
        response.then().spec(responseSpecStatus(200));
    }

    @Test
    @DisplayName("GET: Проверка граничных значений (Pagination)")
    public void testGetUsersEmptyPage() {
        Response response = io.restassured.RestAssured.given()
                .spec(Specifications.requestSpec())
                .queryParam("limit", 5)
                .queryParam("skip", 1000)
                .when()
                .get("/users");

        response.then()
                .spec(responseSpecOK200())
                .body("users", org.hamcrest.Matchers.empty())
                .body("total", org.hamcrest.Matchers.greaterThan(0));
    }

    @Test
    @DisplayName("GET: Невалидный ID (Negative - 400)")
    public void testGetUserWithInvalidId() {
        Response response = io.restassured.RestAssured.given()
                .spec(Specifications.requestSpec())
                .when()
                .get("/users/abc");

        response.then().statusCode(org.hamcrest.Matchers.anyOf(
                org.hamcrest.Matchers.is(400),
                org.hamcrest.Matchers.is(404)
        ));
    }
}