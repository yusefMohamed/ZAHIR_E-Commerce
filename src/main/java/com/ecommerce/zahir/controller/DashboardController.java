package com.ecommerce.zahir.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "Dashboard page - login successful";
    }
}
