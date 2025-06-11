CREATE TABLE users (
	user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(60) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE todo (
<<<<<<< HEAD
    todoId INTEGER AUTO_INCREMENT PRIMARY KEY,
    createdDate Date,
    completed boolean,
    title VARCHAR(255) NOT NULL
);
=======
	todo_id INTEGER AUTO_INCREMENT PRIMARY KEY,
	user_id INTEGER NOT NULL,
	completed BOOLEAN,
	title VARCHAR(255) NOT NULL,
	priority INTEGER,
	completion_date TIMESTAMP,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);
>>>>>>> d11dd826a6c4073d9f94be97ffb7b4f9a0d43b86
