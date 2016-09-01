-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 01, 2016 at 07:39 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jira`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `name`, `password`, `status`, `role`) VALUES
(1, 'Oana', 'Oana', 'REJECTED', 'ADMIN'),
(2, 'Oana1', 'Oana1', 'APPROVED', 'PJM'),
(3, 'Oana2', 'Oana2', 'REJECTED', 'PJM');

-- --------------------------------------------------------

--
-- Table structure for table `accountprojects`
--

CREATE TABLE `accountprojects` (
  `id` bigint(20) NOT NULL,
  `accountid` bigint(20) NOT NULL,
  `accountproject` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountprojects`
--

INSERT INTO `accountprojects` (`id`, `accountid`, `accountproject`) VALUES
(2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `databasechangelog`
--

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `databasechangelog`
--

INSERT INTO `databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`) VALUES
('00000000000001', 'jhipster', 'classpath:config/liquibase/changelog/00000000000000_initial_schema.xml', '2016-07-30 23:18:51', 1, 'EXECUTED', '7:088448cd8ce4f9b9b9211b5e698272c3', 'createTable, createIndex (x2), createTable (x2), addPrimaryKey, createTable, addForeignKeyConstraint (x3), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint', '', NULL, '3.4.2', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `databasechangeloglock`
--

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `databasechangeloglock`
--

INSERT INTO `databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
(1, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `efforttype`
--

CREATE TABLE `efforttype` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `efforttype`
--

INSERT INTO `efforttype` (`id`, `name`) VALUES
(1, 'Strayegy'),
(2, 'Technical Requirement'),
(3, 'Maintenance'),
(4, 'Other');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `name`) VALUES
(1, 'Spike'),
(2, 'User Story'),
(3, 'Service Requests'),
(4, 'Bugs'),
(5, 'Problems'),
(6, 'Support - Internal');

-- --------------------------------------------------------

--
-- Table structure for table `jhi_authority`
--

CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jhi_authority`
--

INSERT INTO `jhi_authority` (`name`) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `jhi_persistent_audit_event`
--

CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL,
  `principal` varchar(255) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

INSERT INTO `jhi_persistent_audit_event` (`event_id`, `principal`, `event_date`, `event_type`) VALUES
(1, 'admin', '2016-07-30 20:19:08', 'AUTHENTICATION_SUCCESS'),
(2, 'admin', '2016-07-30 20:19:09', 'AUTHENTICATION_SUCCESS'),
(3, 'admin', '2016-08-01 11:14:13', 'AUTHENTICATION_SUCCESS');

-- --------------------------------------------------------

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`, `name`, `value`) VALUES
(1, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(1, 'sessionId', '7717D29FB19457C69C7E194B7EDEC4B1'),
(2, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(2, 'sessionId', '7717D29FB19457C69C7E194B7EDEC4B1'),
(3, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(3, 'sessionId', 'CF3A99543C6AF099C77BBAE5E60C5A9D');

-- --------------------------------------------------------

--
-- Table structure for table `jhi_persistent_token`
--

CREATE TABLE `jhi_persistent_token` (
  `series` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(255) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jhi_persistent_token`
--

INSERT INTO `jhi_persistent_token` (`series`, `user_id`, `token_value`, `token_date`, `ip_address`, `user_agent`) VALUES
('8TxbOwz3rAEjZh2Hg6FuYg==', 3, 'S9H5InVATA7iV00HzwMtmw==', '2016-08-01', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'),
('cpyuP8/mp0Y2uiGc/K9T+Q==', 3, 'q8QPWSXGtvqrkA93/6nGcg==', '2016-07-30', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36'),
('LPXKrxsaJEgfPV0Jakh6nA==', 3, 'Bp4uqpkQJckl8MI4o4cHbA==', '2016-07-30', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36');

-- --------------------------------------------------------

--
-- Table structure for table `jhi_user`
--

CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jhi_user`
--

INSERT INTO `jhi_user` (`id`, `login`, `password_hash`, `first_name`, `last_name`, `email`, `activated`, `lang_key`, `activation_key`, `reset_key`, `created_by`, `created_date`, `reset_date`, `last_modified_by`, `last_modified_date`) VALUES
(1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', b'1', 'en', NULL, NULL, 'system', '2016-07-30 20:18:41', NULL, 'system', '2016-07-30 20:18:41'),
(2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', b'1', 'en', NULL, NULL, 'system', '2016-07-30 20:18:41', NULL, 'system', '2016-07-30 20:18:41'),
(3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', b'1', 'en', NULL, NULL, 'system', '2016-07-30 20:18:41', NULL, 'system', '2016-07-30 20:18:41'),
(4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', b'1', 'en', NULL, NULL, 'system', '2016-07-30 20:18:41', NULL, 'system', '2016-07-30 20:18:41');

-- --------------------------------------------------------

--
-- Table structure for table `jhi_user_authority`
--

CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jhi_user_authority`
--

INSERT INTO `jhi_user_authority` (`user_id`, `authority_name`) VALUES
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(3, 'ROLE_ADMIN'),
(3, 'ROLE_USER'),
(4, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `jiraproject`
--

CREATE TABLE `jiraproject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jiraproject`
--

INSERT INTO `jiraproject` (`id`, `name`) VALUES
(1, 'project1'),
(2, 'Search Engine'),
(3, 'SOM'),
(4, 'Connect');

-- --------------------------------------------------------

--
-- Table structure for table `jirarelease`
--

CREATE TABLE `jirarelease` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jirarelease`
--

INSERT INTO `jirarelease` (`id`, `name`) VALUES
(1, 'R1'),
(2, 'R2');

-- --------------------------------------------------------

--
-- Table structure for table `jirasprint`
--

CREATE TABLE `jirasprint` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jirasprint`
--

INSERT INTO `jirasprint` (`id`, `name`) VALUES
(1, 'Sprint 1'),
(2, 'Sprint 2'),
(3, 'Sprint 3'),
(4, 'Sprint 4');

-- --------------------------------------------------------

--
-- Table structure for table `projectrelease`
--

CREATE TABLE `projectrelease` (
  `id` bigint(20) NOT NULL,
  `idproject` bigint(20) NOT NULL,
  `idrelease` bigint(20) NOT NULL,
  `an` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projectrelease`
--

INSERT INTO `projectrelease` (`id`, `idproject`, `idrelease`, `an`) VALUES
(1, 1, 1, '2016'),
(2, 1, 2, '2016'),
(3, 2, 1, '2016'),
(4, 2, 2, '2016'),
(5, 3, 1, '2016'),
(6, 3, 2, '2016'),
(7, 4, 1, '2016'),
(8, 4, 2, '2016');

-- --------------------------------------------------------

--
-- Table structure for table `projectreleasesprint`
--

CREATE TABLE `projectreleasesprint` (
  `id` bigint(20) NOT NULL,
  `idprojectrelease` bigint(20) NOT NULL,
  `idsprint` bigint(20) NOT NULL,
  `capacity` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projectreleasesprint`
--

INSERT INTO `projectreleasesprint` (`id`, `idprojectrelease`, `idsprint`, `capacity`) VALUES
(1, 1, 1, '15'),
(2, 1, 2, ''),
(3, 1, 3, ''),
(4, 1, 4, ''),
(5, 2, 1, ''),
(6, 2, 2, ''),
(7, 2, 3, ''),
(8, 2, 4, ''),
(9, 3, 1, '14'),
(10, 3, 2, ''),
(11, 3, 3, ''),
(12, 3, 4, ''),
(13, 4, 1, ''),
(14, 4, 2, ''),
(15, 4, 3, ''),
(16, 4, 4, ''),
(17, 5, 1, ''),
(18, 5, 2, ''),
(19, 5, 3, ''),
(20, 5, 4, ''),
(21, 6, 1, ''),
(22, 6, 2, ''),
(23, 6, 3, ''),
(24, 6, 4, ''),
(25, 7, 1, ''),
(26, 7, 2, ''),
(27, 7, 3, ''),
(28, 7, 4, ''),
(29, 8, 1, ''),
(30, 8, 2, ''),
(31, 8, 3, ''),
(32, 8, 4, '');

-- --------------------------------------------------------

--
-- Table structure for table `storypoint`
--

CREATE TABLE `storypoint` (
  `id` bigint(20) NOT NULL,
  `idprojectreleasesprint` bigint(20) NOT NULL,
  `idefforttype` bigint(20) NOT NULL,
  `iditem` bigint(20) NOT NULL,
  `storypoint` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `storypoint`
--

INSERT INTO `storypoint` (`id`, `idprojectreleasesprint`, `idefforttype`, `iditem`, `storypoint`) VALUES
(1, 1, 2, 2, '2'),
(2, 2, 2, 2, '2'),
(3, 3, 3, 3, '3'),
(4, 4, 3, 3, '3'),
(5, 5, 2, 2, '2'),
(6, 6, 2, 2, '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `accountprojects`
--
ALTER TABLE `accountprojects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `databasechangeloglock`
--
ALTER TABLE `databasechangeloglock`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `efforttype`
--
ALTER TABLE `efforttype`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jhi_authority`
--
ALTER TABLE `jhi_authority`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `idx_persistent_audit_event` (`principal`,`event_date`);

--
-- Indexes for table `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD PRIMARY KEY (`event_id`,`name`),
  ADD KEY `idx_persistent_audit_evt_data` (`event_id`);

--
-- Indexes for table `jhi_persistent_token`
--
ALTER TABLE `jhi_persistent_token`
  ADD PRIMARY KEY (`series`),
  ADD KEY `fk_user_persistent_token` (`user_id`);

--
-- Indexes for table `jhi_user`
--
ALTER TABLE `jhi_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `idx_user_login` (`login`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `idx_user_email` (`email`);

--
-- Indexes for table `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD PRIMARY KEY (`user_id`,`authority_name`),
  ADD KEY `fk_authority_name` (`authority_name`);

--
-- Indexes for table `jiraproject`
--
ALTER TABLE `jiraproject`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jirarelease`
--
ALTER TABLE `jirarelease`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jirasprint`
--
ALTER TABLE `jirasprint`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projectrelease`
--
ALTER TABLE `projectrelease`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projectreleasesprint`
--
ALTER TABLE `projectreleasesprint`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `storypoint`
--
ALTER TABLE `storypoint`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `accountprojects`
--
ALTER TABLE `accountprojects`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `efforttype`
--
ALTER TABLE `efforttype`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `jhi_user`
--
ALTER TABLE `jhi_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `jiraproject`
--
ALTER TABLE `jiraproject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `jirarelease`
--
ALTER TABLE `jirarelease`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `jirasprint`
--
ALTER TABLE `jirasprint`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `projectrelease`
--
ALTER TABLE `projectrelease`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `projectreleasesprint`
--
ALTER TABLE `projectreleasesprint`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `storypoint`
--
ALTER TABLE `storypoint`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`);

--
-- Constraints for table `jhi_persistent_token`
--
ALTER TABLE `jhi_persistent_token`
  ADD CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

--
-- Constraints for table `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
