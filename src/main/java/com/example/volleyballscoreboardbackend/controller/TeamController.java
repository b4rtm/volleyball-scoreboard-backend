package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.config.SecurityConfig;
import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.model.User;
import com.example.volleyballscoreboardbackend.service.TeamService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @MessageMapping("/getTeams")
    @SendTo("/topic/teams")
    public List<Team> getTeams(@Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE) && !SecurityConfig.checkUserAccess(token, User.Role.ROLE_OBSERVER)){
            throw new AccessDeniedException("This endpoint is only for logged in users");
        }
        return teamService.getAllTeams();
    }

    @MessageMapping("/addTeam")
    @SendTo("/topic/teams")
    public List<Team> addTeam(Team team, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        teamService.addTeam(team);
        return teamService.getAllTeams();
    }

    @MessageMapping("/updateTeam")
    @SendTo("/topic/teams")
    public List<Team> updateTeam(Team team, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        teamService.updateTeam(team);
        return teamService.getAllTeams();
    }

    @MessageMapping("/deleteTeam")
    @SendTo("/topic/teams")
    public List<Team> deleteTeam(JsonNode payload, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        Long teamId = payload.get("id").asLong();
        teamService.deleteTeam(teamId);
        return teamService.getAllTeams();
    }
}
