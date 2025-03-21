package com.planit.enterprise.service.impl;

import com.planit.enterprise.dto.RSVPDTO;
import com.planit.enterprise.entity.RSVP;
import com.planit.enterprise.entity.User; // Import User
import com.planit.enterprise.entity.Event; // Import Event
import com.planit.enterprise.repository.EventRepository;
import com.planit.enterprise.repository.RSVPRepository;
import com.planit.enterprise.repository.UserRepository;
import com.planit.enterprise.service.interfaces.IRSVPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RSVPServiceImpl implements IRSVPService {

    @Autowired
    private RSVPRepository rsvpRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public RSVPServiceImpl(RSVPRepository rsvpRepository) {
        this.rsvpRepository = rsvpRepository;
    }

    @Override
    public List<RSVPDTO> fetchAllByUserID(int userId) {
        List<RSVP> rsvps = rsvpRepository.findByUserId(userId);
        // Convert rsvps to RSVPDTO
        return rsvps.stream()
                .map(rsvp -> new RSVPDTO(rsvp.getId(), rsvp.getUser().getId(), rsvp.getEvent().getId(), rsvp.getRsvpStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RSVPDTO> fetchAllByEventID(int eventId) {
        List<RSVP> rsvps = rsvpRepository.findByEventId(eventId);
        // Convert rsvps to RSVPDTO
        return rsvps.stream()
                .map(rsvp -> new RSVPDTO(rsvp.getId(), rsvp.getUser().getId(), rsvp.getEvent().getId(), rsvp.getRsvpStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean updateStatus(int eventId, int userId, int status) {
        RSVP rsvp = rsvpRepository.findByUserIdAndEventId(userId, eventId);
        if (rsvp != null) {
            rsvp.setRsvpStatus(String.valueOf(status)); // Update the status
            rsvpRepository.save(rsvp);
            return true;
        }
        return false;
    }

    @Override
    public Boolean createRSVP(int eventId, int userId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        User user = userRepository.findById(userId);

        RSVP rsvp = new RSVP();
        rsvp.setEvent(event);
        rsvp.setUser(user);
        rsvp.setRsvpStatus(String.valueOf(0)); // Default status

        rsvpRepository.save(rsvp);
        return true;
    }
}


