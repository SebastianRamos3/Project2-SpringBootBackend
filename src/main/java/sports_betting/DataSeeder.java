package sports_betting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.OffsetDateTime;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedData(GameRepository repository) {
        return args -> {
            if (repository.count() > 0){
                return;
            }
            Game game1 = new Game();
            game1.setHomeTeam("Lakers");
            game1.setAwayTeam("Celtics");
            game1.setHomeOdds(1.5);
            game1.setAwayOdds(2.5);
            game1.setGameTime(OffsetDateTime.now().plusDays(1));

            Game game2 = new Game();
            game2.setHomeTeam("Bulls");
            game2.setAwayTeam("Knicks");
            game2.setHomeOdds(1.8);
            game2.setAwayOdds(2.0);
            game2.setGameTime(OffsetDateTime.now().plusDays(2));

            repository.save(game1);
            repository.save(game2);
        };
    }
}