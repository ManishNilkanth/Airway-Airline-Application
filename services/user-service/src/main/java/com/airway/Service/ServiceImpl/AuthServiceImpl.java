package com.airway.Service.ServiceImpl;

import com.airway.Mapper.UserMapper;
import com.airway.Repository.UserRepository;
import com.airway.Service.AuthService;
import com.airway.enums.UserRole;
import com.airway.exceptions.InvalidLoginCredentialsException;
import com.airway.exceptions.SystemAdminSignupNotAllowedException;
import com.airway.exceptions.UserExistsWithEmailException;
import com.airway.model.User;
import com.airway.payload.DTO.UserDTO;
import com.airway.payload.reposnse.AuthResponse;
import com.airway.payload.request.LoginRequest;
import com.airway.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse signup(UserDTO request) {

        if(request.getRole() == UserRole.ROLE_SYSTEM_ADMIN)
        {
            throw new SystemAdminSignupNotAllowedException("You can not signup as System admin");
        }
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastLogin(LocalDateTime.now())
                .build();

        User savedUser;
        try
        {
            savedUser = userRepository.save(user);
        }catch(DataIntegrityViolationException exception)
        {
            throw new UserExistsWithEmailException(
                    String.format("User is already exists with email $s",request.getEmail())
            );
        }
        String token = jwtService.generateToken(savedUser);
        return AuthResponse.builder()
                .title("Welcome "+ savedUser.getFullName())
                .token(token)
                .user(UserMapper.mapToUserDTO(savedUser))
                .message("Registered Successfully!")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

        }catch (BadCredentialsException exception)
        {
            throw new InvalidLoginCredentialsException("Invalid login credentials!");
        }
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        User userFromDb = user.get();
        userFromDb.setLastLogin(LocalDateTime.now());
        userRepository.save(userFromDb);

        String token = jwtService.generateToken(userFromDb);

        return AuthResponse.builder()
                .title("Welcome Back "+ userFromDb.getFullName())
                .token(token)
                .user(UserMapper.mapToUserDTO(userFromDb))
                .message("login Successfully!")
                .build();
    }

}
