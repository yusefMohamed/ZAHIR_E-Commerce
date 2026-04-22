package com.ecommerce.zahir.mapper;

import com.ecommerce.zahir.dto.request.CreateUserRequest;
import com.ecommerce.zahir.dto.response.UserResponse;
import com.ecommerce.zahir.dto.response.UserSummaryResponse;
import com.ecommerce.zahir.entities.User;

/**
 * Mapper class responsible for converting between User entity and DTOs.
 */
public class UserMapper {

    /**
     * Convert CreateUserRequest → User entity
     */
    public static User toEntity(CreateUserRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        //Important: password will be encoded in service layer
        user.setPassword(request.getPassword());

        return user;
    }

    /**
     * Convert User → UserResponse (full details)
     */
    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setEnabled(user.isEnabled());

        // Convert Role enum to String
        response.setRole(user.getRole().getName().name());

        return response;
    }

    /**
     * Convert User → UserSummaryResponse (lightweight)
     */
    public static UserSummaryResponse toSummary(User user) {
        UserSummaryResponse response = new UserSummaryResponse();

        response.setId(user.getId());
        response.setFullName(user.getFirstName() + " " + user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().getName().name());
        response.setEnabled(user.isEnabled());

        return response;
    }
    
}
