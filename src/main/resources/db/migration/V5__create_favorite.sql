CREATE TABLE IF NOT EXISTS favorite (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    nba_team_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES app_user(id),
    FOREIGN KEY (nba_team_id) REFERENCES nba_team(id),
    UNIQUE KEY unique_user_team (user_id, nba_team_id)
);
