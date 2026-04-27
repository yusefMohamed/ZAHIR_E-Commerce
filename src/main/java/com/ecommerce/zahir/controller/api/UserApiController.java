package com.ecommerce.zahir.controller.api;

import com.ecommerce.zahir.dto.request.CreateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserStatusRequest;
import com.ecommerce.zahir.dto.request.UserPasswordChangeRequest;
import com.ecommerce.zahir.dto.response.UserResponse;
import com.ecommerce.zahir.dto.response.UserSummaryResponse;
import com.ecommerce.zahir.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing dashboard users.
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Update basic user data.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Update user status (enabled / locked).
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<UserResponse> updateUserStatus(@PathVariable Long id,
                                                         @Valid @RequestBody UpdateUserStatusRequest request) {
        UserResponse response = userService.updateUserStatus(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Change user password.
     */
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                              @Valid @RequestBody UserPasswordChangeRequest request) {
        userService.changePassword(id, request);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get a user by id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all users as lightweight summaries.
     */
    @GetMapping
    public ResponseEntity<List<UserSummaryResponse>> getAllUsers() {
        List<UserSummaryResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    

}
