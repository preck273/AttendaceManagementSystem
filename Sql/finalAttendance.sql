-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2022 at 05:11 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendancelog`
--

CREATE TABLE `attendancelog` (
  `log_id` int(11) NOT NULL,
  `present_no` int(11) DEFAULT NULL,
  `absent_no` int(11) DEFAULT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `course_start_date` date DEFAULT NULL,
  `course_end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_name`, `course_start_date`, `course_end_date`) VALUES
(1, 'PHP', '2022-04-04', '2022-06-15'),
(2, 'JAVA', '2022-05-01', '2022-06-15'),
(3, 'PHP', '2022-05-05', '2022-06-03'),
(4, 'JavaScript', '2022-05-01', '2022-05-01'),
(5, 'Java', '2022-05-01', '2022-05-01'),
(6, 'Database', '2022-05-01', '2022-05-01');

-- --------------------------------------------------------

--
-- Table structure for table `enroll`
--

CREATE TABLE `enroll` (
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enroll`
--

INSERT INTO `enroll` (`course_id`, `student_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `presentlog`
--

CREATE TABLE `presentlog` (
  `presentlog_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `presentlog`
--

INSERT INTO `presentlog` (`presentlog_id`, `student_id`, `course_id`) VALUES
(1, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `schedule_id` int(11) NOT NULL,
  `course_name` varchar(30) NOT NULL,
  `room_number` int(11) DEFAULT NULL,
  `course_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schedule_id`, `course_name`, `room_number`, `course_date`, `start_time`, `end_time`) VALUES
(1, 'PHP', 123, '2022-06-06', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `student_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `first_name`, `last_name`, `email_address`, `student_password`) VALUES
(1, 'precious', 'Oziwo', 'preck273@gmil.com', '$2y$10$qzVgd3BHFLjjOMkxbFm0SuLRtXrFPY4JVsxiFE5gmmOvgQ1MzuRBC'),
(2, 'john', 'doe', 'jhondoe@gmail.com', '$2y$10$6xhRoTXiQoQYejAWAktkuuOxWNv81W4GwS2VJk1LK6HKJ2mu17eL.'),
(3, 'sam', 'fre', 'samfre@gmail.com', '$2y$10$we/LPpiE/MrOwFLOj4HqTuGXSi3aU2YtbgS4nLjhODhoq2oU04/mW'),
(4, 's', 'f', 'sf@gmail.com', '$2y$10$d0Mj3wpBnqmLOzYvcdENGeOQhT4/XV2YEv6tlhdUOaZKom.4gTAG.'),
(5, 'a', 'b', 'ab@gmail.om', '$2y$10$Y5XuMJH6kHj8Bmk3bM27Ae1U9I6S2/lmPR58.JyVWZsuxuaRPI.Oi');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `teacher_password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `first_name`, `last_name`, `email_address`, `teacher_password`) VALUES
(1, 'Rob', 'Smith', 'Robsmith@gmail.com', '$2y$10$BvmCrAeZH42u8mQUBI4kyOGT2e1oSy6BpSBfhXj8az9x6jMvtwfXa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendancelog`
--
ALTER TABLE `attendancelog`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `attendanceLog_student_fk` (`student_id`),
  ADD KEY `attendanceLog_course_fk` (`course_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `enroll`
--
ALTER TABLE `enroll`
  ADD KEY `enroll_course_fk` (`course_id`),
  ADD KEY `enroll_student_fk` (`student_id`);

--
-- Indexes for table `presentlog`
--
ALTER TABLE `presentlog`
  ADD PRIMARY KEY (`presentlog_id`),
  ADD KEY `student_ID_FK` (`student_id`),
  ADD KEY `course_ID_FK` (`course_id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`schedule_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendancelog`
--
ALTER TABLE `attendancelog`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `presentlog`
--
ALTER TABLE `presentlog`
  MODIFY `presentlog_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `teacher_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendancelog`
--
ALTER TABLE `attendancelog`
  ADD CONSTRAINT `attendanceLog_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `attendanceLog_student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `enroll`
--
ALTER TABLE `enroll`
  ADD CONSTRAINT `enroll_course_fk` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `enroll_student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `presentlog`
--
ALTER TABLE `presentlog`
  ADD CONSTRAINT `course_ID_FK` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ID_FK` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
