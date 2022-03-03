package com.devpedrod.TestsJunit.services.impl;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.dto.UserDto;
import com.devpedrod.TestsJunit.repository.UserRepository;
import com.devpedrod.TestsJunit.services.IUserService;
import com.devpedrod.TestsJunit.services.exceptions.DataIntegrityException;
import com.devpedrod.TestsJunit.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        log.info("Looking for user with ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User with id: "+ id +" not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("Searching all users");
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto object) {
        log.info("Creating a new user");
        findByEmail(object);
        return userRepository.save(mapper.map(object, User.class));
    }

    private void findByEmail(UserDto object) {
        log.info("Checking if the email: {} already exists", object.getEmail());
        Optional<User> user = userRepository.findByEmail(object.getEmail());
        if(user.isPresent() && !user.get().getId().equals(object.getId())){
            throw new DataIntegrityException("E-mail already exists");
        }
    }

    @Override
    public void update(UserDto object) {
        log.info("updating user with ID: {}", object.getId());
        getById(object.getId());
        findByEmail(object);
        userRepository.saveAndFlush(mapper.map(object, User.class));
    }

    @Override
    public void delete(Long id) {
        getById(id);
        userRepository.deleteById(id);
    }
}