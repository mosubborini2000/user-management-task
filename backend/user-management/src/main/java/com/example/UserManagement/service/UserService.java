package com.example.UserManagement.service;

import com.example.UserManagement.dto.UserRequest;
import com.example.UserManagement.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<UserResponse>getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);


}
