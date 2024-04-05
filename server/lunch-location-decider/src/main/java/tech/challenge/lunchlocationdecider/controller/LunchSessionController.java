package tech.challenge.lunchlocationdecider.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.service.LunchSessionService;

@Log
@RestController
@RequestMapping(path="/lunch/session", produces= MediaType.APPLICATION_JSON_VALUE)
public class LunchSessionController {

    @Autowired
    private LunchSessionService lunchSessionService;

    @GetMapping(path="/new")
    public ResponseEntity<LunchSessionResponseDto> newLunchSession() {
        log.info("Creating a new Lunch Session!");
        LunchSessionResponseDto lunchSessionResponseDto = lunchSessionService.newLunchSession();
        return ResponseEntity.status(HttpStatus.CREATED).body(lunchSessionResponseDto);
    }

    @PostMapping(path="/find")
    public ResponseEntity<LunchSessionResponseDto> findLunchSession(@RequestBody LunchSessionRequestDto lunchSessionRequestDto) {
        log.info("Retrieving Lunch Session by room code...");
        LunchSessionResponseDto lunchSessionResponseDto = lunchSessionService.findLunchSession(lunchSessionRequestDto);
        return ResponseEntity.ok(lunchSessionResponseDto);
    }

//    @PutMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public LunchSessionDto updateLunchSessionRestaurants(@RequestBody LunchSessionEntity lunchSessionEntity) {
//        log.info("Updating Lunch Session restaurants...");
//        return lunchSessionService.updateLunchSessionRestaurants(lunchSessionEntity);
//    }
//
//    @PutMapping(path="/end", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public LunchSessionDto endLunchSession(@RequestBody LunchSessionEntity lunchSessionEntity) {
//        log.info("Ending Lunch Session...");
//        return lunchSessionService.updateLunchSessionActiveStatus(lunchSessionEntity);
//    }

}
