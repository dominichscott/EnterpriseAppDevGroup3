package com.planit.enterprise.dto;

import lombok.Data;

public @Data class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    // Constructor with parameters
    public UserDTO(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

