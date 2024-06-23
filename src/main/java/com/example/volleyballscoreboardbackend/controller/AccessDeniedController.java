package com.example.volleyballscoreboardbackend.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccessDeniedController {

    @MessageExceptionHandler(AccessDeniedException.class)
    @SendToUser("/topic/errors")
    public String handleAccessDeniedException(AccessDeniedException ex) {
        return ex.getMessage();
    }
}

