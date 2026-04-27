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
import com.ecommerce.zahir.security.CustomUserDetails;
import com.ecommerce.zahir.service.UserService;

import jakarta.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing dashboard users.
 */
@Service
@Transactional
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
        @PreAuthorize("hasRole('MANAGER')")
        @Override
        public UserResponse createUser(CreateUserRequest request) {

                String normalizedEmail = request.getEmail().trim().toLowerCase();
                if (userRepository.existsByEmail(normalizedEmail)) {
                        throw new DuplicateResourceException(
                                        ErrorCode.DUPLICATE_RESOURCE,
                                        "Email is already in use");
                }

                User user = UserMapper.toEntity(request);

                RoleName roleName = parseRoleName(request.getRole());

                Role role = roleRepository.findByName(roleName)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                ErrorCode.ROLE_NOT_FOUND,
                                                "Role not found: " + request.getRole()));

                user.setRole(role);
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setEnabled(true);
                user.setAccountNonLocked(true);
                user.setEmail(normalizedEmail);

                User savedUser = userRepository.save(user);

                return UserMapper.toResponse(savedUser);
        }

        /**
         * Updates basic user data.
         */
        @Override
        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        public UserResponse updateUser(Long userId, UpdateUserRequest request) {

                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                ErrorCode.USER_NOT_FOUND,
                                                "User not found with id: " + userId));

                String normalizedEmail = request.getEmail().trim().toLowerCase();
                String currentEmail = user.getEmail().trim().toLowerCase();

                if (!currentEmail.equals(normalizedEmail)
                                && userRepository.existsByEmail(normalizedEmail)) {
                        throw new DuplicateResourceException(
                                        ErrorCode.DUPLICATE_RESOURCE,
                                        "Email is already in use");
                }

                RoleName requestedRoleName = parseRoleName(request.getRole());
                RoleName currentUserRoleName = user.getRole().getName();

                boolean roleChanged = currentUserRoleName != requestedRoleName;

                if (roleChanged && !isCurrentUserManager()) {
                        throw new BusinessException(
                                        ErrorCode.UNAUTHORIZED_ACTION,
                                        "Only manager can change user roles");
                }

                Role role = roleRepository.findByName(requestedRoleName)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                ErrorCode.ROLE_NOT_FOUND,
                                                "Role not found: " + request.getRole()));

                user.setFirstName(request.getFirstName());
                user.setLastName(request.getLastName());
                user.setEmail(normalizedEmail);
                user.setPhone(request.getPhone());
                user.setRole(role);

                User updatedUser = userRepository.save(user);

                return UserMapper.toResponse(updatedUser);
        }

        /**
         * Updates enabled/locked status of a user.
         * If the account is locked, it is automatically disabled.
         */
        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        @Override
        public UserResponse updateUserStatus(Long userId, UpdateUserStatusRequest request) {

                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                ErrorCode.USER_NOT_FOUND,
                                                "User not found with id: " + userId));

                User currentUser = getCurrentAuthenticatedUser();

                if (currentUser.getId().equals(user.getId())) {
                        throw new BusinessException(
                                        ErrorCode.SELF_DEACTIVATION_NOT_ALLOWED,
                                        "You cannot deactivate your own account");
                }

                if (user.getRole().getName() == RoleName.ROLE_MANAGER) {
                        throw new BusinessException(
                                        ErrorCode.MANAGER_ROLE_PROTECTED,
                                        "Manager account cannot be deactivated");
                }

                if (Boolean.FALSE.equals(request.getAccountNonLocked())) {
                        user.setAccountNonLocked(false);
                        user.setEnabled(false);
                } else {
                        user.setAccountNonLocked(true);
                        user.setEnabled(Boolean.TRUE.equals(request.getEnabled()));
                }

                userRepository.save(user);
                return UserMapper.toResponse(user);
        }

        /**
         * Changes the password of a user.
         */
        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        @Override
        public void changePassword(Long userId, UserPasswordChangeRequest request) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                ErrorCode.USER_NOT_FOUND,
                                                "User not found with id: " + userId));

                if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                        throw new BusinessException(
                                        ErrorCode.PASSWORD_MISMATCH,
                                        "Password confirmation does not match");
                }

                user.setPassword(passwordEncoder.encode(request.getNewPassword()));

                userRepository.save(user);
        }

        /**
         * Returns full details of a user by id.
         */
        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        @Override
        public UserResponse getUserById(Long userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                ErrorCode.USER_NOT_FOUND,
                                                "User not found with id: " + userId));

                return UserMapper.toResponse(user);
        }

        /**
         * Returns all users as lightweight summary responses.
         */
        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
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
                        return RoleName.valueOf(roleValue.trim().toUpperCase());
                } catch (IllegalArgumentException ex) {
                        throw new BusinessException(
                                        ErrorCode.INVALID_ROLE,
                                        "Invalid role value: " + roleValue);
                }
        }

        private User getCurrentAuthenticatedUser() {
                Object principal = SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getPrincipal();

                if (principal instanceof CustomUserDetails customUserDetails) {
                        return customUserDetails.getUser();
                }

                throw new BusinessException(
                                ErrorCode.UNAUTHORIZED_ACTION,
                                "Authenticated user not found");
        }

        private boolean isCurrentUserManager() {
                Object principal = SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getPrincipal();

                if (principal instanceof CustomUserDetails customUserDetails) {
                        return customUserDetails.getUser()
                                        .getRole()
                                        .getName() == RoleName.ROLE_MANAGER;
                }

                return false;
        }
}