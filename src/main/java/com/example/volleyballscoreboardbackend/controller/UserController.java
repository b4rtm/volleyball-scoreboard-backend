package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.dto.AuthDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @MessageMapping("/auth")
    @SendTo("/topic/authRes")
    public String greeting(AuthDto authData) {
        return "udalo sie zalogowac dla: " + authData.getLogin();
    }
}
