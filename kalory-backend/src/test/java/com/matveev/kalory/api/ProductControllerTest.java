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

public class ProductControllerTest extends AbstractWebTest {

    @Test
    public void should_create_read_and_delete_products() {
        final var adminSessionId = getSession("admin");

        // create
        final var id = tryCreateProduct(adminSessionId, createBody())
                .statusCode(SC_CREATED)
                .extract().body().jsonPath().getInt("id");

        // read
        given()
                .when()
                .sessionId(adminSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/product")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(4))
                .body("content.size()", equalTo(4))
                .body("content.get(3).id", equalTo(id))
                .body("content.get(3).name", equalTo("Product 1"))
                .body("content.get(3).baseAmount", equalTo(100f))
                .body("content.get(3).baseAmountType", equalTo("MILLI"))
                .body("content.get(3).baseCalories", equalTo(150f))
                .body("content.get(3).baseCarbs", equalTo(1f))
                .body("content.get(3).baseLipids", equalTo(2f))
                .body("content.get(3).baseProteins", equalTo(3f))
        ;

        // delete
        given()
                .when()
                .sessionId(adminSessionId)
                .delete(getServiceUrl() + "/product/" + id)
                .then()
                .statusCode(SC_OK);

        // read
        given()
                .when()
                .sessionId(adminSessionId)
                .param("page", 0)
                .param("limit", 10)
                .get(getServiceUrl() + "/product")
                .then()
                .statusCode(SC_OK)
                .body("size", equalTo(10))
                .body("numberOfElements", equalTo(3))
                .body("content.size()", equalTo(3));
    }

    @Test
    public void should_forbid_access_to_product_creation_or_deletion_for_client_users() {
        final var clientSessionId = getSession("client");

        tryCreateProduct(clientSessionId, createBody())
                .statusCode(HttpStatus.SC_FORBIDDEN);

        given()
                .when()
                .sessionId(clientSessionId)
                .delete(getServiceUrl() + "/product/1")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void should_not_allow_create_product_with_empty_or_null_name() {
        final var adminSessionId = getSession("admin");

        tryCreateProduct(adminSessionId, createBody().put("name", ""))
                .statusCode(SC_BAD_REQUEST);

        tryCreateProduct(adminSessionId, remove(createBody(), "name"))
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    public void should_not_allow_create_product_with_incorrect_base_values() {
        final var adminSessionId = getSession("admin");

        // base calories
        tryCreateProduct(adminSessionId, createBody().put("baseCalories", 0))
                .statusCode(SC_BAD_REQUEST);

        tryCreateProduct(adminSessionId, createBody().put("baseCalories", -1))
                .statusCode(SC_BAD_REQUEST);

        // base amount
        tryCreateProduct(adminSessionId, createBody().put("baseAmount", 0))
                .statusCode(SC_BAD_REQUEST);

        tryCreateProduct(adminSessionId, createBody().put("baseAmount", -1))
                .statusCode(SC_BAD_REQUEST);

        // base carbs
        tryCreateProduct(adminSessionId, createBody().put("baseCarbs", -1))
                .statusCode(SC_BAD_REQUEST);

        // base lipids
        tryCreateProduct(adminSessionId, createBody().put("baseLipids", -1))
                .statusCode(SC_BAD_REQUEST);

        // base lipids
        tryCreateProduct(adminSessionId, createBody().put("baseProteins", -1))
                .statusCode(SC_BAD_REQUEST);
    }

    private ValidatableResponse tryCreateProduct(String sessionId, Object payload) {
        return given()
                .when()
                .sessionId(sessionId)
                .contentType(JSON)
                .body(payload.toString())
                .post(getServiceUrl() + "/product")
                .then();
    }

    private JSONObject createBody() {
        return new JSONObject()
                .put("name", "Product 1")
                .put("baseAmount", 100)
                .put("baseAmountType", "MILLI")
                .put("baseCalories", 150)
                .put("baseCarbs", 1)
                .put("baseLipids", 2)
                .put("baseProteins", 3);
    }
}
