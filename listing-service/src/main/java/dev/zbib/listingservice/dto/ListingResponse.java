package dev.zbib.listingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingResponse {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer reservedStock;

    private String userId;

    private Integer stock;

    private boolean available;

    private String category;

}
