package com.devpedrod.TestsJunit.controller;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.dto.UserDto;
import com.devpedrod.TestsJunit.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    // Index para pegar o User na lista
    public static final int INDEX = 0;

    // Parâmetros para criar User
    public static final Long ID = 1L;
    public static final String NAME = "Pedro";
    public static final String EMAIL = "Pedro@gmail.com";
    public static final String PASSWORD = "Pedro123";

    @InjectMocks
    private UserController userController;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private IUserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenGetByIdThenReturnSuccess() {
        when(userService.getById(anyLong())).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDto.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void whenGetAllUsersThenReturnAListOfUserDTO() {
        when(userService.getAllUsers()).thenReturn(List.of(user));
        when(modelMapper.map(any(), any())).thenReturn(userDto);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDto.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(userService.create(any())).thenReturn(user);

        ResponseEntity<Void> response = userController.create(userDto);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
        verify(userService, times(1)).create(userDto);
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userService.update(userDto)).thenReturn(user);

        ResponseEntity<Void> response = userController.update(ID, userDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        verify(userService, times(1)).update(userDto);
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(userService).delete(anyLong());

        ResponseEntity<Void> response = userController.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).delete(anyLong());
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
    }
}