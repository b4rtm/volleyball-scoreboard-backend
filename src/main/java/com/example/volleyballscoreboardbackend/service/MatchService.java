package com.example.volleyballscoreboardbackend.service;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.mapper.MatchDtoMapper;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchDtoMapper matchDtoMapper;

    private final MatchRepository matchRepository;

    public MatchService(MatchDtoMapper matchDtoMapper, MatchRepository matchRepository) {
        this.matchDtoMapper = matchDtoMapper;
        this.matchRepository = matchRepository;
    }

    public Match addMatch(MatchDto matchDto) {

        Match match = matchDtoMapper.map(matchDto);
        return matchRepository.save(match);
    }
}
