package com.example.volleyballscoreboardbackend.repository;

import com.example.volleyballscoreboardbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByLogin(String login);
}
