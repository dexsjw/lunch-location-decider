package tech.challenge.lunchlocationdecider.helper;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

@Component
public class LunchSessionHelper {

    public static LunchSessionEntity toLunchSessionEntity(LunchSessionRequestDto lunchSessionRequestDto, String restaurants)
            throws IllegalArgumentException {
        restaurants = String.join(",", restaurants, lunchSessionRequestDto.getRestaurant());
        UUID roomId = UUID.fromString(lunchSessionRequestDto.getRoomId());
        return new LunchSessionEntity(roomId, lunchSessionRequestDto.getOwnerCode(), lunchSessionRequestDto.isActiveStatus(), restaurants);
    }

    public static LunchSessionResponseDto toLunchSessionResponseDto(LunchSessionEntity lunchSessionEntity, boolean hasOwnerCode,
                                                                    String message) {
        return new LunchSessionResponseDto(lunchSessionEntity.getRoomId().toString(), hasOwnerCode, lunchSessionEntity.isActiveStatus(),
                lunchSessionEntity.getRestaurants(), message);
    }

    public static LunchSessionResponseDto toLunchSessionResponseDto(LunchSessionRequestDto lunchSessionRequestDto, boolean hasOwnerCode,
                                                                    String message) {
        return new LunchSessionResponseDto(lunchSessionRequestDto.getRoomId(), hasOwnerCode, lunchSessionRequestDto.isActiveStatus(),
                lunchSessionRequestDto.getRestaurant(), message);
    }

    public static LunchSessionEntity lunchSessionEntityNullCheck(LunchSessionEntity lunchSessionEntity) {
        lunchSessionEntity.setRoomId(Optional.ofNullable(lunchSessionEntity.getRoomId()).orElseGet(UUID::randomUUID));
        lunchSessionEntity.setOwnerCode(Optional.ofNullable(lunchSessionEntity.getOwnerCode()).orElseGet(
                () -> UUID.randomUUID().toString().substring(0, 8)));
        lunchSessionEntity.setActiveStatus(Optional.of(lunchSessionEntity.isActiveStatus()).orElseGet(() -> true));
        lunchSessionEntity.setRestaurants(Optional.ofNullable(lunchSessionEntity.getRestaurants()).orElseGet(() -> ""));
        return lunchSessionEntity;
    }

    public static LunchSessionRequestDto lunchSessionRequestDtoNullCheck(LunchSessionRequestDto lunchSessionRequestDto) {
        lunchSessionRequestDto.setRoomId(Optional.ofNullable(lunchSessionRequestDto.getRoomId()).orElseGet(() -> ""));
        lunchSessionRequestDto.setOwnerCode(Optional.ofNullable(lunchSessionRequestDto.getOwnerCode()).orElseGet(() -> ""));
        lunchSessionRequestDto.setActiveStatus(Optional.of(lunchSessionRequestDto.isActiveStatus()).orElseGet(() -> true));
        lunchSessionRequestDto.setRestaurant(Optional.ofNullable(lunchSessionRequestDto.getRestaurant()).orElseGet(() -> ""));
        return lunchSessionRequestDto;
    }

    public static LunchSessionResponseDto lunchSessionResponseDtoNullCheck(LunchSessionResponseDto lunchSessionResponseDto) {
        lunchSessionResponseDto.setRoomId(Optional.ofNullable(lunchSessionResponseDto.getRoomId()).orElseGet(() -> ""));
        lunchSessionResponseDto.setHasOwnerCode(Optional.of(lunchSessionResponseDto.isHasOwnerCode()).orElseGet(() -> false));
        lunchSessionResponseDto.setActiveStatus(Optional.of(lunchSessionResponseDto.isActiveStatus()).orElseGet(() -> false));
        lunchSessionResponseDto.setRestaurants(Optional.ofNullable(lunchSessionResponseDto.getRestaurants()).orElseGet(() -> ""));
        lunchSessionResponseDto.setMessage(Optional.ofNullable(lunchSessionResponseDto.getMessage()).orElseGet(() -> ""));
        return lunchSessionResponseDto;
    }

}
