package com.airway.payload.DTO;

import com.airway.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    Long id;

    private String fullName;

    private String email;

    private String phone;

    private String password;

    private UserRole role;

    private LocalDateTime lastLogin;
}
