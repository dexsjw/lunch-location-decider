package tech.challenge.lunchlocationdecider.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LunchSessionRequestDtoTest {

    @Test
    public void givenAllArgs_whenConstruct_thenReturnEqual() {
        LunchSessionRequestDto lunchSessionRequestDto = new LunchSessionRequestDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F4", "e58ed764", true, "Macs");

        String roomId = "E58ED763-928C-4155-BEE9-FDBAAADC15F4";
        String restaurant = "KFC";
        Assertions.assertEquals(roomId, lunchSessionRequestDto.getRoomId());
        Assertions.assertNotEquals(restaurant, lunchSessionRequestDto.getRestaurant());
    }

}
