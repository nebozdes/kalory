package com.matveev.kalory.api;

import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class ConsumedProductControllerTest extends AbstractWebTest {

    @Test
    public void should_create_read_and_delete_products() {
        final var clientSessionId = getSession("client");

        // create
        final var id = tryCreateConsumedProduct(clientSessionId, createBody())
                .extract().body().jsonPath().getInt("id");

        final var yesterday = now().minus(1, DAYS).toString();

        // create one with yesterday's date
        final var yesterdayId = tryCreateConsumedProduct(clientSessionId, createBody()
                .put("consumptionDate", yesterday))
                .extract().body().jsonPath().getInt("id");

        // read only for yesterday
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .param("consumptionDate", yesterday)
                .get(getServiceUrl() + "/consumed-product")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(1))
                .body("numberOfElements", equalTo(1))
                .body("content.size()", equalTo(1))
                .body("content.get(0).id", equalTo(yesterdayId))
                .body("content.get(0).productId", equalTo(1))
                .body("content.get(0).consumptionDate", equalTo(yesterday))
                .body("content.get(0).consumptionAmount", equalTo(200f))
                .body("content.get(0).comment", equalTo("Some comment"))
                .body("content.get(0).calculatedCalories", equalTo(758f))
                .body("content.get(0).calculatedCarbs", equalTo(136f))
                .body("content.get(0).calculatedLipids", equalTo(14f))
                .body("content.get(0).calculatedProteins", equalTo(26f));

        // read all
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/consumed-product")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(2))
                .body("content.size()", equalTo(2))
                .body("content.get(0).id", equalTo(id))
                .body("content.get(1).id", equalTo(yesterdayId))
        ;

        // delete today's
        given()
                .when()
                .sessionId(clientSessionId)
                .delete(getServiceUrl() + "/consumed-product/" + id)
                .then()
                .statusCode(SC_OK);

        // delete yesterday's
        given()
                .when()
                .sessionId(clientSessionId)
                .delete(getServiceUrl() + "/consumed-product/" + yesterdayId)
                .then()
                .statusCode(SC_OK);

        // read
        given()
                .when()
                .sessionId(clientSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/consumed-product")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(0))
                .body("content.size()", equalTo(0));
    }

    @Test
    public void should_not_allow_create_consumed_product_with_incorrect_values() {
        final var clientSessionId = getSession("client");

        tryCreateConsumedProduct(clientSessionId, remove(createBody(), "consumptionDate"))
                .statusCode(SC_BAD_REQUEST);

        tryCreateConsumedProduct(clientSessionId, remove(createBody(), "productId"))
                .statusCode(SC_BAD_REQUEST);
        
        tryCreateConsumedProduct(clientSessionId, createBody().put("productId", -1))
                .statusCode(SC_BAD_REQUEST);

        tryCreateConsumedProduct(clientSessionId, createBody().put("productId", 25))
                .statusCode(SC_BAD_REQUEST);
    }

    private ValidatableResponse tryCreateConsumedProduct(String sessionId, Object payload) {
        return given()
                .when()
                .sessionId(sessionId)
                .contentType(JSON)
                .body(payload.toString())
                .post(getServiceUrl() + "/consumed-product")
                .then();
    }

    private JSONObject createBody() {
        return new JSONObject()
                .put("productId", 1)
                .put("consumptionDate", now().toString())
                .put("consumptionAmount", 200)
                .put("comment", "Some comment");
    }
}
