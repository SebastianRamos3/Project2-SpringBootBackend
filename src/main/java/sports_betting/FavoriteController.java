package sports_betting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/favorites")
public class FavoriteController {
    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    //Get
    @GetMapping
    public List<NBATeam> list(@PathVariable Long userId) {
        return service.listFavorites(userId);
    }

    //Post
    @PostMapping
    public ResponseEntity<?> add(@PathVariable Long userId, @RequestParam Long teamId) {
        service.addFavorite(userId, teamId);
        return ResponseEntity.status(201).build();
    }

    //Delete
    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> remove(@PathVariable Long userId, @PathVariable Long teamId) {
        service.removeFavorite(userId, teamId);
        return ResponseEntity.noContent().build();
    }

    //still need something for PUT
}