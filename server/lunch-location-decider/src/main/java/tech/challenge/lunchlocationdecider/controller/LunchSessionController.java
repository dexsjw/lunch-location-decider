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
        log.info("Creating a new Lunch Session...");
        LunchSessionResponseDto lunchSessionResponseDto = lunchSessionService.newLunchSession();
        if (lunchSessionResponseDto.getMessage().startsWith("<400>")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lunchSessionResponseDto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(lunchSessionResponseDto);
    }

    @PostMapping(path="/find", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LunchSessionResponseDto> findLunchSession(@RequestBody LunchSessionRequestDto lunchSessionRequestDto) {
        log.info("Retrieving Lunch Session by Room ID...");
        LunchSessionResponseDto lunchSessionResponseDto = lunchSessionService.processLunchSessionRequestDto(lunchSessionRequestDto, "find");
        if (lunchSessionResponseDto.getMessage().startsWith("<400>")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lunchSessionResponseDto);
        } else if (lunchSessionResponseDto.getMessage().startsWith("<404>")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lunchSessionResponseDto);
        }
        return ResponseEntity.ok(lunchSessionResponseDto);
    }

    @PutMapping(path="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LunchSessionResponseDto> updateLunchSessionRestaurants(@RequestBody LunchSessionRequestDto lunchSessionRequestDto) {
        log.info("Updating Lunch Session restaurants...");
        LunchSessionResponseDto lunchSessionResponseDto = lunchSessionService.processLunchSessionRequestDto(lunchSessionRequestDto, "update");
        if (lunchSessionResponseDto.getMessage().startsWith("<400>")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lunchSessionResponseDto);
        } else if (lunchSessionResponseDto.getMessage().startsWith("<404>")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lunchSessionResponseDto);
        }
        return  ResponseEntity.ok(lunchSessionResponseDto);
    }

    @PutMapping(path="/end", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LunchSessionResponseDto> endLunchSession(@RequestBody LunchSessionRequestDto lunchSessionRequestDto) {
        log.info("Ending Lunch Session...");
        LunchSessionResponseDto lunchSessionResponseDto = lunchSessionService.processLunchSessionRequestDto(lunchSessionRequestDto, "end");
        if (lunchSessionResponseDto.getMessage().startsWith("<400>")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lunchSessionResponseDto);
        } else if (lunchSessionResponseDto.getMessage().startsWith("<404>")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lunchSessionResponseDto);
        }
        return  ResponseEntity.ok(lunchSessionResponseDto);
    }

}
