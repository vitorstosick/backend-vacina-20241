create schema if not EXISTS vacinas;

use vacinas;

CREATE table if not EXISTS vacinas.pessoa (
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	DATA_NASCIMENTO DATE NOT NULL,
	SEXO varchar(15) NOT NULL,
	CPF varchar(14) NOT NULL,
	TIPO varchar(255) NOT NULL,
	CONSTRAINT CARTA_pk PRIMARY KEY (ID)
);