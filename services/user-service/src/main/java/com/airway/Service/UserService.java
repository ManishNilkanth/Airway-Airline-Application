package com.airway.Service;

import com.airway.payload.DTO.UserDTO;
import com.airway.payload.reposnse.ApiResponse;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    ApiResponse deleteUser(Long id);

    List<UserDTO> getAllUsers();
}
