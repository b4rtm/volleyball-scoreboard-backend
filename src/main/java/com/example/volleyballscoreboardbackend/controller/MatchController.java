package com.example.volleyballscoreboardbackend.controller;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
