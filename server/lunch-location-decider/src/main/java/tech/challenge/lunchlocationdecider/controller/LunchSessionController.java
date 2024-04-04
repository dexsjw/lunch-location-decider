package tech.challenge.lunchlocationdecider.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.challenge.lunchlocationdecider.dto.LunchSessionDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;
import tech.challenge.lunchlocationdecider.service.LunchSessionService;

@Log
@RestController
@RequestMapping(path="/lunch/session", produces= MediaType.APPLICATION_JSON_VALUE)
public class LunchSessionController {

    @Autowired
    private LunchSessionService lunchSessionService;

    @PostMapping(path="/new")
    public ResponseEntity<LunchSessionDto> newLunchSession() {
        log.info("Creating a new Lunch Session!");
        LunchSessionDto lunchSessionDto = lunchSessionService.newLunchSession();
        return ResponseEntity.status(HttpStatus.CREATED).body(lunchSessionDto);
    }

    @PostMapping(path="/find")
    public ResponseEntity<LunchSessionDto> findLunchSession(@RequestBody LunchSessionDto lunchSessionDto) {
        log.info("Retrieving Lunch Session by room code...");
        return lunchSessionService.findLunchSession(lunchSessionDto);
    }

    @PutMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LunchSessionDto updateLunchSessionRestaurants(@RequestBody LunchSessionEntity lunchSessionEntity) {
        log.info("Updating Lunch Session restaurants...");
        return lunchSessionService.updateLunchSessionRestaurants(lunchSessionEntity);
    }

    @PutMapping(path="/end", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LunchSessionDto endLunchSession(@RequestBody LunchSessionEntity lunchSessionEntity) {
        log.info("Ending Lunch Session...");
        return lunchSessionService.updateLunchSessionActiveStatus(lunchSessionEntity);
    }

}
