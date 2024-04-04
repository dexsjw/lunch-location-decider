package tech.challenge.lunchlocationdecider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lunch_session")
public class LunchSessionEntity {

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

    @Override
    public String toString() {
        return "LunchSession{" +
                "id=" + id + "\n" +
                ", ownerCode='" + ownerCode + "'\n" +
                ", roomCode='" + roomCode + "'\n" +
                ", activeStatus=" + activeStatus + "\n" +
                ", restaurants='" + restaurants + "'\n" +
                '}';
    }

}
