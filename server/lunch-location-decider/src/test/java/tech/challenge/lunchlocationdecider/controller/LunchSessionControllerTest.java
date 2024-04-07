package tech.challenge.lunchlocationdecider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.challenge.lunchlocationdecider.dto.LunchSessionRequestDto;
import tech.challenge.lunchlocationdecider.dto.LunchSessionResponseDto;
import tech.challenge.lunchlocationdecider.service.LunchSessionService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LunchSessionController.class)
public class LunchSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LunchSessionService lunchSessionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getNewLunchSessionFromServiceSuccess() throws Exception {
        LunchSessionResponseDto expectedLunchSessionResponseDto = new LunchSessionResponseDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F6", true, true, "Macs", ""
        );
        when(lunchSessionService.newLunchSession()).thenReturn(expectedLunchSessionResponseDto);
        mockMvc.perform(get("/lunch/session/new"))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedLunchSessionResponseDto)));
    }

    @Test
    public void getNewLunchSessionFromServiceFail() throws Exception {
        LunchSessionResponseDto expectedLunchSessionResponseDto = new LunchSessionResponseDto(
                "E58ED763-928C-4155-BEE9-FDBAAADC15F6", true, true, "Macs", "<400>Bad Request"
        );
        when(lunchSessionService.newLunchSession()).thenReturn(expectedLunchSessionResponseDto);
        mockMvc.perform(get("/lunch/session/new"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedLunchSessionResponseDto)));
    }

//    @Test
//    public void findLunchSessionByRoomIdSuccess() throws Exception {
//        LunchSessionRequestDto lunchSessionRequestDto = new LunchSessionRequestDto(
//                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", "e58ed763", true, "Macs"
//        );
//        LunchSessionResponseDto expectedLunchSessionResponseDto = new LunchSessionResponseDto(
//                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", false, true, "Macs,KFC,LJS", ""
//        );
//
//        when(lunchSessionService.processLunchSessionRequestDto(lunchSessionRequestDto, "find")).thenReturn(expectedLunchSessionResponseDto);
//        mockMvc.perform(post("/lunch/session/find")
//                        .content(objectMapper.writeValueAsString(lunchSessionRequestDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedLunchSessionResponseDto)));
//    }

//    @Test
//    public void findLunchSessionByRoomIdFail() throws Exception {
//        LunchSessionRequestDto lunchSessionRequestDto = new LunchSessionRequestDto(
//                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", "e58ed763", true, "Macs"
//        );
//        LunchSessionResponseDto expectedLunchSessionResponseDto = new LunchSessionResponseDto(
//                "E58ED763-928C-4155-BEE9-FDBAAADC15F3", false, true, "Macs,KFC,LJS", "<404>Not Found"
//        );
//
//        when(lunchSessionService.processLunchSessionRequestDto(lunchSessionRequestDto, "find")).thenReturn(expectedLunchSessionResponseDto);
//        mockMvc.perform(post("/lunch/session/find", lunchSessionRequestDto)
//                        .content(objectMapper.writeValueAsString(lunchSessionRequestDto))
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isNotFound())
//                .andExpect(content().json(objectMapper.writeValueAsString(expectedLunchSessionResponseDto)));
//    }

}
