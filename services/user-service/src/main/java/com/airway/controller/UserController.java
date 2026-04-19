package com.airway.controller;

import com.airway.Service.UserService;
import com.airway.payload.DTO.UserDTO;
import com.airway.payload.reposnse.ApiResponse;
import com.airway.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile()
    {
        String email = SecurityUtils.getCurrentUserEmail();
        UserDTO response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id)
    {
        UserDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> responses = userService.getAllUsers();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long id)
    {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
