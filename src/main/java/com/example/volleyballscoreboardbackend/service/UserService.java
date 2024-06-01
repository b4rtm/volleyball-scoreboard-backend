package com.example.volleyballscoreboardbackend.service;

import com.example.volleyballscoreboardbackend.dto.AuthDto;
import com.example.volleyballscoreboardbackend.model.User;
import com.example.volleyballscoreboardbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public String addUniqueUser(AuthDto authData){
        if (userRepository.getUserByLogin(authData.getLogin()) != null){
            return "Taki użytkownik już jest w bazie, więc nie został dodany do tabeli";
        }

        userRepository.save(new User(authData.getLogin(), authData.getPassword(), User.Role.ROLE_OBSERVER));
        return "Pomyślnie dodano użytkownika do tabeli";
    }
}
