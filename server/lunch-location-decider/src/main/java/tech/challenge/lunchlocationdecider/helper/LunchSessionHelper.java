package tech.challenge.lunchlocationdecider.helper;

import java.util.Optional;
import org.springframework.stereotype.Component;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

@Component
public class LunchSessionHelper {

    public static LunchSessionEntity toLunchSessionEntity(LunchSessionRequestDto lunchSessionRequestDto, String restaurants) {
        restaurants = String.join(",", restaurants, lunchSessionRequestDto.getRestaurant());
        return new LunchSessionEntity(0L, lunchSessionRequestDto.getOwnerCode(), lunchSessionRequestDto.getRoomCode(),
                lunchSessionRequestDto.isActiveStatus(), restaurants);
    }

    public static LunchSessionResponseDto toLunchSessionResponseDto(LunchSessionEntity lunchSessionEntity, boolean hasOwnerCode,
                                                                    String message) {
        return new LunchSessionResponseDto(hasOwnerCode, lunchSessionEntity.getRoomCode(), lunchSessionEntity.isActiveStatus(),
                lunchSessionEntity.getRestaurants(), message);
    }

    public static LunchSessionResponseDto toLunchSessionResponseDto(LunchSessionRequestDto lunchSessionRequestDto, boolean hasOwnerCode,
                                                                    String message) {
        return new LunchSessionResponseDto(hasOwnerCode, lunchSessionRequestDto.getRoomCode(), lunchSessionRequestDto.isActiveStatus(),
                lunchSessionRequestDto.getRestaurant(), message);
    }

    public static LunchSessionEntity lunchSessionEntityNullCheck(LunchSessionEntity lunchSessionEntity) {
        lunchSessionEntity.setId(Optional.ofNullable(lunchSessionEntity.getId()).orElseGet(() -> 0L));
        lunchSessionEntity.setOwnerCode(Optional.ofNullable(lunchSessionEntity.getOwnerCode()).orElseGet(() -> ""));
        lunchSessionEntity.setRoomCode(Optional.ofNullable(lunchSessionEntity.getRoomCode()).orElseGet(() -> ""));
        lunchSessionEntity.setActiveStatus(Optional.of(lunchSessionEntity.isActiveStatus()).orElseGet(() -> true));
        lunchSessionEntity.setRestaurants(Optional.ofNullable(lunchSessionEntity.getRestaurants()).orElseGet(() -> ""));
        return lunchSessionEntity;
    }

    public static LunchSessionRequestDto lunchSessionRequestDtoNullCheck(LunchSessionRequestDto lunchSessionRequestDto) {
        lunchSessionRequestDto.setOwnerCode(Optional.ofNullable(lunchSessionRequestDto.getOwnerCode()).orElseGet(() -> ""));
        lunchSessionRequestDto.setRoomCode(Optional.ofNullable(lunchSessionRequestDto.getRoomCode()).orElseGet(() -> ""));
        lunchSessionRequestDto.setActiveStatus(Optional.of(lunchSessionRequestDto.isActiveStatus()).orElseGet(() -> true));
        lunchSessionRequestDto.setRestaurant(Optional.ofNullable(lunchSessionRequestDto.getRestaurant()).orElseGet(() -> ""));
        return lunchSessionRequestDto;
    }

    public static LunchSessionResponseDto lunchSessionResponseDtoNullCheck(LunchSessionResponseDto lunchSessionResponseDto) {
        lunchSessionResponseDto.setHasOwnerCode(Optional.of(lunchSessionResponseDto.isHasOwnerCode()).orElseGet(() -> false));
        lunchSessionResponseDto.setRoomCode(Optional.ofNullable(lunchSessionResponseDto.getRoomCode()).orElseGet(() -> ""));
        lunchSessionResponseDto.setActiveStatus(Optional.of(lunchSessionResponseDto.isActiveStatus()).orElseGet(() -> false));
        lunchSessionResponseDto.setRestaurants(Optional.ofNullable(lunchSessionResponseDto.getRestaurants()).orElseGet(() -> ""));
        lunchSessionResponseDto.setMessage(Optional.ofNullable(lunchSessionResponseDto.getMessage()).orElseGet(() -> ""));
        return lunchSessionResponseDto;
    }

}
