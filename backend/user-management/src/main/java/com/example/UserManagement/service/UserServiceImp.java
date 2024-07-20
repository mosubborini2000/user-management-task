package com.example.UserManagement.service;

import com.example.UserManagement.dto.UserRequest;
import com.example.UserManagement.dto.UserResponse;
import com.example.UserManagement.mapper.UserMapper;
import com.example.UserManagement.model.User;
import com.example.UserManagement.repositories.UserRepository;
import com.example.UserManagement.validators.CompositeValidator;
import com.example.UserManagement.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Validator<UserRequest, List<String>> userValidator;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidator = createCompositeValidator();
    }

    private CompositeValidator<UserRequest, String> createCompositeValidator() {
        return new CompositeValidator<UserRequest, String>()
                .addValidator(request -> {
                    boolean result = CompositeValidator.hasValue(request.getFirstName()) &&
                            request.getFirstName().matches("^[a-zA-Z]{1,36}$");
                    if (!result) System.out.println("Invalid first name: " + request.getFirstName());
                    return result;
                }, "Invalid first name")
                .addValidator(request -> {
                    boolean result = CompositeValidator.hasValue(request.getLastName()) &&
                            request.getLastName().matches("^[a-zA-Z]{1,36}$");
                    if (!result) System.out.println("Invalid last name: " + request.getLastName());
                    return result;
                }, "Invalid last name")
                .addValidator(request -> {
                    boolean result = CompositeValidator.hasValue(request.getUserName()) &&
                            request.getUserName().matches("^[a-zA-Z0-9_]{1,36}$");
                    if (!result) System.out.println("Invalid username: " + request.getUserName());
                    return result;
                }, "Invalid username")
                .addValidator(request -> {
                    boolean result = !userRepository.findByUsername(request.getUserName()).isPresent();
                    if (!result) System.out.println("Username already exists: " + request.getUserName());
                    return result;
                }, "Username already exists")
                .addValidator(request -> {
                    boolean result = CompositeValidator.hasValue(request.getPassword()) &&
                            request.getPassword().matches("^[a-zA-Z0-9!@#$%^&*]{8,36}$");
                    if (!result) System.out.println("Invalid password: " + request.getPassword());
                    return result;
                }, "Invalid password");
    }



    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertUserToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .stream()
                .map(userMapper::convertUserToResponse)
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        List<String> violations = userValidator.validate(userRequest);
        CompositeValidator.joinViolations(violations);

        User user = userMapper.convertRequestToUser(userRequest);
        User savedUser = userRepository.save(user);

        return userMapper.convertUserToResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<String> violations = userValidator.validate(userRequest);
        CompositeValidator.joinViolations(violations);
        User user = userMapper.convertRequestToUser(userRequest);
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return userMapper.convertUserToResponse(updatedUser);

    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }
}
