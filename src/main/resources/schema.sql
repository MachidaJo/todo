CREATE TABLE todo (
    todo_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    todo_date Date,
    completed boolean,
    title VARCHAR(255) NOT NULL
);