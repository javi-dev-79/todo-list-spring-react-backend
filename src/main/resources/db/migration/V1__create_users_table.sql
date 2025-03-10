CREATE TABLE users
(
    id    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name  VARCHAR(100)        NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);
