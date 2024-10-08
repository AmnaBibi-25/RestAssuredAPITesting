package restfulBooker.tests;

import org.junit.Test;
import restfulBooker.data.BookingData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static restfulBooker.data.TestDataBuilder.getBookingData;
import static restfulBooker.tests.BaseTest.requestSpec;
import static restfulBooker.tests.BaseTest.responseSpec;

public class CreateBookingTest {

    BookingData bookingData = getBookingData();

    @Test
    public void testCreateBooking() {
        given()
                .spec(requestSpec())
                .basePath("/booking")
                .body(this.bookingData)
                .when()
                .post()
                .then()
                .spec(responseSpec())
                .body("bookingid", is(notNullValue()))
                .extract().path("bookingid");
    }

}
