package com.example.volleyballscoreboardbackend.service;

import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.repository.MatchRepository;
import com.example.volleyballscoreboardbackend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> updateTeam(Team teamDetails) {
        Optional<Team> team = teamRepository.findById(teamDetails.getId());
        if (team.isPresent()) {
            Team updatedTeam = team.get();
            updatedTeam.setName(teamDetails.getName());
            updatedTeam.setPlayers(teamDetails.getPlayers());
            return Optional.of(teamRepository.save(updatedTeam));
        } else {
            return Optional.empty();
        }
    }

    public void deleteTeam(Long id) {
        List<Match> matchesToDelete = matchRepository.findByTeamId(id);
        for (Match match : matchesToDelete) {
            matchRepository.delete(match);
        }

        teamRepository.deleteById(id);
    }
}
