-- public."admin" definition

-- Drop table

-- DROP TABLE public."admin";

CREATE TABLE public."admin" (
	id serial NOT NULL,
	username varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT admin_pkey PRIMARY KEY (id),
	CONSTRAINT admin_username_key UNIQUE (username)
);


-- public.player definition

-- Drop table

-- DROP TABLE public.player;

CREATE TABLE public.player (
	id serial NOT NULL,
	username varchar(64) NOT NULL,
	"password" varchar(64) NOT NULL,
	CONSTRAINT player_pkey PRIMARY KEY (id),
	CONSTRAINT player_username_key UNIQUE (username)
);


-- public.question definition

-- Drop table

-- DROP TABLE public.question;

CREATE TABLE public.question (
	id int4 NOT NULL,
	"text" varchar NOT NULL,
	CONSTRAINT question_pkey PRIMARY KEY (id)
);


-- public.correct_answer definition

-- Drop table

-- DROP TABLE public.correct_answer;

CREATE TABLE public.correct_answer (
	id serial NOT NULL,
	"text" varchar NOT NULL,
	question_id int4 NULL,
	CONSTRAINT correct_answer_pkey PRIMARY KEY (id),
	CONSTRAINT correct_answer_question_id_fkey FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);


-- public.incorrect_answer definition

-- Drop table

-- DROP TABLE public.incorrect_answer;

CREATE TABLE public.incorrect_answer (
	id serial NOT NULL,
	"text" varchar NOT NULL,
	question_id int4 NULL,
	CONSTRAINT incorrect_answer_pkey PRIMARY KEY (id),
	CONSTRAINT incorrect_answer_question_id_fkey FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);


-- public.round definition

-- Drop table

-- DROP TABLE public.round;

CREATE TABLE public.round (
	id serial NOT NULL,
	score int4 NOT NULL,
	"date" timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	player_id int4 NULL,
	CONSTRAINT round_pkey PRIMARY KEY (id),
	CONSTRAINT round_player_id_fkey FOREIGN KEY (player_id) REFERENCES player(id) ON DELETE CASCADE
);
