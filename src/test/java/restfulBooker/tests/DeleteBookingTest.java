package restfulBooker.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static restfulBooker.tests.BaseTest.*;

public class DeleteBookingTest {

    @Test
    public void testDeleteBooking() {
        int bookingID = createBooking();
        String token = generateToken();

        given()
                .spec(requestSpec())
                .basePath("/booking/" + bookingID)
                .header("Cookie", "token=" + token)
                .when()
                .delete()
                .then()
                .statusCode(201);

        given()
                .spec(requestSpec())
                .basePath("/booking/" + bookingID)
                .when()
                .get()
                .then()
                .statusCode(404);
    }
}
