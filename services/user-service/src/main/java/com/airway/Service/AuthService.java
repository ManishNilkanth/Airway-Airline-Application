package com.airway.Service;

import com.airway.payload.DTO.UserDTO;
import com.airway.payload.reposnse.AuthResponse;
import com.airway.payload.request.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse signup(UserDTO request);
}
