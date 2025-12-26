CREATE DATABASE expenseTrackerDB;
DROP DATABASE EXPENSE_TRACKER;
USE expenseTrackerDB;

CREATE TABLE Users(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    mobile VARCHAR(100),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    active_yn int DEFAULT 1
);

CREATE TABLE Categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    name VARCHAR(100),
    description VARCHAR(200),
    transaction_type ENUM('INCOME','EXPENSE') NOT NULL DEFAULT 'EXPENSE',
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    active_yn int DEFAULT 1,
    icon_url VARCHAR(100),
    FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE Transactions(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    amount DECIMAL(10,2),
    category_id INT NOT NULL,
    date_of_transaction DATE NOT NULL,
    notes VARCHAR(200),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    active_yn int DEFAULT 1,
    FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY(category_id) REFERENCES Categories(id) ON DELETE CASCADE
);

INSERT INTO Transactions (user_id, amount, category_id, date_of_transaction, notes)
VALUES
(1, 50000.00, 1, '2025-01-01', 'January Salary'),
(1, 250.00, 2, '2025-01-02', 'Lunch'),
(1, 180.00, 2, '2025-01-02', 'Dinner'),
(1, 1200.00, 3, '2025-01-03', 'Cab to airport'),
(1, 3000.00, 3, '2025-01-04', 'Train tickets');

INSERT INTO Categories (user_id, name, description, transaction_type, icon_url)
VALUES
(1, 'Salary', 'Monthly salary income', 'INCOME', 'salary.png'),
(1, 'Food', 'Daily food expenses', 'EXPENSE', 'food.png'),
(1, 'Travel', 'Travel and transport expenses', 'EXPENSE', 'travel.png');

INSERT INTO Users (name, username, email, password, mobile)
VALUES ('Gaurav Sharma', 'gaurav01', 'gaurav@example.com', 'hashed_password_123', '9876543210');
