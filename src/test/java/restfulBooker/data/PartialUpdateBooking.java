package restfulBooker.data;

public record PartialUpdateBooking(
        int totalprice,
        String additionalneeds
) {
}
