package com.ecommerce.zahir.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * Seeds default role data when the application starts.
 */

import com.ecommerce.zahir.entities.Role;
import com.ecommerce.zahir.enums.RoleName;
import com.ecommerce.zahir.repos.RoleRepo;

@Component
@Order(1)
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepo roleRepo;

    public RoleSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    /**
     * Creates a role only if it does not already exist.
     */
    private void seedRole(RoleName roleName, String description) {
        if (!roleRepo.existsByName(roleName)) {
            Role role = new Role();
            role.setName(roleName);
            role.setDescription(description);

            roleRepo.save(role);
        }
    }

    @Override
    public void run(String... args) {

        if (!roleRepo.existsByName(RoleName.ROLE_MANAGER)) {

            seedRole(RoleName.ROLE_MANAGER, "Manager with highest dashboard permissions");
        }

        if (!roleRepo.existsByName(RoleName.ROLE_ADMIN)) {

            seedRole(RoleName.ROLE_ADMIN, "Administrator with full dashboard access");
        }

        if (!roleRepo.existsByName(RoleName.ROLE_STAFF)) {

            seedRole(RoleName.ROLE_STAFF, "Staff member with limited dashboard access");
        }
    }

}
