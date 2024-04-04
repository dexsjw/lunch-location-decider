package tech.challenge.lunchlocationdecider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

import java.util.Optional;

@Repository
public interface LunchSessionRepository extends JpaRepository<LunchSessionEntity, Long> {

    Optional<LunchSessionEntity> findByRoomCode(String roomCode);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE LunchSession ls SET ls.restaurants = :restaurants WHERE ls.roomCode = :roomCode")
    void updateLunchSessionRestaurantsByRoomCode(@Param("restaurants") String restaurants, @Param("roomCode") String roomCode);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE LunchSession ls SET ls.restaurants = :restaurants, ls.activeStatus = :activeStatus WHERE ls.roomCode = :roomCode")
    void updateLunchSessionRestaurantsAndActiveStatusByRoomCode(@Param("restaurants") String restaurants,
                                                                @Param("activeStatus") boolean activeStatus,
                                                                @Param("roomCode") String roomCode);

}
