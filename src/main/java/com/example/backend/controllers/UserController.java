package com.example.backend.controllers;

import com.example.backend.models.User;
import com.example.backend.services.DTOs.UserWithoutPasswordDto;
import com.example.backend.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user,
                                   HttpServletResponse response) {
        try {
            String token = userService.login(user);
            if (token == null) {
                return new ResponseEntity<>(
                        "Invalid Credentials",
                        HttpStatus.BAD_REQUEST
                );
            }

            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setPath("/");
            tokenCookie.setHttpOnly(true);
            response.addCookie(tokenCookie);
            return new ResponseEntity<>(
                    "User logged in successfully",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(
                    "Login Failed",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User user1 = userService.signup(user);
            if (user1 == null) {
                return new ResponseEntity<>(
                        "User already exists",
                        HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(
                    "User created successfully",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "User Creation Failed",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        try {
            Cookie tokenCookie = WebUtils.getCookie(request, "token");
            assert tokenCookie != null;
            String token = tokenCookie.getValue();
            UserWithoutPasswordDto user = userService.getUserByToken(token);
            if (user == null) {
                return new ResponseEntity<>(
                        "User not found",
                        HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(
                    user,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(
                    "Failed to fetch user",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
