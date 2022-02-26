package com.devpedrod.TestsJunit.controller.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter @Setter
@SuperBuilder
public class StandardError {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String path;
}
