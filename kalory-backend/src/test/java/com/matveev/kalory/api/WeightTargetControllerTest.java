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

public class WeightTargetControllerTest extends AbstractWebTest {

    @Test
    public void should_create_read_and_delete_weight_targets() {
        final var clientSessionId = getSession("client");

        // create
        final var id = tryCreateWeightTarget(clientSessionId, createBody())
                .statusCode(SC_CREATED)
                .extract().body().jsonPath().getInt("id");

        // read
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/weight-target")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(1))
                .body("content.size()", equalTo(1))
                .body("content.get(0).id", equalTo(id))
                .body("content.get(0).value", equalTo(60f))
                .body("content.get(0).deadline", equalTo("2022-12-25"))
        ;

        // delete
        given()
                .when()
                .sessionId(clientSessionId)
                .delete(getServiceUrl() + "/weight-target/" + id)
                .then()
                .statusCode(SC_OK);

        // read
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/weight-target")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(0))
                .body("content.size()", equalTo(0));
    }

    @Test
    public void should_not_allow_create_weight_target_without_required_fields() {
        final var clientSessionId = getSession("client");

        tryCreateWeightTarget(clientSessionId, remove(createBody(), "value"))
                .statusCode(SC_BAD_REQUEST);

        tryCreateWeightTarget(clientSessionId, createBody().put("value", 0))
                .statusCode(SC_BAD_REQUEST);

        tryCreateWeightTarget(clientSessionId, createBody().put("value", -1))
                .statusCode(SC_BAD_REQUEST);
    }

    private ValidatableResponse tryCreateWeightTarget(String sessionId, Object payload) {
        return given()
                .when()
                .log().all()
                .sessionId(sessionId)
                .contentType(JSON)
                .body(payload.toString())
                .post(getServiceUrl() + "/weight-target")
                .then()
                .log().all();
    }

    private JSONObject createBody() {
        return new JSONObject()
                .put("value", 60)
                .put("deadline", "2022-12-25");
    }
}
