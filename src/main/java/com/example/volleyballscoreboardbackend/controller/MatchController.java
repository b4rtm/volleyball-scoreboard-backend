package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.dto.ScoreDto;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.service.MatchService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @MessageMapping("/addMatch")
    @SendTo("/topic/matches")
    public List<Match> addMatch(MatchDto match) {
        matchService.addMatch(match);
        return matchService.getAllMatches();
    }

    @MessageMapping("/getMatches")
    @SendTo("/topic/matches")
    public List<Match> getMatches() {
        return matchService.getAllMatches();
    }

    @MessageMapping("/updateScore/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> updateScore(@DestinationVariable Long matchId, ScoreDto score) {
        matchService.addScore(matchId, score);
        return matchService.getAllMatches();
    }

    @MessageMapping("/deleteMatch/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> deleteMatch(@DestinationVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return matchService.getAllMatches();
    }

}
