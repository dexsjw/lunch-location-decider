package tech.challenge.lunchlocationdecider.service;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
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

    public LunchSessionResponseDto newLunchSession() {
        String ownerCode = UUID.randomUUID().toString().substring(0, 8);
        String roomCode = UUID.randomUUID().toString();
        LunchSessionEntity lunchSessionEntity = lunchSessionRepository.save(
                new LunchSessionEntity(0L, ownerCode, roomCode, true, ""));
        log.info("Lunch Session with Room Code '" + roomCode + "' created!");
        return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionEntity, true, ownerCode);
    }

    public LunchSessionResponseDto findLunchSession(LunchSessionRequestDto lunchSessionRequestDto) {
        LunchSessionHelper.lunchSessionRequestDtoNullCheck(lunchSessionRequestDto);
        String roomCode = lunchSessionRequestDto.getRoomCode();
        log.info("RoomCode: " + roomCode);

        Optional<LunchSessionEntity> optLunchSessionEntity = lunchSessionRepository.findByRoomCode(roomCode);
        if (optLunchSessionEntity.isPresent()) {
            log.info("Successfully retrieved Lunch Session for Room Code: " + roomCode);
            LunchSessionEntity lunchSessionEntity = optLunchSessionEntity.get();
            boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
            return lunchSessionActiveStatusCheck(lunchSessionEntity, hasOwnerCode);
        } else {
            return lunchSessionRoomCodeCheck(lunchSessionRequestDto);
        }
    }

    private LunchSessionResponseDto lunchSessionActiveStatusCheck(LunchSessionEntity lunchSessionEntity, boolean hasOwnerCode) {
        String message = lunchSessionEntity.isActiveStatus()
                ? "Successfully retrieved Lunch Session for Room Code '" + lunchSessionEntity.getRoomCode() + "'."
                : "Session for Room Code '" + lunchSessionEntity.getRoomCode() + "' has ended.";
        return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionEntity, hasOwnerCode, message);
    }

    private LunchSessionResponseDto lunchSessionRoomCodeCheck(LunchSessionRequestDto lunchSessionRequestDto) {
        String message = (lunchSessionRequestDto.getRoomCode() == null || lunchSessionRequestDto.getRoomCode().isEmpty())
                ? "Room Code provided is empty."
                : "Room Code '" + lunchSessionRequestDto.getRoomCode() + "' does not exist.";
        return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
    }
    
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
