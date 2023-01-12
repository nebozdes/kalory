package com.matveev.kalory.api;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class AuthTest extends AbstractWebTest {

    @Test
    public void should_authorize_correctly_with_admin_password() {
        given()
                .log()
                .all()
                .contentType(JSON)
                .body(new JSONObject().put("username", "admin").put("password", "password").toString())
                .when()
                .post(getServiceUrl() + "/login")
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void should_return_unauthorized_with_incorrect_admin_password() {
        given()
                .log()
                .all()
                .contentType(JSON)
                .body(new JSONObject().put("username", "admin").put("password", "NotAPassword").toString())
                .when()
                .post(getServiceUrl() + "/login")
                .then()
                .statusCode(SC_FORBIDDEN);
    }
}
