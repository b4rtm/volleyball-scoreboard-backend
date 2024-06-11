package com.example.volleyballscoreboardbackend.dto;

import lombok.Data;

@Data
public class ScoreDto {

    private Long teamId;

    private Integer point;

    private Integer opponentBreak;
}
