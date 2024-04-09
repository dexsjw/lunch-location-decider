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
        UUID roomId = UUID.randomUUID();
        String roomIdStr = roomId.toString();
        String ownerCode = UUID.randomUUID().toString().substring(0, 8);
        LunchSessionEntity lunchSessionEntity = lunchSessionRepository.saveAndFlush(
                new LunchSessionEntity(roomId, ownerCode, true, ""));
        log.info("Lunch Session with Room ID '" + roomIdStr + "' created!");
        return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionEntity, true, ownerCode);
    }

    public LunchSessionResponseDto processLunchSessionRequestDto(LunchSessionRequestDto lunchSessionRequestDto, String action) {
        LunchSessionHelper.lunchSessionRequestDtoNullCheck(lunchSessionRequestDto);
//        String roomIdStr = lunchSessionRequestDto.getRoomId();
        log.info("Room ID: " + lunchSessionRequestDto.getRoomId());

        try {
            Optional<LunchSessionEntity> optLunchSessionEntity = lunchSessionRepository.findById(UUID.fromString(lunchSessionRequestDto.getRoomId()));
            if (optLunchSessionEntity.isPresent()) {
                LunchSessionEntity lunchSessionEntity = optLunchSessionEntity.get();
                boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
                String msg = executeMethodBasedOnAction(lunchSessionEntity, lunchSessionRequestDto, action);
                return lunchSessionActiveStatusCheck(lunchSessionEntity, hasOwnerCode, msg);
            } else {
                return lunchSessionRoomIdCheck(lunchSessionRequestDto);
            }
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            String message = "<400>Room ID provided is not the right format, please check.";
            log.info(message);
            return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
        }
    }

    private String executeMethodBasedOnAction(LunchSessionEntity lunchSessionEntity, LunchSessionRequestDto lunchSessionRequestDto, String action) {
        String roomId = lunchSessionRequestDto.getRoomId();
        String msg = "";
        switch (action) {
            case "find": {
                msg = "Successfully retrieved Lunch Session for Room ID '" + roomId + "'.";
                break;
            }
            case "update": {
                updateRestaurants(lunchSessionEntity, lunchSessionRequestDto.getRestaurant());
                msg = "Successfully updated Lunch Session restaurants for Room ID '" + roomId + "'.";
                break;
            }
            case "end": {
                chooseRestaurantAndEndSession(lunchSessionEntity, lunchSessionRequestDto.isActiveStatus());
                msg = "Successfully ended Lunch Session for Room Code: '" + roomId + "'.";
                break;
            }
            default: {
                break;
            }
        }
        log.info(msg);
        return msg;
    }

    private void updateRestaurants(LunchSessionEntity lunchSessionEntity, String restaurant) {
        if (restaurant == null || restaurant.isEmpty()) {
            return;
        }
        String restaurants = lunchSessionEntity.getRestaurants();
        restaurants = (restaurants == null || restaurants.isEmpty())
                ? restaurant
                : String.join(",", restaurants, restaurant);
        lunchSessionEntity.setRestaurants(restaurants);
        lunchSessionRepository.saveAndFlush(lunchSessionEntity);
    }

    private void chooseRestaurantAndEndSession(LunchSessionEntity lunchSessionEntity, boolean activeStatus) {
        Random random = new Random();
        List<String> restaurantsList = Arrays.asList(lunchSessionEntity.getRestaurants().split(","));
        String selectedRestaurant = restaurantsList.get(random.nextInt(restaurantsList.size()));
        log.info("Restaurant selected for lunch: " + selectedRestaurant);

        lunchSessionEntity.setRestaurants(selectedRestaurant);
        lunchSessionEntity.setActiveStatus(activeStatus);
        lunchSessionRepository.saveAndFlush(lunchSessionEntity);
    }

    private LunchSessionResponseDto lunchSessionActiveStatusCheck(LunchSessionEntity lunchSessionEntity, boolean hasOwnerCode,
                                                                  String msg) {
        String message = lunchSessionEntity.isActiveStatus()
                ? msg
                : "Session for Room Code '" + lunchSessionEntity.getRoomId().toString() + "' has ended.";
        return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionEntity, hasOwnerCode, message);
    }

    private LunchSessionResponseDto lunchSessionRoomIdCheck(LunchSessionRequestDto lunchSessionRequestDto) {
        String message = (lunchSessionRequestDto.getRoomId() == null || lunchSessionRequestDto.getRoomId().isEmpty())
                ? "<404>Room Code provided is empty."
                : "<404>Room Code '" + lunchSessionRequestDto.getRoomId() + "' does not exist.";
        return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
    }

}
