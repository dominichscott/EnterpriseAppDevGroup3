package com.planit.enterprise.dto;

import lombok.Data;

public @Data class RSVPDTO {
    private int id;
    private int userId;
    private int eventId;
    private String rsvpStatus;

    // Constructor with parameters
    public RSVPDTO(int id, int userId, int eventId, String rsvpStatus) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.rsvpStatus = rsvpStatus;
    }
}


