package com.example.volleyballscoreboardbackend.config;

import com.example.volleyballscoreboardbackend.model.User;
import com.example.volleyballscoreboardbackend.service.JwtUtils;
import com.example.volleyballscoreboardbackend.service.UserService;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    private static UserService staticUserService;
    @Autowired
    public void setStaticUserService(UserService userService) {
        SecurityConfig.staticUserService = userService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/**").permitAll().anyRequest().authenticated());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }

    public static boolean checkUserAccess(String token, User.Role role){
        token = token.split(" ")[1];
        JsonObject claims = JwtUtils.decodeAndVerifyGoogleJWT(token);
        User user = staticUserService.getUserByEmail(claims.get("email").getAsString());
        return user.getRole() == role;
    }
}