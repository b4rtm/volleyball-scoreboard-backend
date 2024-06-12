package com.example.volleyballscoreboardbackend.mapper;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchDtoMapper {

    private final TeamRepository teamRepository;

    public MatchDtoMapper(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Match map(MatchDto dto){
        Match match = new Match();
        match.setDate(dto.getDate());
        if(dto.getStatus().equals("PLANNED"))
            match.setStatus(Match.Status.PLANNED);
        else if(dto.getStatus().equals("IN_PROGRESS"))
            match.setStatus(Match.Status.IN_PROGRESS);

        Team teamA = teamRepository.findByName(dto.getTeamA());
        Team teamB = teamRepository.findByName(dto.getTeamB());

        match.setTeamA(teamA);
        match.setTeamB(teamB);

        match.setSetsToWin(dto.getSetsToWin());
        match.setPointsToWinSet(dto.getPointsToWinSet());
        match.setIsTieBreak(dto.getIsTieBreak());
        match.setPointsToWinTieBreak(dto.getPointsToWinTieBreak());
        if (match.getTimeline() == null){
            match.setTimeline("[]");
        }

        match.setSetsTimes(dto.getSetsTimes());
        match.setResultDetailed("{\"resD\": []}");
        match.setResult("0:0");
        return match;
    }

}
