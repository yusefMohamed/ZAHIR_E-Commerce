package com.ecommerce.zahir.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.User;
import java.util.Optional;

/**
 * Repository for managing dashboard users such as admins and staff members.
 * This repository is mainly used for authentication and user management.
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    /**
     * Finds a user by email address.
     * This is mainly used during dashboard login and security authentication.
     *
     * email the user email
     * return an Optional containing the user if found
     */
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
