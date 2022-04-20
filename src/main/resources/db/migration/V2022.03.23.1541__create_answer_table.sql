CREATE TABLE answer
(
    id                          SERIAL PRIMARY KEY,
    question_id                 INTEGER,
    answer                      VARCHAR(100),
    correct                     BOOLEAN,

    FOREIGN KEY (question_id) REFERENCES question (id)
);