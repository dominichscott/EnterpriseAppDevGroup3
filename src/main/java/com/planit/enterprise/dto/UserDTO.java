package com.planit.enterprise.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    // Getters and setters
    @Setter
    @Getter
    private int id;
    private String fName;
    private String lName;
    @Setter
    @Getter
    private String email;

    // Constructor with parameters
    public UserDTO(int id, String fName, String lName, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public UserDTO() {}


    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

}

