CREATE TABLE members (
    id          SERIAL          PRIMARY KEY	,
    name        VARCHAR(255)    NOT NULL	,
    employee    BOOLEAN         NOT NULL	,
    assignment  VARCHAR(100)    NOT NULL
);
