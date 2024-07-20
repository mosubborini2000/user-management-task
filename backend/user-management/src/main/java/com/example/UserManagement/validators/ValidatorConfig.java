//package com.example.UserManagement.validators;
//
//import com.example.UserManagement.dto.UserRequest;
//import com.example.UserManagement.repositories.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ValidatorConfig {
//    private final UserRepository userRepository;
//
//    public ValidatorConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Bean
//    public CompositeValidator<UserRequest, String> userRequestValidator() {
//        return new CompositeValidator<UserRequest, String>()
//                .addValidator(request -> CompositeValidator.hasValue(request.getFirstName()) &&
//                        request.getFirstName().matches("^[a-zA-Z]{1,36}$"), "Invalid first name")
//                .addValidator(request -> CompositeValidator.hasValue(request.getLastName()) &&
//                        request.getLastName().matches("^[a-zA-Z]{1,36}$"), "Invalid last name")
//                .addValidator(request -> CompositeValidator.hasValue(request.getUsername()) &&
//                        request.getUsername().matches("^[a-zA-Z0-9]{1,36}$"), "Invalid username")
//                .addValidator(request -> !userRepository.findByUsername(request.getUsername()).isPresent(),
//                        "Username already exists")
//                .addValidator(request -> CompositeValidator.hasValue(request.getPassword()) &&
//                        request.getPassword().matches("^[a-zA-Z0-9]+$"), "Invalid password");
//    }
//}