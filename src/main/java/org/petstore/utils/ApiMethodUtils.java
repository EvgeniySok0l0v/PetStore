package org.petstore.utils;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodUtils {

    @Step("Отправка GET запроса")
    public static Response get(RequestSpecification requestSpec) {
        return given()
                .filter(new AllureRestAssured())
                .spec(requestSpec)
                .get();
    }

    @Step("Отправка GET запроса")
    public static Response post(RequestSpecification requestSpec, String requestBody) {
        return given(requestSpec)
                .filter(new AllureRestAssured())
                .body(requestBody)
                .when()
                .post();
    }

    @Step("Отправка POST запроса")
    public static Response post(RequestSpecification requestSpec, Map<String, Object> requestBody) {
        return given(requestSpec)
                .filter(new AllureRestAssured())
                .body(requestBody)
                .when()
                .post();
    }

    @Step("Отправка POST запроса")
    public static Response post(Map<String, Object> params, RequestSpecification requestSpec) {
        return given(requestSpec)
                .filter(new AllureRestAssured())
                .queryParams(params)
                .when()
                .post();
    }

    @Step("Отправка PUT запроса")
    public static Response put(RequestSpecification requestSpec, Map<String, Object> requestBody) {
        return given()
                .filter(new AllureRestAssured())
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put();
    }

    @Step("Отправка DELETE запроса")
    public static Response delete(RequestSpecification requestSpec) {
        return given()
                .filter(new AllureRestAssured())
                .spec(requestSpec)
                .when()
                .delete();
    }
}
