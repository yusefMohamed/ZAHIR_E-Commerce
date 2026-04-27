package com.ecommerce.zahir.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
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
@Order(2)
public class ManagerUserSeeder implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public ManagerUserSeeder(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {

        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String managerEmail = "yusef.mohamed.azmy@gmail.com";
        String managerPhone = "01121218891";
        String rawPassword = "123456"; // fake password for dev only

        if (!userRepo.existsByEmail(managerEmail)) {

            Role managerRole = roleRepo.findByName(RoleName.ROLE_MANAGER)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            ErrorCode.ROLE_NOT_FOUND,
                            "ROLE_MANAGER not found in database"));
                            
            User manager = new User();
            manager.setFirstName("Youssef");
            manager.setLastName("Mohamed");
            manager.setEmail(managerEmail);
            manager.setPhone(managerPhone);

            // IMPORTANT
            manager.setPassword(passwordEncoder.encode(rawPassword));

            manager.setRole(managerRole);
            manager.setEnabled(true);
            manager.setAccountNonLocked(true);

            userRepo.save(manager);
        }
    }

}
