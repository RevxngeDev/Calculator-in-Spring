package com.example.calculator.services;

import com.example.calculator.dto.UserDto;
import com.example.calculator.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserDto> getAllUsers();
    Optional<User> getUserWithCalculations(Long userId);
}
