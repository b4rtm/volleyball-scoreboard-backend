package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.dto.ScoreDto;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MatchController {

    @Autowired
    private MatchService matchService;

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

    @MessageMapping("/getMatch/{matchId}")
    @SendTo("/topic/matches/{matchId}")
    public Match sendMatch(@DestinationVariable Long matchId) {
        return matchService.getMatchById(matchId).orElse(new Match());
    }

    @MessageMapping("/updateScore/{matchId}")
    @SendTo("/topic/matches/{matchId}")
    public List<Match> updateScore(@DestinationVariable Long matchId, ScoreDto score) {
        matchService.addScore(matchId, score);
        return matchService.getAllMatches();
    }

    @MessageMapping("/deleteMatch/{matchId}")
    @SendTo("/topic/matches/{matchId}")
    public void deleteMatch(@DestinationVariable Long matchId) {
        matchService.deleteMatch(matchId);
    }

}
