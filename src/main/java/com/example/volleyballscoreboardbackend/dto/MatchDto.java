package com.example.volleyballscoreboardbackend.dto;

import lombok.Data;

@Data
public class MatchDto {

    private String teamA;

    private String teamB;

    private String date;

    private String status;

    private Integer setsToWin;

    private Integer pointsToWinSet;

    private Boolean isTieBreak;

    private Integer pointsToWinTieBreak;
}
