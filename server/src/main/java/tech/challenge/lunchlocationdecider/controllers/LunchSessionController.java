package tech.challenge.lunchlocationdecider.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.challenge.lunchlocationdecider.models.LunchSession;
import tech.challenge.lunchlocationdecider.services.LunchSessionService;

@RestController
@RequestMapping(path="/lunch", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class LunchSessionController {

    @Autowired
    private LunchSessionService lunchSessionService;

    @PostMapping(path="/session/create")
    public LunchSession postLunchSession(@RequestBody LunchSession lunchSession) {
        return lunchSessionService.postLunchSession(lunchSession);
    }

    @GetMapping(path="/session")
    public LunchSession getLunchSessionByReferenceId(@RequestBody LunchSession lunchSession) {
        return lunchSessionService.getReferenceById(lunchSession.getId());
    }

}
