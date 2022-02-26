package com.devpedrod.TestsJunit.services.impl;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.repository.UserRepository;
import com.devpedrod.TestsJunit.services.IUserService;
import com.devpedrod.TestsJunit.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        log.info("Looking for user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User with ID: "+ id +" not found"));
    }
}
