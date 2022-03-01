package com.devpedrod.TestsJunit.services;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.dto.UserDto;

import java.util.List;

public interface IUserService {
    User getById(Long id);
    List<User> getAllUsers();
    User create(UserDto object);
    void findByEmail(UserDto object);
    User update(UserDto object);
}
