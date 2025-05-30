package com.planit.enterprise;

import com.planit.enterprise.dto.RSVPDTO;
import com.planit.enterprise.dto.UserDTO;
import com.planit.enterprise.entity.Event;
import com.planit.enterprise.entity.RSVP;
import com.planit.enterprise.entity.User;
import com.planit.enterprise.service.interfaces.IRSVPService;
import com.planit.enterprise.service.interfaces.IEventService;
import com.planit.enterprise.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/rsvps")
public class RSVPController {

    @Autowired
    private IRSVPService rsvpService;

    @Autowired
    private IEventService eventService;

    @Autowired
    private IUserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/viewEvent/{id}")
    public String viewEvent(@PathVariable("id") int eventId, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            Event event = eventService.getEventById(eventId);
            Optional<Event> optionalEvent = Optional.ofNullable(event);

            if (optionalEvent.isPresent()) {
                model.addAttribute("event", optionalEvent.get());

                List<RSVPDTO> rsvps = rsvpService.getRSVPsByEvent(optionalEvent.get());
                Map<Integer, String> statusMap = new HashMap<>();
                Map<Integer, String> userFullNamesMap = new HashMap<>();

                // Populate userMap and userFullNames
                for (RSVPDTO rsvp : rsvps) {
                    int userId = rsvp.getUserId();
                    String fullName = userService.getFullNameById(userId);
                    String status = rsvp.getRsvpStatusAsString();

                    userFullNamesMap.put(userId, fullName);
                    statusMap.put(userId, status);

                }
                model.addAttribute("rsvps", rsvps);


                String hostFName = event.getHost().getFName();
                String hostLName = event.getHost().getLName();

                model.addAttribute("hostFName", hostFName);
                model.addAttribute("hostLName", hostLName);

                model.addAttribute("fullNameMap", userFullNamesMap);
                model.addAttribute("statusMap", statusMap);
                model.addAttribute("currentUser", currentUser);
            }
        } else {
            return "redirect:/signIn";
        }

        return "viewEvent";
    }

    @PostMapping("/viewEvent/{id}")
    public String updateRSVP(@PathVariable("id") int eventId,
                             @RequestParam("status") String status,
                             Model model,
                             HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/signIn";
        }

        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return "redirect:/error";
        }

        rsvpService.createOrUpdateRSVP(currentUser, event, status);

        return "redirect:/rsvps/viewEvent/" + eventId;
    }

}








