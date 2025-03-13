CREATE TABLE users
(
    id       UUID PRIMARY KEY             DEFAULT gen_random_uuid(),
    name     VARCHAR(100)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) UNIQUE NOT NULL,
    role     VARCHAR(50)         NOT NULL DEFAULT 'USER'
);
