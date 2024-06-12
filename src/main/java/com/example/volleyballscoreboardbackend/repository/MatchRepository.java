package com.example.volleyballscoreboardbackend.repository;

import com.example.volleyballscoreboardbackend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m WHERE m.teamA.id = :teamId OR m.teamB.id = :teamId")
    List<Match> findByTeamId(Long teamId);

    @Query("SELECT m.setsTimes FROM Match m WHERE m.id = :matchId")
    String getSetsTimesByMatchId(Long matchId);
}