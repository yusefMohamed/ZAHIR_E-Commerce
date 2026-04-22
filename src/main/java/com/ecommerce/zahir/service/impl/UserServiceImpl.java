package com.ecommerce.zahir.service.impl;

import com.ecommerce.zahir.dto.request.CreateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserStatusRequest;
import com.ecommerce.zahir.dto.request.UserPasswordChangeRequest;
import com.ecommerce.zahir.dto.response.UserResponse;
import com.ecommerce.zahir.dto.response.UserSummaryResponse;
import com.ecommerce.zahir.entities.Role;
import com.ecommerce.zahir.entities.User;
import com.ecommerce.zahir.enums.ErrorCode;
import com.ecommerce.zahir.enums.RoleName;
import com.ecommerce.zahir.exceptions.BusinessException;
import com.ecommerce.zahir.exceptions.DuplicateResourceException;
import com.ecommerce.zahir.exceptions.ResourceNotFoundException;
import com.ecommerce.zahir.mapper.UserMapper;
import com.ecommerce.zahir.repos.RoleRepo;
import com.ecommerce.zahir.repos.UserRepo;
import com.ecommerce.zahir.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing dashboard users.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepository,
                           RoleRepo roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new dashboard user.
     */
    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    ErrorCode.DUPLICATE_RESOURCE,
                    "Email is already in use"
            );
        }

        User user = UserMapper.toEntity(request);

        RoleName roleName = parseRoleName(request.getRole());

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.ROLE_NOT_FOUND,
                        "Role not found: " + request.getRole()
                ));

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setAccountNonLocked(true);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    /**
     * Updates basic user data.
     */
    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User not found with id: " + userId
                ));

        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    ErrorCode.DUPLICATE_RESOURCE,
                    "Email is already in use"
            );
        }

        RoleName roleName = parseRoleName(request.getRole());

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.ROLE_NOT_FOUND,
                        "Role not found: " + request.getRole()
                ));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(role);

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }

    /**
     * Updates enabled/locked status of a user.
     * If the account is locked, it is automatically disabled.
     */
    @Override
    public void updateUserStatus(Long userId, UpdateUserStatusRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User not found with id: " + userId
                ));

        if (Boolean.FALSE.equals(request.getAccountNonLocked())) {
            user.setAccountNonLocked(false);
            user.setEnabled(false);
        } else {
            user.setAccountNonLocked(true);
            user.setEnabled(Boolean.TRUE.equals(request.getEnabled()));
        }

        userRepository.save(user);
    }

    /**
     * Changes the password of a user.
     */
    @Override
    public void changePassword(Long userId, UserPasswordChangeRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User not found with id: " + userId
                ));

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BusinessException(
                    ErrorCode.INTERNAL_ERROR,
                    "Password confirmation does not match"
            );
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    /**
     * Returns full details of a user by id.
     */
    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ErrorCode.USER_NOT_FOUND,
                        "User not found with id: " + userId
                ));

        return UserMapper.toResponse(user);
    }

    /**
     * Returns all users as lightweight summary responses.
     */
    @Override
    public List<UserSummaryResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toSummary)
                .toList();
    }

    /**
     * Parses and validates role name string into RoleName enum.
     */
    private RoleName parseRoleName(String roleValue) {
        try {
            return RoleName.valueOf(roleValue);
        } catch (IllegalArgumentException ex) {
            throw new BusinessException(
                    ErrorCode.INTERNAL_ERROR,
                    "Invalid role value: " + roleValue
            );
        }
    }
}