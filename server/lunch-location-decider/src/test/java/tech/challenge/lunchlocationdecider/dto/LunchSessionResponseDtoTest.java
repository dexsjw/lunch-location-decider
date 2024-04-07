package tech.challenge.lunchlocationdecider.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LunchSessionResponseDtoTest {

    @Test
    public void givenAllArgs_whenConstruct_thenReturnEqual() {
        LunchSessionResponseDto lunchSessionResponseDto = new LunchSessionResponseDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F5", true, true, "Macs,KFC,LJS", "Test Message");

        String roomId = "E58ED763-928C-4155-BEE9-FDBAAADC15F5";
        String restaurants = "Astons,Genki,ToriQ";
        String message = "Hello World";
        Assertions.assertEquals(roomId, lunchSessionResponseDto.getRoomId());
        Assertions.assertNotEquals(restaurants, lunchSessionResponseDto.getRestaurants());
        Assertions.assertNotEquals(message, lunchSessionResponseDto.getMessage());
    }

}
