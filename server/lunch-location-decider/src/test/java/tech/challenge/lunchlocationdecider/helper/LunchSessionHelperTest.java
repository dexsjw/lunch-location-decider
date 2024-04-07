package tech.challenge.lunchlocationdecider.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

import java.util.UUID;

public class LunchSessionHelperTest {

    @Test
    public void givenLunchSessionRequestDto_whenToLunchSessionEntity_thenReturnLunchSessionEntity() throws IllegalArgumentException {
        LunchSessionRequestDto lunchSessionRequestDto = new LunchSessionRequestDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", "e58ed763", true, "Macs"
        );
        LunchSessionEntity actualLunchSessionEntity = LunchSessionHelper.toLunchSessionEntity(lunchSessionRequestDto, "KFC,LJS");
        LunchSessionEntity expectedLunchSessionEntity = new LunchSessionEntity(
                UUID.fromString("E58ED763-928C-4155-BEE9-FDBAAADC15F3"), "e58ed763", true, "KFC,LJS,Macs"
        );
        Assertions.assertEquals(expectedLunchSessionEntity.getRoomId(), actualLunchSessionEntity.getRoomId());
        Assertions.assertEquals(expectedLunchSessionEntity.getOwnerCode(), actualLunchSessionEntity.getOwnerCode());
        Assertions.assertEquals(expectedLunchSessionEntity.isActiveStatus(), actualLunchSessionEntity.isActiveStatus());
        Assertions.assertEquals(expectedLunchSessionEntity.getRestaurants(), actualLunchSessionEntity.getRestaurants());
        expectedLunchSessionEntity.setActiveStatus(false);
        Assertions.assertNotEquals(expectedLunchSessionEntity.isActiveStatus(), actualLunchSessionEntity.isActiveStatus());
    }

    @Test
    public void givenLunchSessionEntity_whenToLunchSessionResponseDto_thenReturnLunchSessionResponseDto() throws IllegalArgumentException {
        LunchSessionEntity lunchSessionEntity = new LunchSessionEntity(
                UUID.fromString("E58ED763-928C-4155-BEE9-FDBAAADC15F3"), "e58ed763", true, "Macs,KFC,LJS"
        );
        LunchSessionResponseDto actualLunchSessionResponseDto = LunchSessionHelper.toLunchSessionResponseDto(
                lunchSessionEntity, true, "TestMessage");
        LunchSessionResponseDto expectedLunchSessionResponseDto = new LunchSessionResponseDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", true, true, "Macs,KFC,LJS", "TestMessage"
        );
        Assertions.assertEquals(expectedLunchSessionResponseDto.getRoomId(), actualLunchSessionResponseDto.getRoomId().toUpperCase());
        Assertions.assertEquals(expectedLunchSessionResponseDto.isHasOwnerCode(), actualLunchSessionResponseDto.isHasOwnerCode());
        Assertions.assertEquals(expectedLunchSessionResponseDto.isActiveStatus(), actualLunchSessionResponseDto.isActiveStatus());
        Assertions.assertEquals(expectedLunchSessionResponseDto.getRestaurants(), actualLunchSessionResponseDto.getRestaurants());
        Assertions.assertEquals(expectedLunchSessionResponseDto.getMessage(), actualLunchSessionResponseDto.getMessage());
        expectedLunchSessionResponseDto.setHasOwnerCode(false);
        Assertions.assertNotEquals(expectedLunchSessionResponseDto.isHasOwnerCode(), actualLunchSessionResponseDto.isHasOwnerCode());
    }

    @Test
    public void givenLunchSessionRequestDto_whenToLunchSessionResponseDto_thenReturnLunchSessionResponseDto() {
        LunchSessionRequestDto lunchSessionRequestDto = new LunchSessionRequestDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", "e58ed763", true, "Macs"
        );
        LunchSessionResponseDto actualLunchSessionResponseDto = LunchSessionHelper.toLunchSessionResponseDto(
                lunchSessionRequestDto, false, "TestMessage");
        LunchSessionResponseDto expectedLunchSessionResponseDto = new LunchSessionResponseDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", false, true, "Macs", "TestMessage"
        );
        Assertions.assertEquals(expectedLunchSessionResponseDto.getRoomId(), actualLunchSessionResponseDto.getRoomId());
        Assertions.assertEquals(expectedLunchSessionResponseDto.isHasOwnerCode(), actualLunchSessionResponseDto.isHasOwnerCode());
        Assertions.assertEquals(expectedLunchSessionResponseDto.isActiveStatus(), actualLunchSessionResponseDto.isActiveStatus());
        Assertions.assertEquals(expectedLunchSessionResponseDto.getRestaurants(), actualLunchSessionResponseDto.getRestaurants());
        Assertions.assertEquals(expectedLunchSessionResponseDto.getMessage(), actualLunchSessionResponseDto.getMessage());
        expectedLunchSessionResponseDto.setHasOwnerCode(true);
        Assertions.assertNotEquals(expectedLunchSessionResponseDto.isHasOwnerCode(), actualLunchSessionResponseDto.isHasOwnerCode());
    }

    @Test
    public void givenLunchSessionEntity_whenLunchSessionEntityNullCheck_thenReturnNonNullLunchSessionEntity() {
        LunchSessionEntity lunchSessionEntity = new LunchSessionEntity(
                null, null, false, null
        );
        LunchSessionHelper.lunchSessionEntityNullCheck(lunchSessionEntity);
        Assertions.assertNotEquals(null, lunchSessionEntity.getRoomId());
        Assertions.assertNotEquals(null, lunchSessionEntity.getOwnerCode());
        Assertions.assertNotEquals(null, lunchSessionEntity.isActiveStatus());
        Assertions.assertNotEquals(null, lunchSessionEntity.getRestaurants());
    }

    @Test
    public void givenLunchSessionRequestDto_whenLunchSessionRequestDtoNullCheck_thenReturnNonNullLunchSessionRequestDto() {
        LunchSessionRequestDto lunchSessionRequestDto = new LunchSessionRequestDto(
                null, null, false, null
        );
        LunchSessionHelper.lunchSessionRequestDtoNullCheck(lunchSessionRequestDto);
        Assertions.assertNotEquals(null, lunchSessionRequestDto.getRoomId());
        Assertions.assertNotEquals(null, lunchSessionRequestDto.getOwnerCode());
        Assertions.assertNotEquals(null, lunchSessionRequestDto.isActiveStatus());
        Assertions.assertNotEquals(null, lunchSessionRequestDto.getRestaurant());
    }

    @Test
    public void givenLunchSessionResponseDto_whenLunchSessionResponseDtoNullCheck_thenReturnNonNullLunchSessionResponseDto() {
        LunchSessionResponseDto lunchSessionResponseDto = new LunchSessionResponseDto(
                null, false, false, null, null
        );
        LunchSessionHelper.lunchSessionResponseDtoNullCheck(lunchSessionResponseDto);
        Assertions.assertNotEquals(null, lunchSessionResponseDto.getRoomId());
        Assertions.assertNotEquals(null, lunchSessionResponseDto.isHasOwnerCode());
        Assertions.assertNotEquals(null, lunchSessionResponseDto.isActiveStatus());
        Assertions.assertNotEquals(null, lunchSessionResponseDto.getRestaurants());
        Assertions.assertNotEquals(null, lunchSessionResponseDto.getMessage());
    }

}
