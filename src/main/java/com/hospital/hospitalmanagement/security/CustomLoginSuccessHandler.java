package com.hospital.hospitalmanagement.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomLoginSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override

    public void onAuthenticationSuccess(

            HttpServletRequest request,

            HttpServletResponse response,

            Authentication authentication

    ) throws IOException, ServletException {

        String username =
                authentication.getName();

        /* ADMIN */

        if(username.equals("admin")) {

            response.sendRedirect(
                    "/admin-dashboard.html"
            );

        }

        /* RECEPTION */

        else if(username.equals("reception")) {

            response.sendRedirect(
                    "/reception-dashboard.html"
            );

        }

        /* DOCTOR */

        else if(username.equals("doctor")) {

            response.sendRedirect(
                    "/doctor-dashboard.html"
            );

        }

        /* PHARMACY */

        else if(username.equals("pharmacy")) {

            response.sendRedirect(
                    "/pharmacy-dashboard.html"
            );

        }

        else {

            response.sendRedirect(
                    "/login.html"
            );

        }

    }

}