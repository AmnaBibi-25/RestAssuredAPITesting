package restfulBooker.tests;

import org.testng.annotations.Test;
import restfulBooker.data.BookingData;

import static io.restassured.RestAssured.given;
import static restfulBooker.data.TestDataBuilder.getBookingData;
import static restfulBooker.tests.BaseTest.*;

public class UpdateBookingTest {

    @Test
    public void testUpdateBooking() {
        BookingData updateBookingData = getBookingData();

        int bookingID = createBooking();
        String token = generateToken();

        given()
                .spec(requestSpec())
                .basePath("/booking/" + bookingID)
                .header("Cookie", "token=" + token)
                .body(updateBookingData)
                .when()
                .put()
                .then()
                .spec(responseSpec());
    }
}
