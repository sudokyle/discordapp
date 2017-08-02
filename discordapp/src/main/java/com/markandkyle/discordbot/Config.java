package com.markandkyle.discordbot;

import com.fasterxml.jackson.annotation.JsonView;

public class Config {

    private String token;

    String getToken() {
        return this.token;
    }

    void setToken(String token) {
        this.token = token;
    }
}
