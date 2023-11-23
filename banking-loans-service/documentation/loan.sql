
CREATE TYPE loan_status AS ENUM('ACTIVE', 'CLOSED');

CREATE TABLE loan(
    iban text PRIMARY KEY,
    loan_amount NUMERIC(10,2) not null DEFAULT 0,
    individual_id int,
    period INTERVAL YEAR,
    interest_rate NUMERIC(3,2),
    start_date date,
    loan_status text,
    principal NUMERIC(10,2),
    FOREIGN KEY (individual_id)REFERENCES individual(id)
);