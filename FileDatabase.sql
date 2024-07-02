-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 02, 2024 alle 10:51
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webmobile`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `comuni`
--

CREATE TABLE `comuni` (
  `id` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `regione` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `comuni`
--

INSERT INTO `comuni` (`id`, `nome`, `regione`) VALUES
('1', 'Fermo', 'Marche'),
('2', 'Camerino', 'Marche'),
('3', 'Porto San Giorgio', 'Marche'),
('4', 'Macerata', 'Marche');

-- --------------------------------------------------------

--
-- Struttura della tabella `contenuti`
--

CREATE TABLE `contenuti` (
  `id` varchar(50) NOT NULL,
  `descrizione` varchar(50) NOT NULL,
  `puntoDiRiferimento` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `contenuti`
--

INSERT INTO `contenuti` (`id`, `descrizione`, `puntoDiRiferimento`) VALUES
('1', 'Serata quiz indimenticabile con premi da paura', 'Cervellone'),
('2', 'Ottimo circo pieno di animali', 'Circo');

-- --------------------------------------------------------

--
-- Struttura della tabella `contest`
--

CREATE TABLE `contest` (
  `id` varchar(50) NOT NULL,
  `titolo` varchar(50) NOT NULL,
  `descrizione` varchar(50) NOT NULL,
  `dataInizio` varchar(50) NOT NULL,
  `dataFine` varchar(50) NOT NULL,
  `comuneDiRiferimento` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `contest`
--

INSERT INTO `contest` (`id`, `titolo`, `descrizione`, `dataInizio`, `dataFine`, `comuneDiRiferimento`) VALUES
('1', 'Ballo invernale', 'contest per scelta tema ballo scolastico invernale', '2023-10-20', '2023-10-30', 'Camerino'),
('2', 'Ballo fine anno', 'scelta tema ballo fine anno', '2023-05-10', '2023-05-20', 'Fermo');

-- --------------------------------------------------------

--
-- Struttura della tabella `itinerari`
--

CREATE TABLE `itinerari` (
  `id` varchar(50) NOT NULL,
  `titolo` varchar(50) NOT NULL,
  `comuneDiRiferimento` varchar(50) NOT NULL,
  `puntiSelezionati` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `itinerari`
--

INSERT INTO `itinerari` (`id`, `titolo`, `comuneDiRiferimento`, `puntiSelezionati`) VALUES
('1', 'Giornata ottima per Fermo', 'Fermo', 'Circo Party Cervellone'),
('2', 'Serata tranquilla', 'Camerino', 'Cervellone'),
('4', 'fdsfsd', 'Porto San Giorgio', 'Cervellone');

-- --------------------------------------------------------

--
-- Struttura della tabella `puntidirilievo`
--

CREATE TABLE `puntidirilievo` (
  `id` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `comuneDiRiferimento` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `puntidirilievo`
--

INSERT INTO `puntidirilievo` (`id`, `nome`, `comuneDiRiferimento`) VALUES
('1', 'Circo', 'Fermo'),
('2', 'Party', 'Fermo'),
('3', 'Cervellone', 'Porto San Giorgio'),
('4', 'Cervellone', 'Camerino'),
('5', 'Cervellone', 'Fermo');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `comuni`
--
ALTER TABLE `comuni`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `contenuti`
--
ALTER TABLE `contenuti`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `contest`
--
ALTER TABLE `contest`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `itinerari`
--
ALTER TABLE `itinerari`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `puntidirilievo`
--
ALTER TABLE `puntidirilievo`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
