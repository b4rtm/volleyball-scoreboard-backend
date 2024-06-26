package com.example.volleyballscoreboardbackend.repository;

import com.example.volleyballscoreboardbackend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
}
