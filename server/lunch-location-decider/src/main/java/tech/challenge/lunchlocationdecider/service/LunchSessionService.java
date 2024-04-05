package tech.challenge.lunchlocationdecider.service;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

import java.util.*;

import tech.challenge.lunchlocationdecider.helper.LunchSessionHelper;
import tech.challenge.lunchlocationdecider.repository.LunchSessionRepository;

@Log
@Service
@Transactional
public class LunchSessionService {

    @Autowired
    private LunchSessionRepository lunchSessionRepository;

    public LunchSessionRequestDto newLunchSession() {
        String roomCode = UUID.randomUUID().toString();
        String ownerCode = UUID.randomUUID().toString().substring(0, 8) + "_" + roomCode;
        LunchSessionEntity lunchSessionEntity = lunchSessionRepository.save(
                new LunchSessionEntity(0L, ownerCode, roomCode, true, ""));
        log.info("Lunch Session with Room Code '" + roomCode + "' created!");
        return LunchSessionHelper.toLunchSessionDto(lunchSessionEntity, true);
    }

    public LunchSessionRequestDto findLunchSession(LunchSessionRequestDto lunchSessionRequestDto) {
        LunchSessionHelper.lunchSessionDtoNullCheck(lunchSessionRequestDto);
        String roomCode = lunchSessionRequestDto.getRoomCode();
        log.info("RoomCode: " + roomCode);
        if (roomCode == null || roomCode.isEmpty()) {
            return whenLunchSessionRoomCodeEmpty(lunchSessionRequestDto);
        }
        Optional<LunchSessionEntity> optLunchSession = lunchSessionRepository.findByRoomCode(roomCode);
        LunchSessionEntity lunchSessionEntity = optLunchSession.orElse(
                new LunchSessionEntity(0L, "", "", false, ""));

        log.info("Successfully retrieved Lunch Session for Room Code: " + roomCode);
        boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
        return LunchSessionHelper.toLunchSessionDto(lunchSessionEntity, hasOwnerCode);
    }

    private LunchSessionRequestDto whenLunchSessionRoomCodeEmpty(
        LunchSessionRequestDto lunchSessionRequestDto) {
        lunchSessionRequestDto.setMessage("Room Code provided is empty.");
        return lunchSessionRequestDto;
    }

//    private LunchSessionDto whenLunchSessionEntityEmpty(LunchSessionDto lunchSessionDto) {
//
//    }

//    public LunchSessionEntity findLunchSession(LunchSessionEntity lunchSessionEntity) {
//        String roomCode = lunchSessionEntity.getRoomCode();
//        log.info("RoomCode: " + roomCode);
//        if (roomCode == null || roomCode.isEmpty()) {
//            lunchSessionEntity.setRestaurants("<Error>: Room Code provided is empty.");
//            log.info(lunchSessionEntity.getRestaurants());
//            return lunchSessionEntity;
//        }
//        LunchSessionEntity existingLunchSessionEntity = lunchSessionRepository.findByRoomCode(roomCode);
//        if (existingLunchSessionEntity == null) {
//            lunchSessionEntity.setRestaurants("<Error>: Room Code does not exist.");
//            log.info(lunchSessionEntity.getRestaurants());
//            return lunchSessionEntity;
//        } else {
//            if (!existingLunchSessionEntity.isActiveStatus()) {
//                log.info("<Error>: Session has ended.");
//                existingLunchSessionEntity.setRestaurantsList(new ArrayList<>());
//                return existingLunchSessionEntity;
//            }
//
//            String existingRestaurants = existingLunchSessionEntity.getRestaurants();
//            if (existingRestaurants == null || existingRestaurants.isEmpty()) {
//                existingLunchSessionEntity.setRestaurantsList(new ArrayList<>());
//            } else {
//                existingLunchSessionEntity.setRestaurantsList(LunchSessionEntity.generateRestaurantsList(existingRestaurants));
//            }
//            log.info("Successfully retrieved Lunch Session for Room Code: " + roomCode);
//            return existingLunchSessionEntity;
//        }
//    }
//
//    public LunchSessionEntity updateLunchSessionRestaurants(LunchSessionEntity lunchSessionEntity) {
//        LunchSessionEntity existingLunchSessionEntity = lunchSessionRepository.findByRoomCode(lunchSessionEntity.getRoomCode());
//        if (existingLunchSessionEntity == null) {
//            lunchSessionEntity.setRestaurants("<Error>: Room Code does not exist.");
//            log.info(lunchSessionEntity.getRestaurants());
//            return lunchSessionEntity;
//        } else {
//            if (!existingLunchSessionEntity.isActiveStatus()) {
//                log.info("<Error>: Session has ended.");
//                existingLunchSessionEntity.setRestaurantsList(new ArrayList<>());
//                return existingLunchSessionEntity;
//            }
//            log.info("Lunch Session with Room Code '" + lunchSessionEntity.getRoomCode() + "' found!");
//
//            String existingRestaurants = existingLunchSessionEntity.getRestaurants();
//            String updatedRestaurants = "";
//            if (existingRestaurants == null || existingRestaurants.isEmpty()) {
//                updatedRestaurants = lunchSessionEntity.getRestaurants();
//            } else {
//                updatedRestaurants = String.join(",", existingRestaurants, lunchSessionEntity.getRestaurants());
//            }
//            lunchSessionRepository.updateLunchSessionRestaurantsByRoomCode(updatedRestaurants, lunchSessionEntity.getRoomCode());
//            existingLunchSessionEntity.setRestaurants(updatedRestaurants);
//            existingLunchSessionEntity.setRestaurantsList(LunchSessionEntity.generateRestaurantsList(updatedRestaurants));
//            log.info("Successfully updated Lunch Session restaurants for Room Code: " + lunchSessionEntity.getRoomCode());
//            return existingLunchSessionEntity;
//        }
//    }
//
//    public LunchSessionEntity updateLunchSessionActiveStatus(LunchSessionEntity lunchSessionEntity) {
//        LunchSessionEntity existingLunchSessionEntity = lunchSessionRepository.findByRoomCode(lunchSessionEntity.getRoomCode());
//        if (existingLunchSessionEntity == null) {
//            lunchSessionEntity.setRestaurants("<Error>: Room Code does not exist.");
//            log.info(lunchSessionEntity.getRestaurants());
//            return lunchSessionEntity;
//        } else {
//            log.info("Lunch Session with Room Code '" + lunchSessionEntity.getRoomCode() + "' found!");
//            String selectedRestaurant = "";
//
//            String existingRestaurants = existingLunchSessionEntity.getRestaurants();
//            if (existingRestaurants == null || existingRestaurants.isEmpty()) {
//                existingLunchSessionEntity.setRestaurantsList(new ArrayList<>());
//            } else {
//                List<String> restaurantsList = LunchSessionEntity.generateRestaurantsList(existingRestaurants);
//                Random random = new Random();
//                selectedRestaurant = restaurantsList.get(random.nextInt(restaurantsList.size()));
//                log.info("Restaurant selected for lunch: " + selectedRestaurant);
//
//                existingLunchSessionEntity.setRestaurantsList(restaurantsList);
//                existingLunchSessionEntity.setRestaurants(selectedRestaurant);
//            }
//
//            lunchSessionRepository.updateLunchSessionRestaurantsAndActiveStatusByRoomCode(selectedRestaurant, false, lunchSessionEntity.getRoomCode());
//            existingLunchSessionEntity.setActiveStatus(false);
//
//            log.info("Successfully ended Lunch Session for Room Code: " + lunchSessionEntity.getRoomCode());
//            return existingLunchSessionEntity;
//        }
//    }

}
