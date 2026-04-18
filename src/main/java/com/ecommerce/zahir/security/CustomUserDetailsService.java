package com.ecommerce.zahir.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.zahir.entities.User;
import com.ecommerce.zahir.repos.UserRepo;

/**
 * Loads dashboard users from the database for Spring Security authentication.
 * In this project, email is used as the username during login.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Loads a user by email for authentication.
     *
     * username the email entered during login
     * return UserDetails implementation used by Spring Security
     * throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with email: " + username
                ));

        return new CustomUserDetails(user);
    }
}
