package com.markandkyle.discordbot;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by CptAmerica on 7/22/17.
 */
public class Config {

    private String token;

    String getToken() {
        return this.token;
    }

    void setToken(String token) {
        this.token = token;
    }
}
