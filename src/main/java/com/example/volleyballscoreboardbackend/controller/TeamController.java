package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.service.TeamService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @MessageMapping("/getTeams")
    @SendTo("/topic/teams")
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }
}
