package sports_betting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class NBATeamController {

    private final NBATeamRepo teamRepository;

    public NBATeamController(NBATeamRepo teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<NBATeam> getNbaFranchiseTeams() {
        return teamRepository.findByNbaFranchiseTrue();
    }
}
// redploying the application