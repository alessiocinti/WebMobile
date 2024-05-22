USE WebMobile;

CREATE TABLE comuni (
	id INT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
    regione VARCHAR(50) NOT NULL
);

CREATE TABLE contenuto (
	id INT PRIMARY KEY,
	descrizione VARCHAR(50) NOT NULL,
    puntoDiRiferimento VARCHAR(50) NOT NULL
);

CREATE TABLE contest (
	id INT PRIMARY KEY,
	titolo VARCHAR(50) NOT NULL,
    descrizione VARCHAR(50) NOT NULL,
    dataInizio VARCHAR(50) NOT NULL,
    dataFine VARCHAR(50) NOT NULL,
    comuneDiRiferimento VARCHAR(50) NOT NULL
);


CREATE TABLE itinerario (
	id INT PRIMARY KEY,
	titolo VARCHAR(50) NOT NULL,
    comuneDiRiferimento VARCHAR(50) NOT NULL,
    puntiSelezionati VARCHAR(250) NOT NULL
);

CREATE TABLE puntiDiRilievo (
	id INT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
    comuneDiRiferimento VARCHAR(50) NOT NULL
);



