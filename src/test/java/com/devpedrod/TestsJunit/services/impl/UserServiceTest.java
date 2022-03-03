package com.devpedrod.TestsJunit.services.impl;

import com.devpedrod.TestsJunit.domain.User;
import com.devpedrod.TestsJunit.dto.UserDto;
import com.devpedrod.TestsJunit.repository.UserRepository;
import com.devpedrod.TestsJunit.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    // Parâmetros para o User
    public static final Long ID = 1L;
    public static final String NAME = "Pedro";
    public static final String EMAIL = "Pedro@gmail.com";
    public static final String PASSWORD = "Pedro123";

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    // Antes dos testes faça isso:
    @BeforeEach
    void setUp() {
        // Inicia os mocks dessa classe
        MockitoAnnotations.openMocks(this);

        startUser();
    }

    @Test
    void whenGetByIdThenReturnAnUserInstance() {
        // Quando o findById for chamado com qualquer Long, retorne um Optional User
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);

        User response = userService.getById(ID);

        // Assegura que o retorno não é nulo
        assertNotNull(response);
        // Assegura que ambos são iguais
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenGetByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException("User with id: "+ ID +" not found"));

        try {
            userService.getById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("User with id: "+ ID +" not found", ex.getMessage());
        }
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}