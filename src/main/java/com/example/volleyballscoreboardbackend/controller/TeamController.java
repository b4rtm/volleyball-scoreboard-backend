package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.service.TeamService;
import com.fasterxml.jackson.databind.JsonNode;
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

    @MessageMapping("/addTeam")
    @SendTo("/topic/teams")
    public List<Team> addTeam(Team team) {
        teamService.addTeam(team);
        return teamService.getAllTeams();
    }

    @MessageMapping("/updateTeam")
    @SendTo("/topic/teams")
    public List<Team> updateTeam(Team team) {
        teamService.updateTeam(team);
        return teamService.getAllTeams();
    }

    @MessageMapping("/deleteTeam")
    @SendTo("/topic/teams")
    public List<Team> deleteTeam(JsonNode payload) {
        Long teamId = payload.get("id").asLong();
        teamService.deleteTeam(teamId);
        return teamService.getAllTeams();
    }
}
