package com.hospital.hospitalmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

    /* USERS */

    @Bean

    public InMemoryUserDetailsManager userDetailsService() {

        /* ADMIN */

        UserDetails admin =

                User.builder()

                        .username("admin")

                        .password("{noop}admin123")

                        .roles("ADMIN")

                        .build();

        /* RECEPTION */

        UserDetails reception =

                User.builder()

                        .username("reception")

                        .password("{noop}reception123")

                        .roles("RECEPTION")

                        .build();

        /* DOCTOR */

        UserDetails doctor =

                User.builder()

                        .username("doctor")

                        .password("{noop}doctor123")

                        .roles("DOCTOR")

                        .build();

        /* PHARMACY */

        UserDetails pharmacy =

                User.builder()

                        .username("pharmacy")

                        .password("{noop}pharmacy123")

                        .roles("PHARMACY")

                        .build();

        return new InMemoryUserDetailsManager(

                admin,
                reception,
                doctor,
                pharmacy

        );

    }

    /* SECURITY */

    @Bean

    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            /* DISABLE CSRF */

            .csrf(csrf -> csrf.disable())

            /* ROUTE AUTHORIZATION */

            .authorizeHttpRequests(auth -> auth

                /* PUBLIC PAGES */

                .requestMatchers(

                        "/",
                        "/index.html",
                        "/login.html"

                ).permitAll()

                /* ADMIN ONLY */

                .requestMatchers(

                        "/admin-dashboard.html",
                        "/restore-dashboard.html",
                        "/backupDatabase",
                        "/auditLogs"

                ).hasRole("ADMIN")

                /* RECEPTION */

                .requestMatchers(

                        "/reception-dashboard.html"

                ).hasRole("RECEPTION")

                /* DOCTOR */

                .requestMatchers(

                        "/doctor-dashboard.html"

                ).hasRole("DOCTOR")

                /* PHARMACY */

                .requestMatchers(

                        "/pharmacy-dashboard.html"

                ).hasRole("PHARMACY")

                /* ALL OTHER REQUESTS */

                .anyRequest().authenticated()

            )

            /* LOGIN */

            .formLogin(Customizer.withDefaults())

            /* LOGOUT */

            .logout(logout -> logout

                    .logoutSuccessUrl("/login.html")

                    .permitAll()

            );

        return http.build();

    }

}