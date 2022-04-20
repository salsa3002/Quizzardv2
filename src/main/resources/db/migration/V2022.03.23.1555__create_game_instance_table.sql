CREATE TABLE game_instance
(
    id                          SERIAL PRIMARY KEY,
    user_id                     INTEGER,
    daily_game                  BOOLEAN,

    FOREIGN KEY (user_id) REFERENCES "user" (id)
);