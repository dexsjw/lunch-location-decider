package tech.challenge.lunchlocationdecider.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;

import java.util.UUID;

public class LunchSessionEntityTest {

    @Test
    public void givenAllArgs_whenConstruct_thenReturnEqual() {
        LunchSessionEntity lunchSessionEntity = new LunchSessionEntity(
                UUID.fromString("E58ED763-928C-4155-BEE9-FDBAAADC15F6"), "e58ed766", true, "Macs,KFC,LJS ");

        String roomId = "E58ED763-928C-4155-BEE9-FDBAAADC15F6";
        String ownerCode = "464f0921";
        Assertions.assertEquals(roomId, lunchSessionEntity.getRoomId().toString().toUpperCase());
        Assertions.assertNotEquals(ownerCode, lunchSessionEntity.getOwnerCode());
    }

}
