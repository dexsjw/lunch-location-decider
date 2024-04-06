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
        String roomIdStr = lunchSessionRequestDto.getRoomId();
        log.info("Room ID: " + roomIdStr);

        try {
            Optional<LunchSessionEntity> optLunchSessionEntity = lunchSessionRepository.findById(UUID.fromString(lunchSessionRequestDto.getRoomId()));
            if (optLunchSessionEntity.isPresent()) {
                LunchSessionEntity lunchSessionEntity = optLunchSessionEntity.get();

                String msg = "";
                switch (action) {
                    case "find": {
                        msg = "Successfully retrieved Lunch Session for Room ID '" + roomIdStr + "'.";
                        break;
                    }
                    case "update": {
                        lunchSessionEntity.setRestaurants(updateRestaurants(lunchSessionEntity.getRestaurants(), lunchSessionRequestDto.getRestaurant()));
                        lunchSessionRepository.saveAndFlush(lunchSessionEntity);
                        msg = "Successfully updated Lunch Session restaurants for Room ID '" + roomIdStr + "'.";
                        break;
                    }
                    case "end": {
                        chooseRestaurantAndEndSession(lunchSessionEntity, lunchSessionRequestDto.isActiveStatus());
                        msg = "Successfully ended Lunch Session for Room Code: '" + roomIdStr + "'.";
                        break;
                    }
                    default: {
                        break;
                    }
                }
                log.info(msg);

                boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
                return lunchSessionActiveStatusCheck(lunchSessionEntity, hasOwnerCode, msg);
            } else {
                return lunchSessionRoomIdCheck(lunchSessionRequestDto);
            }
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            String message = "Room ID provided is not the right format, please check.";
            return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
        }
    }

//    public LunchSessionResponseDto findLunchSession(LunchSessionRequestDto lunchSessionRequestDto) {
//        LunchSessionHelper.lunchSessionRequestDtoNullCheck(lunchSessionRequestDto);
//        String roomIdStr = lunchSessionRequestDto.getRoomId();
//        log.info("Room ID: " + roomIdStr);
//
//        try {
//            Optional<LunchSessionEntity> optLunchSessionEntity = lunchSessionRepository.findById(UUID.fromString(lunchSessionRequestDto.getRoomId()));
//            if (optLunchSessionEntity.isPresent()) {
//                String msg = "Successfully retrieved Lunch Session for Room ID '" + roomIdStr + "'.";
//
//                LunchSessionEntity lunchSessionEntity = optLunchSessionEntity.get();
//                log.info(msg);
//
//                boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
//                return lunchSessionActiveStatusCheck(lunchSessionEntity, hasOwnerCode, msg);
//            } else {
//                return lunchSessionRoomIdCheck(lunchSessionRequestDto);
//            }
//        } catch (IllegalArgumentException iae) {
//            iae.printStackTrace();
//            String message = "Room ID provided is not the right format, please check.";
//            return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
//        }
//    }

//    public LunchSessionResponseDto updateLunchSessionRestaurants(LunchSessionRequestDto lunchSessionRequestDto) {
//        LunchSessionHelper.lunchSessionRequestDtoNullCheck(lunchSessionRequestDto);
//        String roomIdStr = lunchSessionRequestDto.getRoomId();
//        log.info("Room ID: " + roomIdStr);
//
//        try {
//            Optional<LunchSessionEntity> optLunchSessionEntity = lunchSessionRepository.findById(UUID.fromString(lunchSessionRequestDto.getRoomId()));
//            if (optLunchSessionEntity.isPresent()) {
//                String msg = "Successfully updated Lunch Session restaurants for Room ID '" + roomIdStr + "'.";
//
//                LunchSessionEntity lunchSessionEntity = optLunchSessionEntity.get();
//                lunchSessionEntity.setRestaurants(updateRestaurants(lunchSessionEntity.getRestaurants(), lunchSessionRequestDto.getRestaurant()));
//                lunchSessionRepository.saveAndFlush(lunchSessionEntity);
//                log.info(msg);
//
//                boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
//                return lunchSessionActiveStatusCheck(lunchSessionEntity, hasOwnerCode, msg);
//            } else {
//                return lunchSessionRoomIdCheck(lunchSessionRequestDto);
//            }
//        } catch (IllegalArgumentException iae) {
//            iae.printStackTrace();
//            String message = "Room ID provided is not the right format, please check.";
//            return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
//        }
//    }

//    public LunchSessionResponseDto endLunchSession(LunchSessionRequestDto lunchSessionRequestDto) {
//        LunchSessionHelper.lunchSessionRequestDtoNullCheck(lunchSessionRequestDto);
//        String roomIdStr = lunchSessionRequestDto.getRoomId();
//        log.info("Room ID: " + roomIdStr);
//
//        try {
//            Optional<LunchSessionEntity> optLunchSessionEntity = lunchSessionRepository.findById(UUID.fromString(lunchSessionRequestDto.getRoomId()));
//            if (optLunchSessionEntity.isPresent()) {
//                String msg = "Successfully ended Lunch Session for Room Code: '" + roomIdStr + "'.";
//
//                LunchSessionEntity lunchSessionEntity = optLunchSessionEntity.get();
//                Random random = new Random();
//                List<String> restaurantsList = Arrays.asList(lunchSessionEntity.getRestaurants().split(","));
//                String selectedRestaurant = restaurantsList.get(random.nextInt(restaurantsList.size()));
//                log.info("Restaurant selected for lunch: " + selectedRestaurant);
//
//                lunchSessionEntity.setRestaurants(selectedRestaurant);
//                lunchSessionEntity.setActiveStatus(lunchSessionRequestDto.isActiveStatus());
//                lunchSessionRepository.saveAndFlush(lunchSessionEntity);
//                log.info(msg);
//
//                boolean hasOwnerCode = lunchSessionRequestDto.getOwnerCode().equals(lunchSessionEntity.getOwnerCode());
//                return lunchSessionActiveStatusCheck(lunchSessionEntity, hasOwnerCode, msg);
//            } else {
//                return lunchSessionRoomIdCheck(lunchSessionRequestDto);
//            }
//        } catch (IllegalArgumentException iae) {
//            iae.printStackTrace();
//            String message = "Room ID provided is not the right format, please check.";
//            return LunchSessionHelper.toLunchSessionResponseDto(lunchSessionRequestDto, false, message);
//        }
//    }

    private String updateRestaurants(String restaurants, String restaurant) {
        if (restaurants == null || restaurants.isEmpty()) {
            restaurants = restaurant;
        } else {
            restaurants = String.join(",", restaurants, restaurant);
        }
        return restaurants;
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
