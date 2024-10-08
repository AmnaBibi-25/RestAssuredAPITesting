package restfulBooker.data;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TestDataBuilder {

    private static final Faker FAKER = new Faker();

    public static BookingData getBookingData() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        BookingDates bookingDates = new BookingDates(
                simpleDateFormat.format(FAKER.date().past(20, TimeUnit.DAYS)),
                simpleDateFormat.format(FAKER.date().future(10, TimeUnit.DAYS))
        );

        return new BookingData(
                FAKER.name().firstName(),
                FAKER.name().lastName(),
                FAKER.number().numberBetween(50, 2000),
                FAKER.bool().bool(),
                bookingDates,
                FAKER.food().dish()
        );
    }

    public static AuthData getAuthData() {
        return new AuthData(
                "admin",
                "password123"
        );
    }

    public static PartialUpdateBooking getPartialUpdateBookingData() {
        return new PartialUpdateBooking(
                FAKER.number().numberBetween(500, 1000),
                FAKER.food().fruit()
        );
    }
}
