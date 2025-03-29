package com.planit.enterprise;

import com.planit.enterprise.dto.EventDTO;
import com.planit.enterprise.dto.UserDTO;
import com.planit.enterprise.dto.RSVPDTO;
import com.planit.enterprise.service.interfaces.IEventService;
import com.planit.enterprise.service.interfaces.IUserService;
import com.planit.enterprise.service.interfaces.IRSVPService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlanItController {

    @Autowired
    private IEventService eventService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRSVPService rsvpService;


    @GetMapping("/")
    public String signIn(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "signIn";
    }


    @PostMapping("/signIn")
    public String signInSubmit(UserDTO userDTO, Model model) {
        // Validate user login
        UserDTO user = userService.fetchUserByEmail(userDTO.getEmail());
        if (user != null) {
            model.addAttribute("userDTO", user);
            return "redirect:/start";
        } else {
            model.addAttribute("error", "Invalid email. Please try again.");
            return "signIn";
        }
    }

    @RequestMapping("/start")
    public String homePage(Model model) {
        List<EventDTO> events = eventService.fetchAllEvents();
        model.addAttribute("events", events);
        return "start";
    }

    @RequestMapping("/createEvent")
    public String createEvent(Model model) {
        model.addAttribute("eventDTO", new EventDTO());
        return "createEvent";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(EventDTO event) {
        eventService.createEvent(event.getName(), event.getDate(), event.getLocation());
        return "redirect:/start";
    }
}


//views not yet created

    /*

    @GetMapping("/rsvps")
    public String viewUserRSVPs(Model model, int userId) {
        model.addAttribute("rsvps", rsvpService.fetchAllByUserID(userId));
        return "rsvpList";
    }

    @PostMapping("/rsvp")
    public String rsvpToEvent(int eventId, int userId) {
        rsvpService.createRSVP(eventId, userId);
        return "redirect:/start";
    }

    @PostMapping("/updateRSVP")
    public String updateRSVP(int eventId, int userId, int status) {
        rsvpService.updateStatus(eventId, userId, status);
        return "redirect:/rsvps?userId=" + userId;
    }
}*/