package tech.challenge.lunchlocationdecider.services;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.challenge.lunchlocationdecider.models.LunchSession;

import java.util.ArrayList;
import java.util.UUID;
import tech.challenge.lunchlocationdecider.repositories.LunchSessionRepository;

@Log
@Service
@Transactional
public class LunchSessionService {

    @Autowired
    private LunchSessionRepository lunchSessionRepository;

    public LunchSession newLunchSession(LunchSession lunchSession) {
        String roomCode = UUID.randomUUID().toString();
        lunchSession.setOwnerCode(UUID.randomUUID().toString().substring(0, 8) + "_" + roomCode);
        lunchSession.setRoomCode(roomCode);
        lunchSession.setActiveStatus(true);
        lunchSession.setRestaurants("");
        log.info("Lunch Session with Room Code '" + roomCode + "' created!");
        return lunchSessionRepository.save(lunchSession);
    }

    public LunchSession findLunchSession(LunchSession lunchSession) {
        String roomCode = lunchSession.getRoomCode();
        log.info("RoomCode: " + roomCode);
        if (roomCode == null || roomCode.isEmpty()) {
            lunchSession.setRestaurants("<Error>: Room Code provided is empty.");
            log.info(lunchSession.getRestaurants());
            return lunchSession;
        }
        LunchSession existingLunchSession = lunchSessionRepository.findByRoomCode(roomCode);
        if (existingLunchSession == null) {
            lunchSession.setRestaurants("<Error>: Room Code does not exist.");
            log.info(lunchSession.getRestaurants());
            return lunchSession;
        } else {
            if (!existingLunchSession.isActiveStatus()) {
                lunchSession.setRestaurants("<Error>: Session has ended.");
                log.info(lunchSession.getRestaurants());
                return lunchSession;
            }

            String existingRestaurants = existingLunchSession.getRestaurants();
            if (existingRestaurants == null || existingRestaurants.isEmpty()) {
                existingLunchSession.setRestaurantsList(new ArrayList<>());
            } else {
                existingLunchSession.setRestaurantsList(LunchSession.generateRestaurantsList(existingRestaurants));
            }
            log.info("Successfully retrieved Lunch Session for Room Code: " + roomCode);
            return existingLunchSession;
        }
    }

    public LunchSession updateLunchSessionRestaurants(LunchSession lunchSession) {
        LunchSession existingLunchSession = lunchSessionRepository.findByRoomCode(lunchSession.getRoomCode());
        if (existingLunchSession == null) {
            lunchSession.setRestaurants("<Error>: Room Code does not exist.");
            log.info(lunchSession.getRestaurants());
            return lunchSession;
        } else {
            if (!existingLunchSession.isActiveStatus()) {
                lunchSession.setRestaurants("<Error>: Session has ended.");
                log.info(lunchSession.getRestaurants());
                return lunchSession;
            }
            log.info("Lunch Session with Room Code '" + lunchSession.getRoomCode() + "' found!");

            String existingRestaurants = existingLunchSession.getRestaurants();
            String updatedRestaurants = "";
            if (existingRestaurants == null || existingRestaurants.isEmpty()) {
                updatedRestaurants = lunchSession.getRestaurants();
            } else {
                updatedRestaurants = String.join(",", existingRestaurants, lunchSession.getRestaurants());
            }
            lunchSessionRepository.updateLunchSessionRestaurantsByRoomCode(updatedRestaurants, lunchSession.getRoomCode());
            existingLunchSession.setRestaurants(updatedRestaurants);
            existingLunchSession.setRestaurantsList(LunchSession.generateRestaurantsList(updatedRestaurants));
            log.info("Successfully updated Lunch Session restaurants for Room Code: " + lunchSession.getRoomCode());
            return existingLunchSession;
        }
    }

    public LunchSession updateLunchSessionActiveStatus(LunchSession lunchSession) {
        LunchSession existingLunchSession = lunchSessionRepository.findByRoomCode(lunchSession.getRoomCode());
        if (existingLunchSession == null) {
            lunchSession.setRestaurants("<Error>: Room Code does not exist.");
            log.info(lunchSession.getRestaurants());
            return lunchSession;
        } else {
            log.info("Lunch Session with Room Code '" + lunchSession.getRoomCode() + "' found!");
            lunchSessionRepository.updateLunchSessionActiveStatusByRoomCode(false, lunchSession.getRoomCode());
            existingLunchSession.setActiveStatus(false);

            String existingRestaurants = existingLunchSession.getRestaurants();
            if (existingRestaurants == null || existingRestaurants.isEmpty()) {
                existingLunchSession.setRestaurantsList(new ArrayList<>());
            } else {
                existingLunchSession.setRestaurantsList(LunchSession.generateRestaurantsList(existingRestaurants));
            }
            log.info("Successfully ended Lunch Session for Room Code: " + lunchSession.getRoomCode());
            return existingLunchSession;
        }
    }

}
