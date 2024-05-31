package com.example.volleyballscoreboardbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private Role role;

    public enum Role {
        ROLE_OBSERVER,
        ROLE_REFEREE
    }
}
