DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS contacts;

CREATE TABLE users
(
	user_id SERIAL NOT NULL,
	user_login varchar(100) NOT NULL,
	user_password varchar(100) NOT NULL,
	user_email varchar(100) NOT NULL,
	PRIMARY KEY(user_id)
);

CREATE TABLE contacts
(
    contact_id SERIAL NOT NULL,
    contact_first_name varchar(100) NOT NULL,
    contact_second_name varchar(100) NOT NULL,
    contact_phone_number varchar(100) NOT NULL,
    contact_email varchar(100) NOT NULL,
    PRIMARY KEY(contact_id)
);

DELETE FROM users WHERE user_id > 0;