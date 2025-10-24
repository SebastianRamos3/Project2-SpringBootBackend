package sports_betting;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favRepo;
    private final AppUserRepository userRepo;
    private final TeamRepository teamRepo;

    public FavoriteService(FavoriteRepository favRepo, AppUserRepository userRepo, TeamRepository teamRepo) {
        this.favRepo = favRepo;
        this.userRepo = userRepo;
        this.teamRepo = teamRepo;
    }

    @Transactional
    public void addFavorite(Long userId, Long teamId) {
        var user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        var team = teamRepo.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found"));

        //This will prevent duplicates
        favRepo.findByUserIdAndTeamId(userId, teamId).ifPresent(f -> {
            throw new IllegalStateException("Already in favorites");
        });

        Favorite fav = new Favorite();
        fav.setUser(user);
        fav.setTeam(team);
        favRepo.save(fav);
    }

    @Transactional
    public void removeFavorite(Long userId, Long teamId) {
        var fav = favRepo.findByUserIdAndTeamId(userId, teamId)
                         .orElseThrow(() -> new IllegalArgumentException("Favorite not found"));
        favRepo.delete(fav);
    }

    public List<Team> listFavorites(Long userId) {
        return favRepo.findByUserId(userId).stream()
                      .map(Favorite::getTeam)
                      .toList();
    }

}