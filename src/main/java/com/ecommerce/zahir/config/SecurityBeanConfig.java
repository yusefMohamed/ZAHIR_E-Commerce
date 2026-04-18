package com.ecommerce.zahir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for security-related beans.
 */

@Configuration
public class SecurityBeanConfig {
    /**
     * Defines the password encoder bean used for hashing passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
}
