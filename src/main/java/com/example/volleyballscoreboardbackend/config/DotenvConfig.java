package com.example.volleyballscoreboardbackend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("/src/main/resources")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }
}
