CREATE TABLE public.senha (
	u_token varchar(128) NOT NULL,
	s_token varchar(256) NOT NULL,
	CONSTRAINT senha_pk PRIMARY KEY (u_token),
	CONSTRAINT senha_unique_usuario_id UNIQUE (u_token)
);