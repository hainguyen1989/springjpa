package com.haint.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestObj {
    private String username;

    public String getUsername() {
        return username;
    }

}
