package tech.challenge.lunchlocationdecider.helper;

import java.util.Optional;
import org.springframework.stereotype.Component;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

@Component
public class LunchSessionHelper {

    public static LunchSessionResponseDto toLunchSessionResponseDto(LunchSessionEntity lunchSessionEntity, boolean hasOwnerCode) {
        return new LunchSessionResponseDto(hasOwnerCode, lunchSessionEntity.getRoomCode(),
            lunchSessionEntity.isActiveStatus(), lunchSessionEntity.getRestaurants(), "");
    }

    public static LunchSessionEntity toLunchSessionEntity(LunchSessionRequestDto lunchSessionRequestDto) {
        return new LunchSessionEntity(0L, lunchSessionRequestDto.getOwnerCode(), lunchSessionRequestDto.getRoomCode(),
                lunchSessionRequestDto.isActiveStatus(), "");
    }

    public static LunchSessionEntity lunchSessionEntityNullCheck(LunchSessionEntity lunchSessionEntity) {
        lunchSessionEntity.setOwnerCode(Optional.ofNullable(lunchSessionEntity.getOwnerCode()).orElseGet(() -> ""));
        lunchSessionEntity.setRoomCode(Optional.ofNullable(lunchSessionEntity.getRoomCode()).orElseGet(() -> ""));
        lunchSessionEntity.setRestaurants(Optional.ofNullable(lunchSessionEntity.getRestaurants()).orElseGet(() -> ""));
        return lunchSessionEntity;
    }

    public static void lunchSessionDtoNullCheck(LunchSessionRequestDto lunchSessionRequestDto) {
        lunchSessionRequestDto.setOwnerCode(Optional.ofNullable(lunchSessionRequestDto.getOwnerCode()).orElseGet(() -> ""));
        lunchSessionRequestDto.setRoomCode(Optional.ofNullable(lunchSessionRequestDto.getRoomCode()).orElseGet(() -> ""));
        lunchSessionRequestDto.setRestaurants(Optional.ofNullable(lunchSessionRequestDto.getRestaurants()).orElseGet(() -> ""));
        lunchSessionRequestDto.setMessage(Optional.ofNullable(lunchSessionRequestDto.getMessage()).orElseGet(() -> ""));
    }

    public static String stringNullCheck(String str) {
        return str == null ? "" : str;
    }

//    public static LunchSessionEntity lunchSessionNullCheck(LunchSessionEntity lunchSessionEntity) {
//        lunchSessionEntity.setOwnerCode(stringNullCheck(lunchSessionEntity.getOwnerCode()));
//        lunchSessionEntity.setRoomCode(stringNullCheck(lunchSessionEntity.getRoomCode()));
//        lunchSessionEntity.setRestaurants(stringNullCheck(lunchSessionEntity.getRestaurants()));
//        return lunchSessionEntity;
//    }

//    public static LunchSessionDto lunchSessionDtoNullCheck(LunchSessionDto lunchSessionDto) {
//        lunchSessionDto.setOwnerCode(stringNullCheck(lunchSessionDto.getOwnerCode()));
//        lunchSessionDto.setRoomCode(stringNullCheck(lunchSessionDto.getRoomCode()));
//        lunchSessionDto.setRestaurants(stringNullCheck(lunchSessionDto.getRestaurants()));
//        lunchSessionDto.setMessage(stringNullCheck(lunchSessionDto.getMessage()));
//        return lunchSessionDto;
//    }

}
