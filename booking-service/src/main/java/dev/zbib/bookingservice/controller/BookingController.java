package dev.zbib.bookingservice.controller;

import dev.zbib.bookingservice.dto.request.CreateDirectBookingRequest;
import dev.zbib.bookingservice.dto.request.CreateOpenBookingRequest;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/direct")
    public void createDirectBooking(@RequestBody CreateDirectBookingRequest req) {
        bookingService.createDirectBooking(req);
    }

    @PostMapping("/open")
    public void createOpenBooking(@RequestBody CreateOpenBookingRequest req) {

    }

    @GetMapping
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public void updateBooking(
            // @RequestBody UpdateBookingRequest req,
            @PathVariable Long id) {
        // cal the booking and update the info
        // return its info
    }

    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

    @PatchMapping("/{id}/accept")
    public void acceptBooking(
            @PathVariable Long id) {
        bookingService.acceptBooking(id);
    }

    @PatchMapping("/{id}/decline")
    public void declineBooking(
            @PathVariable Long id,
            @RequestBody String reason) {
        bookingService.declineBooking(id, reason);
    }
}
