package com.example.blabladem.business.service.impl;

import com.example.blabladem.repository.UserRepository;
import com.example.blabladem.business.service.UserService;
import com.example.blabladem.domain.User;
import com.example.blabladem.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new BadRequestException("Such user does not exist!"));
    }
}
