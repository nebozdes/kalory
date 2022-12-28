package com.matveev.kalory.api;

import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class WeightCheckControllerTest extends AbstractWebTest {

    @Test
    public void should_create_read_and_delete_weight_checks() {
        final var clientSessionId = getSession("client");

        // create
        final var id = tryCreateWeightCheck(clientSessionId, createBody())
                .statusCode(SC_CREATED)
                .extract().body().jsonPath().getInt("id");

        // read
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/weight-check")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(1))
                .body("content.size()", equalTo(1))
                .body("content.get(0).id", equalTo(id))
                .body("content.get(0).value", equalTo(60f))
                .body("content.get(0).checkTime", equalTo("2022-12-25T00:00:00Z"))
        ;

        // delete
        given()
                .when()
                .sessionId(clientSessionId)
                .delete(getServiceUrl() + "/weight-check/" + id)
                .then()
                .statusCode(SC_OK);

        // read
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/weight-check")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(0))
                .body("content.size()", equalTo(0));
    }

    @Test
    public void should_not_allow_create_weight_check_without_required_fields() {
        final var clientSessionId = getSession("client");

        tryCreateWeightCheck(clientSessionId, remove(createBody(), "checkTime"))
                .statusCode(SC_BAD_REQUEST);

        tryCreateWeightCheck(clientSessionId, remove(createBody(), "value"))
                .statusCode(SC_BAD_REQUEST);

        tryCreateWeightCheck(clientSessionId, createBody().put("value", 0))
                .statusCode(SC_BAD_REQUEST);

        tryCreateWeightCheck(clientSessionId, createBody().put("value", -1))
                .statusCode(SC_BAD_REQUEST);
    }

    private ValidatableResponse tryCreateWeightCheck(String sessionId, Object payload) {
        return given()
                .when()
                .sessionId(sessionId)
                .contentType(JSON)
                .body(payload.toString())
                .post(getServiceUrl() + "/weight-check")
                .then();
    }

    private JSONObject createBody() {
        return new JSONObject()
                .put("value", 60)
                .put("checkTime", "2022-12-25T00:00:00Z");
    }
}
