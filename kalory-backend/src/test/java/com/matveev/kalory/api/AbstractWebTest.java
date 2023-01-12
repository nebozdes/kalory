package com.matveev.kalory.api;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = { AbstractWebTest.Initializer.class})
public class AbstractWebTest {

    @LocalServerPort
    private int serverPort;

    @ClassRule
    public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("kalory-db")
            .withUsername("postgres")
            .withPassword("MySuperPassword1!");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgreSQLContainer.start();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    protected String getServiceUrl() {
        return String.format("http://localhost:%s/v1", serverPort);
    }

    protected String getSession(String login) {
        return given()
                .contentType(JSON)
                .body(new JSONObject().put("username", login).put("password", "password").toString())
                .when()
                .post(getServiceUrl() + "/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().sessionId();
    }

    protected JSONObject remove(JSONObject base, String fieldToRemove) {
        final var result = new JSONObject(base.toString());
        result.remove(fieldToRemove);
        return result;
    }
}
