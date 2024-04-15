CREATE TABLE IF NOT EXISTS accounts (
    account_number INT PRIMARY KEY,
    account_name VARCHAR(255),
    account_type VARCHAR(50),
    customer_id INT,
    book_balance DECIMAL(10, 2),
    available_balance DECIMAL(10, 2),
    blocked BOOLEAN,
    closed BOOLEAN,
    currency VARCHAR(3),
    iban VARCHAR(255),
    opening_date DATE,
    last_modified_date DATE
    );

-- Sample accounts
INSERT INTO accounts (account_number, account_name, account_type, customer_id, book_balance, available_balance, blocked, closed, currency, iban, opening_date, last_modified_date)
VALUES
    (1001, 'Savings', 'Savings Account', 1, 1000.0, 900.0, FALSE, FALSE, 'USD', 'MT84MALT011000087654MTLCAST001S', '2024-01-01', '2024-01-01'),
    (1002, 'Checking', 'Checking Account', 2, 2000.0, 1800.0, FALSE, FALSE, 'EUR', 'MT84MALT011000087645MTLCAST001S', '2022-01-01', '2024-09-01'),
    (1003, 'Investment', 'Investment Account', 3, 5000.0, 4900.0, FALSE, FALSE, 'USD', 'MT84MALT011000065445MTLCAST001S', '2023-01-01', '2024-11-01'),
    (1004, 'Credit Card', 'Credit Card Account', 4, -500.0, 0.0, FALSE, FALSE, 'USD', 'MT84MALT011000012565MTLCAST001S', '2022-01-01', '2023-04-01'),
    (1005, 'Investment', 'Investment Account', 1, 8000.0, 7800.0, FALSE, FALSE, 'EUR', 'MT84MALT011000012345MTLCAST001S', '2019-01-01', '2024-12-01'),
    (1006, 'Savings', 'Savings Account', 2, 3000.0, 2800.0, FALSE, FALSE, 'USD', 'MT84MALT011000015555MTLCAST001S', '2022-01-01', '2022-06-01'),
    (1007, 'Checking', 'Checking Account', 3, 6000.0, 5900.0, FALSE, FALSE, 'EUR', 'MT84MALT011000098435MTLCAST001S', '2013-01-01', '2024-05-01')
    -- Add more accounts here...
    ;
