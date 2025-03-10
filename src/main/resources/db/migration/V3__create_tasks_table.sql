CREATE TABLE tasks
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    end_date     TIMESTAMP WITH TIME ZONE,
    status       VARCHAR(50)  NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
    task_list_id UUID         NOT NULL,
    CONSTRAINT fk_task_tasklist FOREIGN KEY (task_list_id) REFERENCES task_lists (id) ON DELETE CASCADE
);
