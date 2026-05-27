package com.hospital.hospitalmanagement.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

public class AuthController {

    @PostMapping("/login")

    public Map<String, String> login(
            @RequestBody Map<String, String> credentials,
            HttpSession session) {

        String username = credentials.get("username");
        String password = credentials.get("password");

        Map<String, String> response = new HashMap<>();

        if(username.equals("admin")
                && password.equals("admin123")) {

            session.setAttribute("user", username);

            response.put("message", "success");

        }

        else {

            response.put("message", "failed");

        }

        return response;

    }

    @GetMapping("/checkSession")

    public Map<String, Boolean> checkSession(
            HttpSession session) {

        Map<String, Boolean> response =
                new HashMap<>();

        response.put("loggedIn",
                session.getAttribute("user") != null);

        return response;

    }

    @PostMapping("/logout")

    public String logout(HttpSession session) {

        session.invalidate();

        return "Logged Out Successfully";

    }

}   