package tech.challenge.lunchlocationdecider.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;
import tech.challenge.lunchlocationdecider.service.LunchSessionService;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LunchSessionController.class)
public class LunchSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LunchSessionService lunchSessionService;

    @Test
    public void givenNoParams_whenNewLunchSession_thenReturnNewLunchSession() throws Exception {
        LunchSessionResponseDto lunchSessionResponseDto = new LunchSessionResponseDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F6", true, true, "Macs", "");
        when(lunchSessionService.newLunchSession()).thenReturn(lunchSessionResponseDto);
        this.mockMvc.perform(get("/lunch/session/new"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
