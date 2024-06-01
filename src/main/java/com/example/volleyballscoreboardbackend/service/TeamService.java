package com.example.volleyballscoreboardbackend.service;

import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
