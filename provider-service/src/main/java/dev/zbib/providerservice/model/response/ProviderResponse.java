package dev.zbib.providerservice.model.response;

import dev.zbib.providerservice.model.entity.Address;
import dev.zbib.providerservice.model.enums.ServiceType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProviderResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private ServiceType serviceType;
    private double rating;
    private boolean available;
    private double hourlyRate;
    private String serviceArea;
    private Address address;
    private LocalDate birthDate;
    private String phoneNumber;
}
