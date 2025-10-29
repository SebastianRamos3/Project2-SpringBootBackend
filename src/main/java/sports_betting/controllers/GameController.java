package sports_betting.controllers;

import org.springframework.web.bind.annotation.*;
import sports_betting.models.Game;
import sports_betting.repositories.GameRepository;
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
    // im now able to get this work through and http link instead of locally
    // steps to make it work.
    // 1. make sure its deployed on heroku
    //
    // run v In terminal to have it work on web and pop up data
    // heroku ps:scale web=1 --app sports-betting

    // can either curl in terminal or paste link into browser
    // curl https://sports-betting-48b2640f3be7.herokuapp.com/api/v1/games/ping

    // atleast for me, I can now try to make it work with the OG code
}