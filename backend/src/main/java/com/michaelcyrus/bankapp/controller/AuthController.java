package com.michaelcyrus.bankapp.controller;

import com.michaelcyrus.bankapp.dto.AuthResponse;
import com.michaelcyrus.bankapp.dto.LoginRequest;
import com.michaelcyrus.bankapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author Michael Cyrus Jr
 **/

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerService customerService;

    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return customerService.login(request);
    }
}
