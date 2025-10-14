package sports_betting;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class gameController {
    private final List<String> games = List.of("Eagles vs Chiefs", "Lakers vs Celtics");

    @GetMapping
    public List<String> getAllGames() 
    {
        return games;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Server is running!";
    }
}