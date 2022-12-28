package com.matveev.kalory.api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class TagControllerTest extends AbstractWebTest {

    @Test
    public void should_create_read_and_delete_tags() {
        final var adminSessionId = getSession("admin");

        // create
        final var id = tryCreateTag(adminSessionId, createBody())
                .statusCode(SC_CREATED)
                .extract().body().jsonPath().getInt("id");

        // read
        given()
                .when()
                .sessionId(adminSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/tag")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(1))
                .body("content.size()", equalTo(1))
                .body("content.get(0).id", equalTo(id))
                .body("content.get(0).name", equalTo("Tag1"));

        // delete
        given()
                .when()
                .sessionId(adminSessionId)
                .delete(getServiceUrl() + "/tag/" + id)
                .then()
                .statusCode(SC_OK);

        // read
        given()
                .when()
                .sessionId(adminSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/tag")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(0))
                .body("content.size()", equalTo(0));
    }

    @Test
    public void should_forbid_access_to_tag_creation_or_deletion_for_client_users() {
        final var clientSessionId = getSession("client");

        tryCreateTag(clientSessionId, createBody())
                .statusCode(HttpStatus.SC_FORBIDDEN);

        given()
                .when()
                .sessionId(clientSessionId)
                .delete(getServiceUrl() + "/tag/1")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void should_not_allow_create_tag_with_empty_or_null_name() {
        final var adminSessionId = getSession("admin");

        tryCreateTag(adminSessionId, createBody().put("name", ""))
                .statusCode(SC_BAD_REQUEST);

        tryCreateTag(adminSessionId, remove(createBody(), "name"))
                .statusCode(SC_BAD_REQUEST);
    }

    private ValidatableResponse tryCreateTag(String sessionId, Object payload) {
        return given()
                .when()
                .sessionId(sessionId)
                .contentType(JSON)
                .body(payload.toString())
                .post(getServiceUrl() + "/tag")
                .then();
    }

    private JSONObject createBody() {
        return new JSONObject().put("name", "Tag1");
    }
}
