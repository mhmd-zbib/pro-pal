package dev.zbib.userservice.repository;

import dev.zbib.userservice.dto.response.UserListResponse;
import dev.zbib.userservice.dto.response.UserResponse;
import dev.zbib.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new dev.zbib.userservice.dto.response.UserListResponse(u.id, u.firstName, u.lastName, u.profilePicture) FROM User u")
    Page<UserListResponse> findUserListResponse(Pageable pageable);

    @Query("SELECT new dev.zbib.userservice.dto.response.UserResponse(u.id, u.firstName, u.lastName, u.phoneNumber, u.birthDate, u.profilePicture, u.role, u.status, u.isVerified, u.isBlocked, a) " +
            "FROM User u LEFT JOIN u.address a WHERE u.id = :id")
    UserResponse findUserResponseById(Long id);

    boolean existsByPhoneNumber(String phoneNumber);

}
