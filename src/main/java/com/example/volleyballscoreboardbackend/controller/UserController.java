package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.dto.AuthDto;
import com.example.volleyballscoreboardbackend.service.UserService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @MessageMapping("/auth")
    @SendTo("/topic/authRes")
    public String greeting(AuthDto authData) {
        String createUserRes = userService.addUniqueUser(authData);

        return new Gson().toJson(createUserRes + ", udalo sie zalogowac dla: " + authData.getLogin());
    }
}
