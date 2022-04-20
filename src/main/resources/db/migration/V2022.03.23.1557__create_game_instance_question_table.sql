CREATE TABLE game_instance_question
(
    question_id                 INTEGER,
    game_instance_id             INTEGER,
    answered_correctly          BOOLEAN,

    PRIMARY KEY (question_id, game_instance_id),
    FOREIGN KEY (question_id) REFERENCES question (id),
    FOREIGN KEY (game_instance_id) REFERENCES game_instance (id)
);