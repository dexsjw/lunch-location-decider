package tech.challenge.lunchlocationdecider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LunchSessionDto {

    @JsonProperty("hasOwnerCode")
    private boolean hasOwnerCode;

    @JsonProperty("ownerCode")
    private String ownerCode;

    @JsonProperty("roomCode")
    private String roomCode;

    @JsonProperty("activeStatus")
    private boolean activeStatus;

    @JsonProperty("restaurants")
    private String restaurants;

    @JsonProperty("message")
    private String message;

}
