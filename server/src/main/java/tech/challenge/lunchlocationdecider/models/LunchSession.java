package tech.challenge.lunchlocationdecider.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lunch_session")
public class LunchSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner_code", nullable = false, unique = true)
    private String ownerCode;

    @Column(name="room_code", nullable = false, unique = true)
    private String roomCode;

    @Column(name="active_status", nullable = false)
    private boolean activeStatus;

    @Column(name="restaurants")
    private String restaurants;

    @Transient
    private List<String> restaurantsList;

    public String generateRestaurantsString(List<String> restaurantsList) {
        return String.join(",", restaurantsList);
    }

}
