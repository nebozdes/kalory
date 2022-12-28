package com.matveev.kalory.api;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class AuthTest extends AbstractWebTest {

    @Test
    public void should_authorize_correctly_with_admin_password() {
        given()
                .log()
                .all()
                .param("username", "admin")
                .param("password", "password")
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
                .param("username", "admin")
                .param("password", "NotAPassword")
                .when()
                .post(getServiceUrl() + "/login")
                .then()
                .statusCode(SC_FORBIDDEN);
    }
}
