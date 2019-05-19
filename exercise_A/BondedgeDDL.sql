CREATE DATABASE  IF NOT EXISTS `bondedge`;
USE `bondedge`;

SET NAMES utf8 ;

--
-- Table structure for table `Citizen`
--

DROP TABLE IF EXISTS `Citizen`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `Citizen` (
  `CitizenId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(256) DEFAULT NULL,
  `LastName` varchar(256) DEFAULT NULL,
  `Street` varchar(256) DEFAULT NULL COMMENT 'Example: 1221 W Adams St.',
  `City` varchar(256) DEFAULT NULL,
  `State` varchar(2) DEFAULT NULL,
  `Zipcode` varchar(10) DEFAULT NULL COMMENT 'We are supporting the following formats: 12345 and 12345-6789',
  PRIMARY KEY (`CitizenId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;


--
-- Table structure for table `PresidentialCandidate`
--

DROP TABLE IF EXISTS `PresidentialCandidate`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `PresidentialCandidate` (
  `PresidentialCandidateId` smallint(5) unsigned NOT NULL,
  `CitizenId` int(11) unsigned NOT NULL,
  `NumberOfVotes` int(11) NOT NULL DEFAULT '0' COMMENT 'The application layer should update this column in order to enamble quick report of results.',
  PRIMARY KEY (`PresidentialCandidateId`),
  UNIQUE KEY `idx_CitizenId_UNIQUE` (`CitizenId`),
  CONSTRAINT `fk_PresidentialCandidate_CitizenId` FOREIGN KEY (`CitizenId`) REFERENCES `Citizen` (`CitizenId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`masteruser`@`%`*/ /*!50003 TRIGGER `PresidentialCandidate_before_insert` BEFORE INSERT ON `PresidentialCandidate`
FOR EACH ROW
BEGIN
    CALL check_PresidentialCandidateMax(new.PresidentialCandidateId);
END */;;
DELIMITER ;

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`masteruser`@`%`*/ /*!50003 TRIGGER `PresidentialCandidate_before_update` BEFORE UPDATE ON `PresidentialCandidate`
FOR EACH ROW
BEGIN
    CALL check_PresidentialCandidateMax(new.PresidentialCandidateId);
END */;;
DELIMITER ;


--
-- Table structure for table `Vote`
--

DROP TABLE IF EXISTS `Vote`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `Vote` (
  `CitizenId` int(10) unsigned NOT NULL,
  `PresidentialCandidateId` smallint(5) unsigned NOT NULL,
  `DateTimeOfVoteUTC` datetime NOT NULL COMMENT 'This field will be set by the database.',
  `LocalTimeZone` varchar(40) NOT NULL COMMENT 'Sample format: GMT, PST',
  PRIMARY KEY (`CitizenId`),
  KEY `idx_PresidentialCandidate` (`PresidentialCandidateId`),
  CONSTRAINT `fk_Vote_PresidentialCandidateId` FOREIGN KEY (`PresidentialCandidateId`) REFERENCES `PresidentialCandidate` (`PresidentialCandidateId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Vote_Citizen` FOREIGN KEY (`CitizenId`) REFERENCES `Citizen` (`CitizenId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`masteruser`@`%`*/ /*!50003 TRIGGER `Vote_before_insert` BEFORE INSERT ON `Vote`
FOR EACH ROW
BEGIN
    CALL check_VoteTime(new.DateTimeOfVoteUTC, new.LocalTimeZone);
END */;;
DELIMITER ;

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`masteruser`@`%`*/ /*!50003 TRIGGER `Vote_before_update` BEFORE UPDATE ON `Vote`
FOR EACH ROW
BEGIN
    CALL check_VoteTime(new.DateTimeOfVoteUTC, new.LocalTimeZone);
END */;;
DELIMITER ;


--
-- Routines 
--

DELIMITER ;;
CREATE DEFINER=`masteruser`@`%` PROCEDURE `check_PresidentialCandidateMax`(IN candidateId int(11))
BEGIN
    IF candidateId < 0 or 9 < candidateId THEN
        SIGNAL SQLSTATE '45000'
           SET MESSAGE_TEXT = 'check constraint on PresidentialCandidate.PresidentialCandidateId failed';
    END IF;
END ;;
DELIMITER ;

DELIMITER ;;
CREATE DEFINER=`masteruser`@`%` PROCEDURE `check_VoteTime`( in utcDateTime datetime, in timezone varchar(40) )
BEGIN
	DECLARE theHour INT;
    SET theHour = hour( CONVERT_TZ ( utcDateTime, 'UTC', timezone ) );
    IF theHour < 6 or 19 < theHour or theHour is null THEN
        SIGNAL SQLSTATE '45000'
           SET MESSAGE_TEXT = 'check constraint on Vote.LocalTimeOfVote failed';
    END IF;
END ;;
DELIMITER ;

