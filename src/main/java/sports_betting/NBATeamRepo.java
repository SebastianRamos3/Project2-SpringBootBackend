package sports_betting;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NBATeamRepo extends JpaRepository<NBATeam, Long> {

    List<NBATeam> findByNbaFranchiseTrue();
}