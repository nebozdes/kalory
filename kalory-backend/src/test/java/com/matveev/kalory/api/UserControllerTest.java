package com.matveev.kalory.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class UserControllerTest extends AbstractWebTest {

    @Test
    public void should_get_information_about_current_user() {
        final var adminSessionId = getSession("admin");

        // create
        given()
                .when()
                .sessionId(adminSessionId)
                .get(getServiceUrl() + "/user/me")
                .then()
                .log()
                .all()
                .statusCode(SC_OK)
                .body("id", notNullValue())
                .body("login", equalTo("admin"))
                .body("status", equalTo("ACTIVE"))
                .body("roles.size()", equalTo(3))
                .body("roles", hasItem("CLIENT"))
                .body("roles", hasItem("MODERATOR"))
                .body("roles", hasItem("ADMINISTRATOR"))
                .body("registrationDate", equalTo("2022-12-15"))
        ;

        final var clientSessionId = getSession("client");

        // create
        given()
                .when()
                .sessionId(clientSessionId)
                .get(getServiceUrl() + "/user/me")
                .then()
                .log()
                .all()
                .statusCode(SC_OK)
                .body("id", notNullValue())
                .body("login", equalTo("client"))
                .body("status", equalTo("ACTIVE"))
                .body("roles.size()", equalTo(1))
                .body("roles", hasItem("CLIENT"))
                .body("registrationDate", equalTo("2022-12-15"))
        ;
    }
}
