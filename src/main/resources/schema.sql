CREATE TABLE todo (
    todoId INTEGER AUTO_INCREMENT PRIMARY KEY,
    createdDate Date,
    completed boolean,
    title VARCHAR(255) NOT NULL
);