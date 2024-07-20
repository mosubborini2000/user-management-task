package com.example.UserManagement.validators;

public interface Validator<P, R> {
    R validate(P input);
}
