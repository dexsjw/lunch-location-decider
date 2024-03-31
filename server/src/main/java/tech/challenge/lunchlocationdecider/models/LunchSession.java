package tech.challenge.lunchlocationdecider.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LunchSession {

    private long id;
    private String ownerCode;
    private String roomCode;
    private boolean activeStatus;
    private String restaurants;

}
