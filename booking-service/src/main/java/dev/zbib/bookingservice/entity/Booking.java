package dev.zbib.bookingservice.entity;

import dev.zbib.shared.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "bookings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private UUID id;
    private String eventId;
    private String userId;
    private String venueId;
    private String reference;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private String paymentStatus;
    private Double totalPrice;
    private String notes;
} 