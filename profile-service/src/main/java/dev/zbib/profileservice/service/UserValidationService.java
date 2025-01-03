package dev.zbib.profileservice.service;

import dev.zbib.profileservice.dto.request.CreateProfileDTO;
import dev.zbib.profileservice.exception.PhoneNumberAlreadyExistsException;
import dev.zbib.profileservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final ProfileRepository profileRepository;

    protected void validateUserCreation(CreateProfileDTO req) {
        validatePhoneNumber(req.getPhoneNumber());
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (profileRepository.existsByPhoneNumber(phoneNumber)) {
            throw new PhoneNumberAlreadyExistsException(phoneNumber);
        }
    }
}