package dev.zbib.userservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderDetailsListResponse {
    private Long id;
    private String serviceType;
    private double rating;
    private boolean available;
    private double hourlyRate;
    private String serviceArea;
}