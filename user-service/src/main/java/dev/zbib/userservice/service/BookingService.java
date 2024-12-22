package dev.zbib.userservice.service;

import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;
import dev.zbib.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final UserService userService;

    public EligibilityResponse canBook(Long id) {
        User user = userService.getUserById(id);
        List<String> reasons = new ArrayList<>();
        if (!user.isVerified()) reasons.add("Your account is not verified");
        if (user.getAccountStatus() != AccountStatus.ACTIVE) reasons.add("Your account is not active");
        if (user.getRole() == UserRole.PROVIDER) reasons.add("Providers can't book");
        return EligibilityResponse.builder()
                .eligible(reasons.isEmpty())
                .reasons(reasons)
                .build();
    }


    public EligibilityResponse canBeBooked(Long id) {
        User user = userService.getUserById(id);
        List<String> reasons = new ArrayList<>();
        if (user.getRole() == UserRole.CUSTOMER) reasons.add("User is not a provider");
        if (!user.isVerified()) reasons.add("Provider account is not verified");
        if (user.getAccountStatus() != AccountStatus.ACTIVE) reasons.add("Provider account is not active");

        return EligibilityResponse.builder()
                .eligible(reasons.isEmpty())
                .reasons(reasons)
                .build();
    }
}
