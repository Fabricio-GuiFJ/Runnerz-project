CREATE TABLE IF NOT EXISTS Run (
    id INTEGER NOT NULL PRIMARY KEY,
    title varchar(250) NOT NULL,
    started_On timestamp NOT NULL,
    completed_On timestamp NOT NULL,
    miles INTEGER NOT NULL,
    location varchar(10) NOT NULL,
    version INT
);