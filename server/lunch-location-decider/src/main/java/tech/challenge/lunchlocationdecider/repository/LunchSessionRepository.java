package tech.challenge.lunchlocationdecider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.challenge.lunchlocationdecider.entity.LunchSessionEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LunchSessionRepository extends JpaRepository<LunchSessionEntity, UUID> {

}
