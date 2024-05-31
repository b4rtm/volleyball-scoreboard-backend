package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.dto.AuthDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(AuthDto authData) throws Exception {
        Thread.sleep(1000);
        return "udalo sie!!!" + authData.getLogin();
    }
}
