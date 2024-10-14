package org.petstore.specs;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class BaseSpec {

    public static RequestSpecification baseRequest = with()
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON);
}
