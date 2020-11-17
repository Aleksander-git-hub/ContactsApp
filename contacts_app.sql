DROP TABLE IF EXISTS contacts;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_id_seq;

CREATE SEQUENCE user_id_seq;

CREATE TABLE users
(
	user_id varchar NOT NULL DEFAULT nextval('user_id_seq'),
	user_login varchar(100) NOT NULL,
	user_password varchar(100) NOT NULL,
	user_email varchar(100) NOT NULL,
	PRIMARY KEY(user_id)
);

CREATE TABLE contacts
(
    contact_user_id varchar NOT NULL,
    contact_id SERIAL NOT NULL,
    contact_first_name varchar(100) NOT NULL,
    contact_second_name varchar(100) NOT NULL,
    contact_phone_number varchar(100) NOT NULL,
    contact_email varchar(100) NOT NULL,
    PRIMARY KEY(contact_id),
    FOREIGN KEY(contact_user_id) REFERENCES users(user_id) ON DELETE RESTRICT
);

INSERT INTO contacts(contact_user_id, contact_first_name, contact_second_name, contact_phone_number, contact_email)
VALUES (
    (SELECT user_id FROM users WHERE user_login = '' AND user_password = ''),
    'name', 'secondName', 'phoneNumber', 'email')
);

DELETE FROM contacts WHERE contact_user_id = '2' AND contact_first_name = 'Илья' AND
	contact_second_name = 'Морозов';
