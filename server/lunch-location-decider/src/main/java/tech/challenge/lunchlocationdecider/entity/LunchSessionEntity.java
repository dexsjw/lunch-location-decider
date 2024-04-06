package tech.challenge.lunchlocationdecider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lunch_session")
public class LunchSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "owner_code", nullable = false)
    private String ownerCode;

//    @Column(name="room_code", nullable = false, unique = true)
//    private String roomCode;

    @Column(name="active_status", nullable = false)
    private boolean activeStatus;

    @Column(name="restaurants")
    private String restaurants;

    @Override
    public String toString() {
        return "LunchSessionEntity{" +
                "roomId=" + roomId +
                ", ownerCode='" + ownerCode + '\'' +
                ", activeStatus=" + activeStatus +
                ", restaurants='" + restaurants + '\'' +
                '}';
    }
}
