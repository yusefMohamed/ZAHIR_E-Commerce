package com.ecommerce.zahir.service;
import com.ecommerce.zahir.dto.request.CreateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserStatusRequest;
import com.ecommerce.zahir.dto.request.UserPasswordChangeRequest;
import com.ecommerce.zahir.dto.response.UserResponse;
import com.ecommerce.zahir.dto.response.UserSummaryResponse;

import java.util.List;

/**
 * Service interface for managing users.
 */

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Long userId, UpdateUserRequest request);

    UserResponse updateUserStatus(Long userId, UpdateUserStatusRequest request);

    void changePassword(Long userId, UserPasswordChangeRequest request);

    UserResponse getUserById(Long userId);

    List<UserSummaryResponse> getAllUsers();
    
    
}
