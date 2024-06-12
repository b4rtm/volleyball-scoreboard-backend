package com.example.volleyballscoreboardbackend.service;

import com.example.volleyballscoreboardbackend.dto.MatchDto;
import com.example.volleyballscoreboardbackend.dto.ScoreDto;
import com.example.volleyballscoreboardbackend.mapper.MatchDtoMapper;
import com.example.volleyballscoreboardbackend.model.Match;
import com.example.volleyballscoreboardbackend.repository.MatchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchService {

    public static final int NUMBER_OF_TIMEOUTS = 2;

    private final MatchDtoMapper matchDtoMapper;

    private final MatchRepository matchRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MatchService(MatchDtoMapper matchDtoMapper, MatchRepository matchRepository) {
        this.matchDtoMapper = matchDtoMapper;
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match addMatch(MatchDto matchDto) {

        Match match = matchDtoMapper.map(matchDto);
        if (match.getTimeline() == null){
            match.setTimeline("[]");
        }

        match.setSetsTimes("[]");
        return matchRepository.save(match);
    }

    public void deleteMatch(Long matchId){
        matchRepository.deleteById(matchId);
    }

    public Optional<Match> getMatchById(Long matchId) {
        return matchRepository.findById(matchId);
    }

    public void addScore(Long matchId, ScoreDto score) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new IllegalArgumentException("match not found"));
        try {
            String timeline = match.getTimeline();
            List<List<ScoreDto>> timelineList = objectMapper.readValue(timeline, new TypeReference<>() {});

            List<ScoreDto> lastSet;
            if (timelineList.isEmpty()) {
                lastSet = new ArrayList<>();
                timelineList.add(lastSet);
            } else {
                lastSet = timelineList.get(timelineList.size() - 1);
            }

            lastSet.add(score);
            String updatedTimeline = objectMapper.writeValueAsString(timelineList);
            match.setTimeline(updatedTimeline);

            matchRepository.save(match);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to process timeline JSON", e);
        }
    }

    public void takeTimeout(Long matchId, Long teamId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new IllegalArgumentException("match not found"));
        try {
            String timeline = match.getTimeline();
            List<List<ScoreDto>> timelineList = objectMapper.readValue(timeline, new TypeReference<>() {});

            List<ScoreDto> lastSet;
            if (timelineList.isEmpty()) {
                return;
            }
            lastSet = timelineList.get(timelineList.size() - 1);
            ScoreDto lastScore = lastSet.get(lastSet.size() - 1);

            List<ScoreDto> listOfTeamTimeouts = lastSet.stream().filter(scoreDto -> !Objects.equals(scoreDto.getTeamId(), teamId) && scoreDto.getOpponentBreak() == 1).toList();

            if(!Objects.equals(lastScore.getTeamId(), teamId) && listOfTeamTimeouts.size() < NUMBER_OF_TIMEOUTS){
                lastScore.setOpponentBreak(1);
                String updatedTimeline = objectMapper.writeValueAsString(timelineList);
                match.setTimeline(updatedTimeline);

                matchRepository.save(match);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to process timeline JSON", e);
        }
    }

    public void endSet(Long matchId){
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new IllegalArgumentException("match not found"));
        try {
            String timeline = match.getTimeline();
            List<List<ScoreDto>> timelineList = objectMapper.readValue(timeline, new TypeReference<>() {});

            List<ScoreDto> lastSet;
            lastSet = timelineList.get(timelineList.size() - 1);

            int teamAPoints = calculateTeamPoints(lastSet, match.getTeamA().getId());
            int teamBPoints = calculateTeamPoints(lastSet, match.getTeamB().getId());

            int[] scores = parseResult(match.getResult());

            if(teamAPoints > teamBPoints){
                int newScore = scores[0] + 1;
                match.setResult(newScore + ":" + scores[1]);
            }
            else{
                int newScore = scores[1] + 1;
                match.setResult(scores[0] + ":" + newScore);
            }

            timelineList.add(new ArrayList<>());
            String updatedTimeline = objectMapper.writeValueAsString(timelineList);
            match.setTimeline(updatedTimeline);


            matchRepository.save(match);


        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to process timeline JSON", e);
        }
    }

    private static int calculateTeamPoints(List<ScoreDto> lastSet, Long id) {
        for(int i = lastSet.size() -1; i >= 0; i--){
            if(lastSet.get(i).getTeamId().equals(id))
                return lastSet.get(i).getPoint();
        }
        return 0;
    }

    public static int[] parseResult(String result) {
        String[] parts = result.split(":");
        int teamAScore = Integer.parseInt(parts[0].trim());
        int teamBScore = Integer.parseInt(parts[1].trim());
        return new int[] { teamAScore, teamBScore };
    }
}
