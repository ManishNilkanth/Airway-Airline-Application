package com.airway.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "email is required to login")
    private String email;

    @NotBlank(message = "password is required to login")
    private String password;
}
