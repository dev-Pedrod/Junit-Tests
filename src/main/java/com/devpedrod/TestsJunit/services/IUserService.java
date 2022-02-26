package com.devpedrod.TestsJunit.services;

import com.devpedrod.TestsJunit.domain.User;

public interface IUserService {
    User getById(Long id);
}
