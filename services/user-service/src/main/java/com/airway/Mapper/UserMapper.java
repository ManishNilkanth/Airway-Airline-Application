package com.airway.Mapper;

import com.airway.model.User;
import com.airway.payload.DTO.UserDTO;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user)
    {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .lastLogin(user.getLastLogin())
                .build();
    }
}
