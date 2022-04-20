CREATE TABLE "user"
(
    id                          SERIAL PRIMARY KEY,
    username                    VARCHAR(20),
    password                    VARCHAR(100),
    email                       VARCHAR(50),
    role                        VARCHAR(20),
    created_at                  TIMESTAMP,
    total_games_played          INTEGER DEFAULT 0
);