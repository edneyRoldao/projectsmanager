CREATE TABLE projects (
    id              SERIAL          PRIMARY KEY									,
    name            VARCHAR(255)    NOT NULL									,
    description     TEXT   			NOT NULL									,
    init_date       TIMESTAMP       NULL										,
    end_date        TIMESTAMP       NULL										,
    real_end_date   TIMESTAMP       NULL										,
    deleted			BOOLEAN			NOT NULL DEFAULT false						,
    budget          NUMERIC(10,2)   NOT NULL DEFAULT 0							,
    risk            VARCHAR(6)      CHECK (risk IN ('LOW', 'MEDIUM', 'HIGH'))	,
    status          VARCHAR(18)     CHECK (status IN ('STARTED', 'PLANNED', 'ONGOING', 'FINISHED', 'CANCELLED', 'ANALYSIS_DONE', 'UNDER_ANALYSIS', 'ANALYSIS_APPROVED')),
    member_id       INT             NOT NULL									,
    CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES members(id)
);
