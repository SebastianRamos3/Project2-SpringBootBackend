package sports_betting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sports_betting.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}