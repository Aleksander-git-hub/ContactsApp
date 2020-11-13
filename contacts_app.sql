DROP TABLE IF EXISTS users;

CREATE TABLE users
(
	user_id SERIAL NOT NULL,
	user_login varchar(100) NOT NULL,
	user_password varchar(100) NOT NULL,
	user_email varchar(100) NOT NULL,
	PRIMARY KEY(user_id)
);

DELETE FROM users WHERE user_id > 0;