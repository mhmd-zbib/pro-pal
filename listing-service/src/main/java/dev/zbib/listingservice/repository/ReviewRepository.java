package dev.zbib.listingservice.repository;

import dev.zbib.listingservice.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Page<Review> findByListingId(String listingId, Pageable pageable);
}
