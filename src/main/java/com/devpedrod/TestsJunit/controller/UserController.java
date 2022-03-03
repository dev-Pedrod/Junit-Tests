package com.devpedrod.TestsJunit.controller;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.dto.UserDto;
import com.devpedrod.TestsJunit.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    public static final String ID = "/{id}";
    @Autowired
    private IUserService userService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping(ID)
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(userService.getById(id), UserDto.class));
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers()
                .stream()
                .map(x -> mapper.map(x, UserDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody UserDto user){
        User newUser = userService.create(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(ID)
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(ID)
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserDto object){
        object.setId(id);
        userService.update(object);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
