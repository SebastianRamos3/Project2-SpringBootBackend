package sports_betting;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favRepo;
    private final AppUserRepository userRepo;
    private final NBATeamRepo nbaRepo;

    public FavoriteService(FavoriteRepository favRepo, AppUserRepository userRepo, NBATeamRepo nbaRepo) {
        this.favRepo = favRepo;
        this.userRepo = userRepo;
        this.nbaRepo = nbaRepo;
    }

    public List<NBATeam> listFavorites(Long userId) {
        return favRepo.findByUserId(userId).stream().map(Favorite::getTeam).toList();
    }

    @Transactional
    public void addFavorite(Long userId, Long nbaTeamId) {
        var user = userRepo.findById(userId).orElseThrow();
        var team = nbaRepo.findById(nbaTeamId).orElseThrow();
        var exists = favRepo.findByUserIdAndTeam_Id(userId, nbaTeamId).isPresent();
        if (exists){
            return;
        }

        var fav = new Favorite();
        fav.setUser(user);
        fav.setTeam(team);
        favRepo.save(fav);
    }

    @Transactional
    public void removeFavorite(Long userId, Long nbaTeamId) {
        var fav = favRepo.findByUserIdAndTeam_Id(userId, nbaTeamId).orElseThrow(() -> new IllegalArgumentException("Favorite not found"));
        favRepo.delete(fav);
    }
}