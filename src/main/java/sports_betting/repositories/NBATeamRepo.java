package sports_betting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sports_betting.models.NBATeam;
import java.util.List;

public interface NBATeamRepo extends JpaRepository<NBATeam, Long> {

    List<NBATeam> findByNbaFranchiseTrue();
}