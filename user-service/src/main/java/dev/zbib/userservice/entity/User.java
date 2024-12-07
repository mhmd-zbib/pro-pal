package dev.zbib.userservice.entity;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = true)
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @NotNull
    private AccountStatus status;

    private boolean isVerified;

    private boolean isBlocked;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFavorite> favorites;
}
