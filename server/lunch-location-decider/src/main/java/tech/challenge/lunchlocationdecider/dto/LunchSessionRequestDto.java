package tech.challenge.lunchlocationdecider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LunchSessionRequestDto {

    @JsonProperty("ownerCode")
    private String ownerCode;

    @JsonProperty("roomCode")
    private String roomCode;

    @JsonProperty("activeStatus")
    private boolean activeStatus;

    @JsonProperty("restaurant")
    private String restaurant;

}
