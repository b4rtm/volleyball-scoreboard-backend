package com.example.volleyballscoreboardbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    public User(String login, String password, Role role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private Role role;

    public User() {

    }

    public enum Role {
        ROLE_OBSERVER,
        ROLE_REFEREE
    }
}
