package com.example.volleyballscoreboardbackend.service;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GoogleCertsResponse {
    private List<GoogleKey> keys;

    @Getter
    @Setter
    static class GoogleKey {
        private String kid;
        private String kty;
        private String alg;
        private String use;
        private String n;
        private String e;
        private String x5c;


    }
}
