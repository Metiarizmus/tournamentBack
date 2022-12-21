package com.scorina.competition.controllers.auth.payload;

import lombok.Data;

@Data
public class LoginPayload {
    private String login;
    private String password;
}
