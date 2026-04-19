package com.airway.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        return (Long) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();
    }

    public static String getCurrentUserEmail()
    {
        JwtUser jwtUser = (JwtUser) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();

        assert jwtUser != null;
        return jwtUser.getEmail();
    }
}