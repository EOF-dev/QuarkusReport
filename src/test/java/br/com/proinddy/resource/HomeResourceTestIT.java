package br.com.proinddy.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class HomeResourceTestIT {

    @Test
    public void testInfo(){
        given()
                .when().get("/relatorio/api/home/info")
                .then()
                .statusCode(200)
                    .body(is("1.0-final"));
    }
}