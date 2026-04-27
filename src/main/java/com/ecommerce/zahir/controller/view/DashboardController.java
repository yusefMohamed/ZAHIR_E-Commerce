package com.ecommerce.zahir.controller.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.zahir.service.UserService;

@Controller
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {

        boolean canViewUsers = authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER")
                        || auth.getAuthority().equals("ROLE_ADMIN"));

        if (canViewUsers) {
            model.addAttribute("totalUsers", userService.getAllUsers().size());
        } else {
            model.addAttribute("totalUsers", null);
        }

        return "dashboard/index";
    }
}
