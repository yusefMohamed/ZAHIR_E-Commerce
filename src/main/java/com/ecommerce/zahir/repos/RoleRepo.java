package com.ecommerce.zahir.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.zahir.entities.Role;
import com.ecommerce.zahir.enums.RoleName;

/**
 * Repository for managing role data.
 * Used to query and persist dashboard roles such as ADMIN and STAFF.
 */

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    
    /**
     * Finds a role by its enum name.
     *
     * the role name (e.g. ROLE_ADMIN, ROLE_STAFF)
     * return an Optional containing the role if found
     */
    Optional<Role> findByName(RoleName name);
}
