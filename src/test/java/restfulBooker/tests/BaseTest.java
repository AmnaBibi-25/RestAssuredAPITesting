package restfulBooker.tests;

import com.restassured.config.EnvironmentConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import restfulBooker.data.AuthData;
import restfulBooker.data.BookingData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static restfulBooker.data.TestDataBuilder.getAuthData;
import static restfulBooker.data.TestDataBuilder.getBookingData;

public class BaseTest {

    static {
        RestAssured.filters(new AllureRestAssured());
    }
    protected static RequestSpecification requestSpec() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        String env = System.getProperty("env", "staging");
        EnvironmentConfig.loadEnvironment(env);
        String baseUrl = EnvironmentConfig.get("base.url");

        return requestBuilder.addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri(baseUrl)
                .build();
    }

    protected static ResponseSpecification responseSpec() {
        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        return responseBuilder
                .expectStatusCode(200)
                .build();
    }


    static BookingData bookingData = getBookingData();

    protected static int createBooking() {
        return given()
                .spec(requestSpec())
                .basePath("/booking")
                .body(bookingData)
                .when()
                .post()
                .then()
                .spec(responseSpec())
                .body("bookingid", is(notNullValue()))
                .extract().path("bookingid");

    }

    protected static String generateToken() {
        AuthData authData = getAuthData();
        return given()
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
