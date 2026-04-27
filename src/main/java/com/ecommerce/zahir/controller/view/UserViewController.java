package com.ecommerce.zahir.controller.view;

import com.ecommerce.zahir.dto.request.CreateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserRequest;
import com.ecommerce.zahir.dto.request.UpdateUserStatusRequest;
import com.ecommerce.zahir.dto.request.UserPasswordChangeRequest;
import com.ecommerce.zahir.dto.response.UserResponse;
import com.ecommerce.zahir.exceptions.BusinessException;
import com.ecommerce.zahir.exceptions.DuplicateResourceException;
import com.ecommerce.zahir.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for dashboard user management pages.
 */

@Controller
@RequestMapping("/dashboard/users")
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Show users list page.
     */

    @GetMapping
    public String usersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "dashboard/users/list";
    }

    /**
     * Show create user form.
     */
    @GetMapping("/create")
    public String createUserPage(Model model) {
        model.addAttribute("user", new CreateUserRequest());
        return "dashboard/users/create";
    }

    /**
     * Handle create user form submission.
     */
    @PostMapping
    public String createUser(@Valid @ModelAttribute("user") CreateUserRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "dashboard/users/create";
        }

        try {
            userService.createUser(request);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully");
            return "redirect:/dashboard/users";

        } catch (BusinessException | DuplicateResourceException ex) {
            bindingResult.reject("createUserError", ex.getMessage());
            return "dashboard/users/create";
        }
    }

    @PostMapping("/{id}/toggle-status")
    public String toggleUserStatus(@PathVariable Long id,
            @RequestParam boolean enabled,
            RedirectAttributes redirectAttributes) {

        try {
            UpdateUserStatusRequest request = new UpdateUserStatusRequest();
            request.setEnabled(enabled);
            request.setAccountNonLocked(enabled);

            userService.updateUserStatus(id, request);

            redirectAttributes.addFlashAttribute("successMessage", "User status updated successfully");

        } catch (BusinessException ex) {

            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }

        return "redirect:/dashboard/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable Long id, Model model) {

        UserResponse userResponse = userService.getUserById(id);

        UpdateUserRequest request = new UpdateUserRequest();
        request.setFirstName(userResponse.getFirstName());
        request.setLastName(userResponse.getLastName());
        request.setEmail(userResponse.getEmail());
        request.setPhone(userResponse.getPhone());
        request.setRole(userResponse.getRole());

        model.addAttribute("userId", id);
        model.addAttribute("user", request);

        return "dashboard/users/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateUser(@PathVariable Long id,
            @Valid @ModelAttribute("user") UpdateUserRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userId", id);
            return "dashboard/users/edit";
        }

        try {
            userService.updateUser(id, request);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
            return "redirect:/dashboard/users";

        } catch (BusinessException | DuplicateResourceException ex) {
            model.addAttribute("userId", id);
            bindingResult.reject("updateUserError", ex.getMessage());
            return "dashboard/users/edit";
        }
    }

    @GetMapping("/{id}/change-password")
    public String changePasswordPage(@PathVariable Long id, Model model) {

        model.addAttribute("userId", id);
        model.addAttribute("passwordRequest", new UserPasswordChangeRequest());

        return "dashboard/users/change-password";
    }

    @PostMapping("/{id}/change-password")
    public String changePassword(@PathVariable Long id,
            @Valid @ModelAttribute("passwordRequest") UserPasswordChangeRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userId", id);
            return "dashboard/users/change-password";
        }

        try {
            userService.changePassword(id, request);
            redirectAttributes.addFlashAttribute("successMessage", "Password changed successfully");
            return "redirect:/dashboard/users";

        } catch (BusinessException ex) {
            model.addAttribute("userId", id);
            bindingResult.reject("passwordError", ex.getMessage());
            return "dashboard/users/change-password";
        }
    }

}
