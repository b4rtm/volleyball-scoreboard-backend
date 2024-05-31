package com.example.volleyballscoreboardbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToOne
    private Team teamA;

    @ManyToOne
    private Team teamB;

    private String result;

    private String resultDetailed;

    private String timeline;

    private Status status;
}
