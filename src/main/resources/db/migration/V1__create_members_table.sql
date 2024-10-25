CREATE TABLE members (
    id          SERIAL          NOT NULL PRIMARY KEY	,
    name        VARCHAR(255)    NOT NULL				,
    employee    BOOLEAN         NOT NULL				,
    assignment  VARCHAR(100)    NOT NULL				,
    document	VARCHAR(30)		UNIQUE NOT NULL
);
