package com.example.volleyballscoreboardbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class SetRecord {
    private String setStartTime;
    private String setEndTime;

    public SetRecord(String setStartTime, String setEndTime){
        this.setStartTime = setStartTime;
        this.setEndTime = setEndTime;
    }

}

