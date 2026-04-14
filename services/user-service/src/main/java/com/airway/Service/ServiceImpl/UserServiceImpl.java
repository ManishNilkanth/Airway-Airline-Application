package com.airway.Service.ServiceImpl;

import com.airway.Mapper.UserMapper;
import com.airway.Repository.UserRepository;
import com.airway.Service.UserService;
import com.airway.exceptions.UserNotFoundException;
import com.airway.model.User;
import com.airway.payload.DTO.UserDTO;
import com.airway.payload.reposnse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException(
                        String.format("User not found with user Id %d",id)
                )
        );
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException(
                        String.format("User not found with email %s",email)
                )
        );
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        if(!userRepository.existsById(id))
        {
            throw new UserNotFoundException(
                    String.format("User not found with email %d",id)
            );
        }
        userRepository.deleteById(id);
        return new ApiResponse("User deleted successfully!");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDTO)
                .toList();
    }
}
