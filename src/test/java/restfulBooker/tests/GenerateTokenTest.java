package restfulBooker.tests;

import org.testng.annotations.Test;
import restfulBooker.data.AuthData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static restfulBooker.tests.BaseTest.requestSpec;
import static restfulBooker.tests.BaseTest.responseSpec;
import static restfulBooker.data.TestDataBuilder.getAuthData;

public class GenerateTokenTest {


    @Test
    public void testTokenGeneration() {
        AuthData authData = getAuthData();
        given()
                .spec(requestSpec())
                .basePath("/auth")
                .body(authData)
                .when()
                .post()
                .then()
                .spec(responseSpec())
                .body("token", is(notNullValue()))
                .extract().path("token");
    }
}
