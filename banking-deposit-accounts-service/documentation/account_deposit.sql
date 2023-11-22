
CREATE TABLE account_deposit(
    iban text PRIMARY KEY,
    deposit_amount NUMERIC(10, 2) NOT NULL,
    balance NUMERIC(10, 2),
    individual_id int,
    interest_rate NUMERIC(3, 2),
    maturity_date date,
    maturity_months int,
    self_capitalization BOOLEAN,
    maturity_iban text,
    start_date date,
    deposit_status text,
    FOREIGN KEY(individual_id) REFERENCES individual(id)
);