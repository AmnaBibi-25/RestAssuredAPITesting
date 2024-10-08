package restfulBooker.tests;

import org.testng.annotations.Test;
import restfulBooker.data.PartialUpdateBooking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static restfulBooker.data.TestDataBuilder.*;
import static restfulBooker.tests.BaseTest.*;

public class PartialUpdateBookingTest {

    @Test
    public void testPartialUpdateBooking() {
        PartialUpdateBooking partialUpdateBooking = getPartialUpdateBookingData();

        int bookingID = createBooking();
        String token = generateToken();

        given()
                .spec(requestSpec())
                .basePath("/booking/" + bookingID)
                .header("Cookie", "token=" + token)
                .body(partialUpdateBooking)
                .when()
                .patch()
                .then()
                .spec(responseSpec())
                .body("additionalneeds", equalTo(partialUpdateBooking.additionalneeds()));
    }
}
