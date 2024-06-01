package com.example.volleyballscoreboardbackend.repository;

import com.example.volleyballscoreboardbackend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
