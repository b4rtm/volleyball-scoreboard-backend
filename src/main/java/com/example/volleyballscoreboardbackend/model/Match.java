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

    @Column(length = 20000)
    private String resultDetailed;

    private String timeline;

    private String setsTimes;

    private Status status;

    private Integer setsToWin;

    private Integer pointsToWinSet;

    private Boolean isTieBreak;

    private Integer pointsToWinTieBreak;

    public enum Status {
        PLANNED,
        IN_PROGRESS,
        FINISHED
    }

}
