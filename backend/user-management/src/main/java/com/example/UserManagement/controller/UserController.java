package com.example.UserManagement.controller;

import com.example.UserManagement.dto.UserRequest;
import com.example.UserManagement.dto.UserResponse;
import com.example.UserManagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>>getAllUsers(){
        List<UserResponse>userResponses=userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse>getUserById(@PathVariable Long id){
        UserResponse userResponses=userService.getUserById(id);
        return ResponseEntity.ok(userResponses);
    }
    @PostMapping("/create")
    public ResponseEntity<UserResponse>createUser(@RequestBody UserRequest userRequest){
        System.out.println(userRequest);
        UserResponse userResponse=userService.createUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        UserResponse updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
