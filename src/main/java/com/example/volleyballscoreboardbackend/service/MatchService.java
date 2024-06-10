package com.example.volleyballscoreboardbackend.service;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.mapper.MatchDtoMapper;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.model.Team;
import com.example.volleyballscoreboardbackend.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchDtoMapper matchDtoMapper;

    private final MatchRepository matchRepository;

    public MatchService(MatchDtoMapper matchDtoMapper, MatchRepository matchRepository) {
        this.matchDtoMapper = matchDtoMapper;
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match addMatch(MatchDto matchDto) {

        Match match = matchDtoMapper.map(matchDto);
        return matchRepository.save(match);
    }

    public void deleteMatch(Long matchId){
        matchRepository.deleteById(matchId);
    }

    public Optional<Match> getMatchById(Long matchId) {
        return matchRepository.findById(matchId);
    }
}
