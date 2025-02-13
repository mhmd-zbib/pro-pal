package dev.zbib.bookingservice.dto;

import dev.zbib.bookingservice.enums.BookingStatus;

public class BookingCreationResponse {
    private String bookingId;
    private BookingStatus status;
    private Double totalPrice;
    private String paymentLink;
    private String reference;
}
