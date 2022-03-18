package com.devpedrod.TestsJunit.controller.exceptions;

import com.devpedrod.TestsJunit.services.exceptions.DataIntegrityException;
import com.devpedrod.TestsJunit.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    private static final String OBJECT_NOT_fOUND = "Object not found";
    private static final String E_MAIL_ALREADY_EXISTS = "E-mail already exists";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException(OBJECT_NOT_fOUND),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJECT_NOT_fOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void whenDataIntegrityThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityException(
                        new DataIntegrityException(E_MAIL_ALREADY_EXISTS),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(E_MAIL_ALREADY_EXISTS, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}