create table role (
	id Serial PRIMARY KEY,
	role_type VARCHAR(64)
);

create table question (
	id Serial ,
	text varchar not null,
	primary key (id)
);

create table answer (
	id Serial PRIMARY KEY,
	text VARCHAR NOT NULL,
	correct boolean,
	question_id int REFERENCES question(id) ON DELETE CASCADE
);

create table question (
	id Serial ,
	text varchar not null,
	primary key (id)
);

create table admin (
	id SERIAL primary key,
	username VARCHAR(255) unique NOT null,
	password VARCHAR(255) not null
	
);

create table player (
	id SERIAL primary key,
	username VARCHAR(255) unique NOT null,
	password VARCHAR(255) not null,
	score INT,
	rounds_played INT
);

create table round (
	id SERIAL primary key,
	score INT not null,
	player_id REFERENCES player(id) ON DELETE CASCADE
);

