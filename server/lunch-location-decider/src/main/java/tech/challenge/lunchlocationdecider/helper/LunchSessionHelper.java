package tech.challenge.lunchlocationdecider.helper;

import org.springframework.stereotype.Component;
import tech.challenge.lunchlocationdecider.dto.LunchSessionDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

@Component
public class LunchSessionHelper {

    public static LunchSessionDto toLunchSessionDto(LunchSessionEntity lunchSessionEntity, boolean hasOwnerCode) {
        return new LunchSessionDto(hasOwnerCode, lunchSessionEntity.getOwnerCode(), lunchSessionEntity.getRoomCode(),
                lunchSessionEntity.isActiveStatus(), lunchSessionEntity.getRestaurants(), "");
    }

    public static LunchSessionEntity toLunchSessionEntity(LunchSessionDto lunchSessionDto) {
        return new LunchSessionEntity(0L, lunchSessionDto.getOwnerCode(), lunchSessionDto.getRoomCode(),
                lunchSessionDto.isActiveStatus(), "");
    }

    public static String stringNullCheck(String str) {
        return str == null ? "" : str;
    }

    public static LunchSessionEntity lunchSessionNullCheck(LunchSessionEntity lunchSessionEntity) {
        lunchSessionEntity.setOwnerCode(stringNullCheck(lunchSessionEntity.getOwnerCode()));
        lunchSessionEntity.setRoomCode(stringNullCheck(lunchSessionEntity.getRoomCode()));
        lunchSessionEntity.setRestaurants(stringNullCheck(lunchSessionEntity.getRestaurants()));
        return lunchSessionEntity;
    }

    public static LunchSessionDto lunchSessionDtoNullCheck(LunchSessionDto lunchSessionDto) {
        lunchSessionDto.setOwnerCode(stringNullCheck(lunchSessionDto.getOwnerCode()));
        lunchSessionDto.setRoomCode(stringNullCheck(lunchSessionDto.getRoomCode()));
        lunchSessionDto.setRestaurants(stringNullCheck(lunchSessionDto.getRestaurants()));
        lunchSessionDto.setMessage(stringNullCheck(lunchSessionDto.getMessage()));
        return lunchSessionDto;
    }

}
