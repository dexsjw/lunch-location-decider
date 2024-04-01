package tech.challenge.lunchlocationdecider.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tech.challenge.lunchlocationdecider.models.LunchSession;
import tech.challenge.lunchlocationdecider.services.LunchSessionService;

@Log
@RestController
@RequestMapping(path="/lunch/session", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class LunchSessionController {

    @Autowired
    private LunchSessionService lunchSessionService;

    @PostMapping(path="/new")
    public LunchSession newLunchSession(@RequestBody LunchSession lunchSession) {
        log.info("Creating a new Lunch Session!");
        return lunchSessionService.newLunchSession(lunchSession);
    }

    @GetMapping(path="/find")
    public LunchSession findLunchSession(@RequestBody LunchSession lunchSession) {
        log.info("Retrieving Lunch Session by room code...");
        return lunchSessionService.findLunchSession(lunchSession);
    }

    @PutMapping(path="/update")
    public LunchSession updateLunchSessionRestaurants(@RequestBody LunchSession lunchSession) {
        log.info("Updating Lunch Session restaurants...");
        return lunchSessionService.updateLunchSessionRestaurants(lunchSession);
    }

    @PutMapping(path="/end")
    public LunchSession endLunchSession(@RequestBody LunchSession lunchSession) {
        log.info("Ending Lunch Session...");
        return lunchSessionService.updateLunchSessionActiveStatus(lunchSession);
    }

}
