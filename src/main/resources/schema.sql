CREATE TABLE users (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE tasks (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title CLOB,
    detail CLOB,
    limit_at TIME,
    completed_at TIME
);