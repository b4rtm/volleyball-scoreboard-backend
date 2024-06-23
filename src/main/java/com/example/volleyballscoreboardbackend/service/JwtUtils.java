package com.example.volleyballscoreboardbackend.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtUtils {

    public static JsonObject decodeAndVerifyGoogleJWT(String token) {
        String[] parts = token.split("\\.");

        String claimsBase64 = parts[1];

        byte[] claimsBytes = Base64.getUrlDecoder().decode(claimsBase64);
        String claimsJson = new String(claimsBytes);

        return JsonParser.parseString(claimsJson).getAsJsonObject();
    }
}
