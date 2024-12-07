package dev.zbib.bookingservice.client;

import dev.zbib.shared.dto.UserDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    
    @GetMapping("/users/{userId}/details")
    UserDetailsResponse getUserDetails(@PathVariable("userId") Long userId);
}