package dev.zbib.profileservice.entity;

import dev.zbib.profileservice.exception.ExceptionMessages;
import dev.zbib.shared.entity.Address;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Profile {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @NotNull(message = ExceptionMessages.BIRTH_DATE_REQUIRED)
    @Past(message = ExceptionMessages.INVALID_BIRTH_DATE)
    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull(message = ExceptionMessages.ADDRESS_REQUIRED)
    @Embedded
    private Address address;

    @NotNull
    private AccountStatus accountStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
