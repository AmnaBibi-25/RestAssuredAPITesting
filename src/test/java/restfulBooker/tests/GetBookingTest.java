package restfulBooker.tests;

import org.testng.annotations.Test;
import restfulBooker.data.BookingData;

import static io.restassured.RestAssured.given;
import static restfulBooker.data.TestDataBuilder.getBookingData;
import static restfulBooker.tests.BaseTest.*;

public class GetBookingTest {

    BookingData bookingData = getBookingData();

    @Test
    public void testGetBooking() {
        int bookingID = createBooking();

        given()
                .spec(requestSpec())
                .basePath("/booking/" + bookingID)
                .when()
                .get()
                .then()
                .spec(responseSpec());
    }
}
