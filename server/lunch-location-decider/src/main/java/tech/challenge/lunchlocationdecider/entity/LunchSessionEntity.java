package tech.challenge.lunchlocationdecider.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
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

    @Column(name="active_status", nullable = false)
    private boolean activeStatus;

    @Column(name="restaurants")
    private String restaurants;

}
