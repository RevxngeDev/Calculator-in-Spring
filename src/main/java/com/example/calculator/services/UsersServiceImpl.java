package com.example.calculator.services;

import com.example.calculator.dto.UserDto;
import com.example.calculator.models.User;
import com.example.calculator.repositores.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.example.calculator.dto.UserDto.userList;

@Component
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userList(usersRepository.findAll());
    }

    @Override
    public Optional<User> getUserWithCalculations(Long userId) {
        return usersRepository.findById(userId);
    }


}
