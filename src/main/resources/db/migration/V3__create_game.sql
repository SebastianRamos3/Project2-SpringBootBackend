CREATE TABLE IF NOT EXISTS game (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    home_team VARCHAR(255),
    away_team VARCHAR(255),
    home_odds DOUBLE,
    away_odds DOUBLE,
    game_time DATETIME(6)
);

