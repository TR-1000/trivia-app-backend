
CREATE DATABASE trivia;

create table admin (
	id SERIAL primary key,
	username VARCHAR(255) unique NOT null,
	password VARCHAR(255) not null
	
);

create table player (
	id SERIAL primary key,
	username VARCHAR(64) unique NOT null,
	password VARCHAR(64) not null
);

create table round (
	id SERIAL primary key,
	score INT not null,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	player_id int REFERENCES player(id) ON DELETE CASCADE
);

create table question (
	id int PRIMARY key UNIQUE not null,
	text varchar not null
);

create table incorrect_answer (
	id Serial PRIMARY KEY,
	text VARCHAR NOT NULL,
	question_id int REFERENCES question(id) ON DELETE CASCADE
);

create table correct_answer (
	id Serial PRIMARY KEY,
	text VARCHAR NOT NULL,
	question_id int REFERENCES question(id) ON DELETE CASCADE
);


