package com.ecommerce.zahir.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.zahir.entities.User;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of Spring Security's UserDetails.
 * Wraps the application's User entity and exposes the data
 * required by Spring Security during authentication and authorization.
 */

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the user's granted authorities.
     * In this project, each dashboard user has a single role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().getName().name()));
    }

    /**
     * Returns the encrypted password stored in the database.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used for authentication.
     * In this project, email is used as the login username.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

     /**
     * Indicates whether the account is non-expired.
     * We keep this always true for now.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

     @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE.equals(user.isAccountNonLocked());
    }

     /**
     * Indicates whether the credentials are non-expired.
     * We keep this always true for now.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.isEnabled());
    }

    /**
     * Exposes the wrapped User entity when needed internally.
     */
    public User getUser() {
        return user;
    }
    
}
