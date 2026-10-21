package org.example.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.config.Config;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.BASE_URL)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .setConfig(RestAssuredConfig.config()
                        .logConfig(LogConfig.logConfig()
                                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecStatus(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}