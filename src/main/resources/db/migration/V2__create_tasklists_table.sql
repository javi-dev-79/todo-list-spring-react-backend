CREATE TABLE task_lists
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name    VARCHAR(100) NOT NULL,
    user_id UUID         NOT NULL,
    CONSTRAINT fk_tasklist_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
