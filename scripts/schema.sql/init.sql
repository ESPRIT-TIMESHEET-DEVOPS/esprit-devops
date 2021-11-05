CREATE DATABASE IF NOT EXISTS timesheet;
USE timesheet;

-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Oct 29, 2021 at 10:03 AM
-- Server version: 8.0.27
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `timesheet`
--

-- --------------------------------------------------------

--
-- Table structure for table `contrat`
--

CREATE TABLE `contrat` (
                           `reference` int NOT NULL,
                           `date_debut` date DEFAULT NULL,
                           `salaire` float NOT NULL,
                           `telephone` float NOT NULL,
                           `type_contrat` varchar(255) DEFAULT NULL,
                           `employe_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `departement`
--

CREATE TABLE `departement` (
                               `id` int NOT NULL,
                               `name` varchar(255) DEFAULT NULL,
                               `entreprise_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `departement_employes`
--

CREATE TABLE `departement_employes` (
                                        `departements_id` int NOT NULL,
                                        `employes_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE `employe` (
                           `id` int NOT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `is_actif` bit(1) NOT NULL,
                           `nom` varchar(255) DEFAULT NULL,
                           `prenom` varchar(255) DEFAULT NULL,
                           `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `entreprise`
--

CREATE TABLE `entreprise` (
                              `id` int NOT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              `raison_social` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mission`
--

CREATE TABLE `mission` (
                           `dtype` varchar(31) NOT NULL,
                           `id` int NOT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `email_facturation` varchar(255) DEFAULT NULL,
                           `taux_journalier_moyen` float DEFAULT NULL,
                           `departement_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `timesheet`
--

CREATE TABLE `timesheet` (
                             `date_debut` date NOT NULL,
                             `date_fin` date NOT NULL,
                             `id_employe` int NOT NULL,
                             `id_mission` int NOT NULL,
                             `is_valide` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contrat`
--
ALTER TABLE `contrat`
    ADD PRIMARY KEY (`reference`),
    ADD KEY `FKidi9k1fvw6mma24yqoe2kmtju` (`employe_id`);

--
-- Indexes for table `departement`
--
ALTER TABLE `departement`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKkg0jmw8ih55tnlfet3ucbkfsy` (`entreprise_id`);

--
-- Indexes for table `departement_employes`
--
ALTER TABLE `departement_employes`
    ADD KEY `FKp688tcln21xhsg34l5ltk164s` (`employes_id`),
    ADD KEY `FK1lnr2unyxtqxd0olwlepjt0gp` (`departements_id`);

--
-- Indexes for table `employe`
--
ALTER TABLE `employe`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `entreprise`
--
ALTER TABLE `entreprise`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mission`
--
ALTER TABLE `mission`
    ADD PRIMARY KEY (`id`),
    ADD KEY `FKso6moxdlog3powkcqulmtfg1s` (`departement_id`);

--
-- Indexes for table `timesheet`
--
ALTER TABLE `timesheet`
    ADD PRIMARY KEY (`date_debut`,`date_fin`,`id_employe`,`id_mission`),
    ADD KEY `FK2skc3sqdd7v35jgqrdfimuqxr` (`id_employe`),
    ADD KEY `FKbauiu1nsna8neie2d892t99vq` (`id_mission`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contrat`
--
ALTER TABLE `contrat`
    MODIFY `reference` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `departement`
--
ALTER TABLE `departement`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employe`
--
ALTER TABLE `employe`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `entreprise`
--
ALTER TABLE `entreprise`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mission`
--
ALTER TABLE `mission`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contrat`
--
ALTER TABLE `contrat`
    ADD CONSTRAINT `FKidi9k1fvw6mma24yqoe2kmtju` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`id`);

--
-- Constraints for table `departement`
--
ALTER TABLE `departement`
    ADD CONSTRAINT `FKkg0jmw8ih55tnlfet3ucbkfsy` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`);

--
-- Constraints for table `departement_employes`
--
ALTER TABLE `departement_employes`
    ADD CONSTRAINT `FK1lnr2unyxtqxd0olwlepjt0gp` FOREIGN KEY (`departements_id`) REFERENCES `departement` (`id`),
    ADD CONSTRAINT `FKp688tcln21xhsg34l5ltk164s` FOREIGN KEY (`employes_id`) REFERENCES `employe` (`id`);

--
-- Constraints for table `mission`
--
ALTER TABLE `mission`
    ADD CONSTRAINT `FKso6moxdlog3powkcqulmtfg1s` FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`);

--
-- Constraints for table `timesheet`
--
ALTER TABLE `timesheet`
    ADD CONSTRAINT `FK2skc3sqdd7v35jgqrdfimuqxr` FOREIGN KEY (`id_employe`) REFERENCES `employe` (`id`),
    ADD CONSTRAINT `FKbauiu1nsna8neie2d892t99vq` FOREIGN KEY (`id_mission`) REFERENCES `mission` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


INSERT INTO `entreprise` (`id`, `name`, `raison_social`) VALUES (NULL, 'Test entreprise', 'Test social');
INSERT INTO `departement` (`id`, `name`, `entreprise_id`) VALUES (NULL, 'test dep', '1');