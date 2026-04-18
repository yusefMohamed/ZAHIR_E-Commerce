package com.ecommerce.zahir.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.zahir.entities.Role;
import com.ecommerce.zahir.entities.User;
import com.ecommerce.zahir.enums.ErrorCode;
import com.ecommerce.zahir.enums.RoleName;
import com.ecommerce.zahir.exceptions.ResourceNotFoundException;
import com.ecommerce.zahir.repos.RoleRepo;
import com.ecommerce.zahir.repos.UserRepo;

@Component
public class AdminUserSeeder implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminUserSeeder(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {

        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String adminEmail = "yusef.mohamed.azmy@gmail.com";
        String adminPhone = "01121218891";
        String rawPassword = "admin123"; // fake password for dev only

        if (!userRepo.existsByEmail(adminEmail)) {

            Role adminRole = roleRepo.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorCode.ROLE_NOT_FOUND,
                            "ROLE_ADMIN not found in database"));
                            
            User admin = new User();
            admin.setFirstName("Youssef");
            admin.setLastName("Mohamed");
            admin.setEmail(adminEmail);
            admin.setPhone(adminPhone);

            // IMPORTANT
            admin.setPassword(passwordEncoder.encode(rawPassword));

            admin.setRole(adminRole);
            admin.setEnabled(true);
            admin.setAccountNonLocked(true);

            userRepo.save(admin);
        }
    }

}
