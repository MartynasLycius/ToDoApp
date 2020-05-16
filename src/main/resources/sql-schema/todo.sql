-- phpMyAdmin SQL Dump
-- version 4.0.10deb1ubuntu0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 16, 2020 at 07:18 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `todo`
--

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `state` int(1) unsigned NOT NULL COMMENT 'NEW = 0 | DONE = 1',
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `name`, `description`, `state`, `created_date`) VALUES
(1, 'Wash Bicycle ', 'Must clean chain with karosin 1', 1, '2020-05-15'),
(3, 'Buy bread', 'Brown bread i guess??', 1, '2020-05-15'),
(4, 'Read a book', 'ANY BOOK', 0, '2020-05-15'),
(8, 'Wash sneaker  ', 'Must be clean ', 0, '2020-05-15');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
