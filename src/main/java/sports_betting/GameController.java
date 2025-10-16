package sports_betting;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameRepository repo;

    public GameController(GameRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return repo.findAll();
    }

    @GetMapping("/ping")
    public String ping() {
        return "Server is running!";
    }
}