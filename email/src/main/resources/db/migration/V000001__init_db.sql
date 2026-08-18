CREATE TYPE email_status AS ENUM ('SENT', 'ERROR');

CREATE TABLE emails (
    id UUID NOT NULL PRIMARY KEY,
    user_id UUID,
    email_from VARCHAR(255),
    email_to VARCHAR(255),
    subject VARCHAR(255),
    text TEXT,
    send_date_time TIMESTAMP WITH TIME ZONE,
    status EMAIL_STATUS
);
