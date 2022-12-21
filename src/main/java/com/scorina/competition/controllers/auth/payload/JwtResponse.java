package com.scorina.competition.controllers.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;

    private String type = "Bearer";
    private String login;
    private List<String> roles;

    public JwtResponse(String token, String login, List<String> roles) {
        this.token = token;
        this.login = login;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", email='" + login + '\'' +
                ", roles=" + roles +
                '}';
    }
}