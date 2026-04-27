package com.ecommerce.zahir.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

/**
 * Main Spring Security configuration for the application.
 * Protects dashboard routes while keeping customer-facing routes public.
 */

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

        private final CustomUserDetailsService customUserDetailsService;
        private final PasswordEncoder passwordEncoder;

        public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                        PasswordEncoder passwordEncoder) {
                this.customUserDetailsService = customUserDetailsService;
                this.passwordEncoder = passwordEncoder;
        }

        /**
         * Defines the main security filter chain.
         */

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth

                                                // User management is admin-only
                                                .requestMatchers("/dashboard/users/create").hasRole("MANAGER")
                                                .requestMatchers("/dashboard/users/**").hasAnyRole("MANAGER", "ADMIN")
                                                .requestMatchers("/api/admin/users/**").hasAnyRole("MANAGER", "ADMIN")

                                                // Temporary: allow other APIs during development/testing
                                                .requestMatchers("/api/**").permitAll()
                                                // Public customer-facing routes
                                                .requestMatchers(
                                                                "/",
                                                                "/home",
                                                                "/about",
                                                                "/contact",
                                                                "/perfumes/**",
                                                                "/checkout/**",
                                                                "/css/**",
                                                                "/js/**",
                                                                "/images/**")
                                                .permitAll()

                                                // Protected dashboard routes
                                                .requestMatchers("/dashboard/**")
                                                .hasAnyRole("MANAGER", "ADMIN", "STAFF")

                                                // Any other request requires authentication
                                                .anyRequest().authenticated())
                                // Use Spring Security default login page
                                .formLogin(form -> form
                                                .defaultSuccessUrl("/dashboard", true)
                                                .permitAll())
                                // Default logout behavior
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID"));

                return http.build();
        }

        /**
         * Connects Spring Security authentication with our database user service.
         */
        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder);
                return authProvider;
        }

}
