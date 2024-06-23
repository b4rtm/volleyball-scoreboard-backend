package com.example.volleyballscoreboardbackend.controller;


import com.example.volleyballscoreboardbackend.config.SecurityConfig;
import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.dto.ScoreDto;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.model.User;
import com.example.volleyballscoreboardbackend.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @MessageMapping("/addMatch")
    @SendTo("/topic/matches")
    public List<Match> addMatch(MatchDto match, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        matchService.addMatch(match);
        return matchService.getAllMatches();
    }

    @MessageMapping("/getMatches")
    @SendTo("/topic/matches")
    public List<Match> getMatches(@Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE) && !SecurityConfig.checkUserAccess(token, User.Role.ROLE_OBSERVER)){
            throw new AccessDeniedException("This endpoint is only for logged in users");
        }
        return matchService.getAllMatches();
    }

    @MessageMapping("/updateScore/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> updateScore(@DestinationVariable Long matchId, ScoreDto score, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        matchService.addScore(matchId, score);
        return matchService.getAllMatches();
    }

    @MessageMapping("/timeout/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> updateTimeout(@DestinationVariable Long matchId, Long teamId, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        matchService.takeTimeout(matchId, teamId);
        return matchService.getAllMatches();
    }

    @MessageMapping("/deleteMatch/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> deleteMatch(@DestinationVariable Long matchId, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        matchService.deleteMatch(matchId);
        return matchService.getAllMatches();
    }

    @MessageMapping("/endSet/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> endSet(@DestinationVariable Long matchId, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        matchService.endSet(matchId, false);
        return matchService.getAllMatches();
    }

    @MessageMapping("/endMatch/{matchId}")
    @SendTo("/topic/matches")
    public List<Match> endMatch(@DestinationVariable Long matchId, @Header("Authorization") String token) {
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE)){
            throw new AccessDeniedException("This endpoint is only for referee");
        }
        matchService.endMatch(matchId);
        return matchService.getAllMatches();
    }

    @MessageMapping("/currentSetNumber/{matchId}")
    @SendTo("/topic/currentSetNumber/{matchId}")
    public int getCurrentSetNumber(@DestinationVariable Long matchId, @Header("Authorization") String token){
        if (!SecurityConfig.checkUserAccess(token, User.Role.ROLE_REFEREE) && !SecurityConfig.checkUserAccess(token, User.Role.ROLE_OBSERVER)){
            throw new AccessDeniedException("This endpoint is only for logged in users");
        }
        return matchService.getCurrentSetNumber(matchId);
    }

}
