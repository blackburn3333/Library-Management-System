-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2017 at 10:02 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `readwithus`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `LMS_bookName` varchar(100) NOT NULL,
  `LMS_bookAuthor` varchar(100) NOT NULL,
  `LMS_bookIsbn` varchar(50) NOT NULL,
  `LMS_booklId` varchar(50) NOT NULL,
  `LMS_bookCount` int(10) NOT NULL,
  `LMS_bookCategory` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`LMS_bookName`, `LMS_bookAuthor`, `LMS_bookIsbn`, `LMS_booklId`, `LMS_bookCount`, `LMS_bookCategory`) VALUES
('Harry Potter and the Philosopher-s Stone', 'J. K. Rowling', 'ISBN9788700631625', 'RWU1520', 22, 'Fantasy'),
('Harry Potter and the Half-Blood Prince', 'J. K. Rowling', 'ISBN0747581088', 'RWU5264', 7, 'Fantasy');

-- --------------------------------------------------------

--
-- Table structure for table `lms_bookbarrow`
--

CREATE TABLE `lms_bookbarrow` (
  `book_issuID` int(11) NOT NULL,
  `lma_memberID` varchar(20) NOT NULL,
  `bookID` varchar(30) NOT NULL,
  `bookBarrowDate` varchar(15) NOT NULL,
  `bookBarrowEndDate` varchar(15) NOT NULL,
  `bookBarrow_AuID` varchar(20) NOT NULL,
  `bookBarrow_Fine` int(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lms_bookreseve`
--

CREATE TABLE `lms_bookreseve` (
  `lms_bookReserveID` int(11) NOT NULL,
  `lms_bookMemberID` varchar(20) NOT NULL,
  `lms_bookResBookID` varchar(10) NOT NULL,
  `lms_bookReserveDate` varchar(15) NOT NULL,
  `lms_bookReserveEndDate` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lms_users`
--

CREATE TABLE `lms_users` (
  `LMS_UserFname` varchar(50) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_contact` varchar(15) NOT NULL,
  `user_Address` varchar(500) NOT NULL,
  `user_gender` varchar(10) DEFAULT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`LMS_booklId`);

--
-- Indexes for table `lms_bookbarrow`
--
ALTER TABLE `lms_bookbarrow`
  ADD PRIMARY KEY (`book_issuID`);

--
-- Indexes for table `lms_bookreseve`
--
ALTER TABLE `lms_bookreseve`
  ADD PRIMARY KEY (`lms_bookReserveID`);

--
-- Indexes for table `lms_users`
--
ALTER TABLE `lms_users`
  ADD PRIMARY KEY (`user_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lms_bookbarrow`
--
ALTER TABLE `lms_bookbarrow`
  MODIFY `book_issuID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `lms_bookreseve`
--
ALTER TABLE `lms_bookreseve`
  MODIFY `lms_bookReserveID` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
