package com.example.UserManagement.mapper;

import com.example.UserManagement.dto.UserRequest;
import com.example.UserManagement.dto.UserResponse;
import com.example.UserManagement.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse convertUserToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUsername())
                .build();
    }
    public User convertRequestToUser(UserRequest user){
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUserName())
                .password(user.getPassword())
                .build();

    }
}
