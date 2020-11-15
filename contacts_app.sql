DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS users;

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
    contact_user_id integer NOT NULL,
    contact_id SERIAL NOT NULL,
    contact_first_name varchar(100) NOT NULL,
    contact_second_name varchar(100) NOT NULL,
    contact_phone_number varchar(100) NOT NULL,
    contact_email varchar(100) NOT NULL,
    PRIMARY KEY(contact_id),
    FOREIGN KEY(contact_user_id) REFERENCES users(user_id) ON DELETE RESTRICT
);
