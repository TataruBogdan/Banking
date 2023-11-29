
CREATE TYPE transaction_status AS ENUM(
    'NEW', 'APPROVED', 'REJECTED', 'FINISHED'
    );
-- cum stiu ce type de cont selectez, contul nu are account_type
CREATE TYPE account_type AS ENUM ('CURRENT', 'DEPOSIT', 'LOAN');

-- list all enum types along with their values,
SELECT t.typname AS enum_type, e.enumlabel AS enum_value
FROM pg_enum e
         JOIN pg_type t ON e.enumtypid = t.oid;

-- DROP TABLE IF EXISTS transaction;

--account este contul : account_current, account_deposit, account_loan ???
CREATE TABLE transaction (
         transaction_id text PRIMARY KEY,
         from_individual_id INTEGER,
         from_iban text,
         from_account_type account_type,
         to_individual_id INTEGER,
         to_iban text,
         to_account_type account_type,
         transaction_amount INTEGER,
         transaction_timestamp TIMESTAMP,
         transaction_status text
);