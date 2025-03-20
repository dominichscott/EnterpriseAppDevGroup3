package com.planit.enterprise.dto;

import lombok.Data;

public @Data class EventDTO {
    private int id;
    private String name;
    private String date;
    private String location;

    // Constructor with parameters
    public EventDTO(int id, String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public EventDTO() {
    }
}

