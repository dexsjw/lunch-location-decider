package tech.challenge.lunchlocationdecider.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.challenge.lunchlocationdecider.models.LunchSession;

@Repository
public interface LunchSessionRepository extends JpaRepository<LunchSession, Long> {
}
