package com.devpedrod.TestsJunit.services;

import com.devpedrod.TestsJunit.domain.User;

import java.util.List;

public interface IUserService {
    User getById(Long id);
    List<User> getAllUsers();
}
