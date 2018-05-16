-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2018 at 01:36 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbkusuma`
--
CREATE DATABASE IF NOT EXISTS `dbkusuma` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dbkusuma`;

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `SPLIT_STRING` (`str` VARCHAR(255), `delim` VARCHAR(12), `pos` INT) RETURNS VARCHAR(255) CHARSET latin1 RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(str, delim, pos),
       LENGTH(SUBSTRING_INDEX(str, delim, pos-1)) + 1),
       delim, '')$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tbantrian`
--

CREATE TABLE `tbantrian` (
  `IdAntrian` int(11) NOT NULL,
  `NoAntrian` int(11) NOT NULL,
  `Tanggal` datetime NOT NULL,
  `IdPasien` int(11) NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbantrian`
--

INSERT INTO `tbantrian` (`IdAntrian`, `NoAntrian`, `Tanggal`, `IdPasien`, `Status`) VALUES
(1, 1, '2018-04-27 00:00:00', 217, 1),
(2, 2, '2018-04-27 00:00:00', 217, 0),
(3, 1, '2018-04-28 00:00:00', 508, 1),
(4, 2, '2018-04-28 00:00:00', 241, 1),
(5, 3, '2018-04-28 00:00:00', 241, 0),
(6, 1, '2018-04-29 00:00:00', 259, 1),
(7, 2, '2018-04-29 00:00:00', 592, 0),
(8, 1, '2018-04-30 00:00:00', 1266, 1),
(9, 2, '2018-04-30 00:00:00', 1266, 1),
(10, 1, '2018-05-01 00:00:00', 1000, 1),
(11, 2, '2018-05-01 00:00:00', 259, 1),
(12, 3, '2018-05-01 00:00:00', 508, 1),
(13, 4, '2018-05-01 00:00:00', 1025, 0),
(14, 5, '2018-05-01 00:00:00', 396, 1),
(15, 1, '2018-05-02 00:00:00', 662, 1),
(16, 2, '2018-05-02 00:00:00', 508, 1),
(17, 3, '2018-05-02 00:00:00', 592, 0),
(18, 4, '2018-05-02 00:00:00', 843, 0),
(19, 5, '2018-05-02 00:00:00', 870, 0),
(20, 6, '2018-05-02 00:00:00', 1158, 0),
(21, 7, '2018-05-02 00:00:00', 1025, 0),
(22, 1, '2018-05-03 00:00:00', 217, 1),
(23, 2, '2018-05-03 00:00:00', 217, 1),
(24, 3, '2018-05-03 00:00:00', 843, 0),
(25, 1, '2018-05-04 00:00:00', 1025, 0),
(26, 1, '2018-05-05 00:00:00', 843, 1),
(27, 2, '2018-05-05 00:00:00', 843, 1),
(28, 3, '2018-05-05 00:00:00', 1025, 1),
(29, 4, '2018-05-05 00:00:00', 1025, 1),
(33, 1, '2018-05-06 00:00:00', 1304, 1),
(34, 2, '2018-05-06 00:00:00', 1304, 1),
(35, 3, '2018-05-06 00:00:00', 1304, 1),
(36, 4, '2018-05-06 00:00:00', 1304, 1),
(37, 5, '2018-05-06 00:00:00', 1304, 1),
(38, 6, '2018-05-06 00:00:00', 662, 1),
(39, 7, '2018-05-06 00:00:00', 1304, 1),
(40, 8, '2018-05-06 00:00:00', 1304, 1),
(41, 1, '2018-05-07 00:00:00', 1025, 1),
(42, 1, '2018-05-08 00:00:00', 593, 1),
(43, 2, '2018-05-08 00:00:00', 593, 1),
(44, 1, '2018-05-09 00:00:00', 593, 1),
(45, 1, '2018-05-10 00:00:00', 593, 1),
(46, 1, '2018-05-11 00:00:00', 593, 1),
(47, 2, '2018-05-11 00:00:00', 593, 1),
(48, 1, '2018-05-14 00:00:00', 593, 0),
(49, 1, '2018-05-15 00:00:00', 662, 0),
(50, 1, '2018-05-16 00:00:00', 744, 1),
(51, 2, '2018-05-16 00:00:00', 593, 1),
(54, 3, '2018-05-16 00:00:00', 593, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbbarangmasuk`
--

CREATE TABLE `tbbarangmasuk` (
  `IdBarangMasuk` int(11) NOT NULL,
  `NoTransaksi` varchar(12) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdPemasok` int(11) NOT NULL,
  `Keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbbarangmasuk`
--

INSERT INTO `tbbarangmasuk` (`IdBarangMasuk`, `NoTransaksi`, `Tanggal`, `IdPemasok`, `Keterangan`) VALUES
(1, 'KB-000001-BM', '2018-05-04', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbbarangmasukdetail`
--

CREATE TABLE `tbbarangmasukdetail` (
  `IdBarangMasukDetail` int(11) NOT NULL,
  `NoTransaksi` varchar(12) NOT NULL,
  `NoKolom` int(11) NOT NULL,
  `IdBarang` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbbarangmasukdetail`
--

INSERT INTO `tbbarangmasukdetail` (`IdBarangMasukDetail`, `NoTransaksi`, `NoKolom`, `IdBarang`, `Jumlah`, `Harga`) VALUES
(1, 'KB-000001-BM', 1, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbbilling`
--

CREATE TABLE `tbbilling` (
  `IdBilling` int(11) NOT NULL,
  `NoBilling` varchar(15) NOT NULL,
  `Tanggal` date NOT NULL,
  `NoInvoice` varchar(15) NOT NULL,
  `Bayar` int(11) NOT NULL,
  `StatusPoin` tinyint(4) NOT NULL DEFAULT '0',
  `Poin` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbbilling`
--

INSERT INTO `tbbilling` (`IdBilling`, `NoBilling`, `Tanggal`, `NoInvoice`, `Bayar`, `StatusPoin`, `Poin`) VALUES
(1, 'KB-000001-BIL', '2018-04-28', 'KB-000002-INV', 200000, 0, 0),
(2, 'KB-000002-BIL', '2018-04-28', 'KB-000003-INV', 200000, 0, 0),
(3, 'KB-000003-BIL', '2018-04-29', 'KB-000004-INV', 250000, 1, 2),
(4, 'KB-000004-BIL', '2018-04-30', 'KB-000005-INV', 200000, 0, 0),
(5, 'KB-000005-BIL', '2018-05-01', 'KB-000007-INV', 200000, 0, 0),
(6, 'KB-000006-BIL', '2018-05-01', 'KB-000008-INV', 200000, 0, 0),
(7, 'KB-000007-BIL', '2018-05-01', 'KB-000009-INV', 400000, 1, 5),
(8, 'KB-000008-BIL', '2018-05-01', 'KB-000010-INV', 200000, 0, 0),
(9, 'KB-000009-BIL', '2018-05-02', 'KB-000012-INV', 200000, 1, 5),
(10, 'KB-000010-BIL', '2018-05-03', 'KB-000013-INV', 200000, 0, 0),
(11, 'KB-000011-BIL', '2018-05-05', 'KB-000015-INV', 300000, 0, 0),
(12, 'KB-000012-BIL', '2018-05-05', 'KB-000016-INV', 200000, 1, 6),
(13, 'KB-000013-BIL', '2018-05-05', 'KB-000017-INV', 200000, 0, 0),
(14, 'KB-000014-BIL', '2018-05-06', 'KB-000019-INV', 100000, 0, 0),
(15, 'KB-000015-BIL', '2018-05-06', 'KB-000020-INV', 250000, 1, 2),
(16, 'KB-000016-BIL', '2018-05-06', 'KB-000021-INV', 200000, 1, 4),
(17, 'KB-000017-BIL', '2018-05-06', 'KB-000022-INV', 200000, 1, 3),
(18, 'KB-000018-BIL', '2018-05-06', 'KB-000025-INV', 650000, 1, 3),
(19, 'KB-000019-BIL', '2018-05-08', 'KB-000028-INV', 500000, 0, 0),
(20, 'KB-000020-BIL', '2018-05-16', 'KB-000035-INV', 60000, 1, 19),
(21, 'KB-000021-BIL', '2018-05-16', 'KB-000036-INV', 0, 1, 46),
(22, 'KB-000022-BIL', '2018-05-16', 'KB-000034-INV', 190000, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbbillingobat`
--

CREATE TABLE `tbbillingobat` (
  `IdBillingObat` int(11) NOT NULL,
  `NoBilling` varchar(15) NOT NULL,
  `IdObat` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbbillingobat`
--

INSERT INTO `tbbillingobat` (`IdBillingObat`, `NoBilling`, `IdObat`, `Jumlah`, `Harga`) VALUES
(1, 'KB-000001-BIL', 1, 1, 110000),
(2, 'KB-000002-BIL', 5, 1, 75000),
(3, 'KB-000003-BIL', 18, 1, 150000),
(4, 'KB-000004-BIL', 2, 1, 110000),
(5, 'KB-000005-BIL', 2, 1, 110000),
(6, 'KB-000006-BIL', 1, 1, 110000),
(7, 'KB-000007-BIL', 2, 1, 110000),
(8, 'KB-000008-BIL', 4, 2, 20000),
(9, 'KB-000009-BIL', 2, 1, 110000),
(10, 'KB-000010-BIL', 1, 1, 110000),
(11, 'KB-000011-BIL', 2, 2, 110000),
(12, 'KB-000012-BIL', 1, 1, 110000),
(13, 'KB-000013-BIL', 1, 1, 110000),
(14, 'KB-000014-BIL', 4, 1, 20000),
(15, 'KB-000015-BIL', 1, 1, 110000),
(16, 'KB-000016-BIL', 1, 1, 110000),
(17, 'KB-000017-BIL', 3, 1, 110000),
(18, 'KB-000018-BIL', 1, 1, 110000),
(19, 'KB-000018-BIL', 2, 1, 110000),
(20, 'KB-000018-BIL', 1, 1, 110000),
(21, 'KB-000018-BIL', 2, 1, 110000),
(22, 'KB-000018-BIL', 1, 1, 110000),
(23, 'KB-000018-BIL', 2, 1, 110000),
(24, 'KB-000018-BIL', 1, 1, 110000),
(25, 'KB-000018-BIL', 2, 1, 110000),
(26, 'KB-000019-BIL', 1, 1, 110000),
(27, 'KB-000019-BIL', 2, 1, 110000),
(28, 'KB-000020-BIL', 2, 1, 110000),
(29, 'KB-000020-BIL', 22, 1, 135000),
(30, 'KB-000020-BIL', 16, 2, 35000),
(31, 'KB-000020-BIL', 18, 4, 150000),
(32, 'KB-000020-BIL', 14, 5, 100000),
(33, 'KB-000020-BIL', 45, 6, 50000),
(34, 'KB-000020-BIL', 2, 1, 110000),
(35, 'KB-000020-BIL', 22, 1, 135000),
(36, 'KB-000020-BIL', 16, 2, 35000),
(37, 'KB-000020-BIL', 18, 4, 150000),
(38, 'KB-000020-BIL', 14, 5, 100000),
(39, 'KB-000020-BIL', 45, 6, 50000),
(40, 'KB-000020-BIL', 5, 1, 75000),
(41, 'KB-000021-BIL', 3, 1, 110000),
(42, 'KB-000022-BIL', 3, 1, 110000);

-- --------------------------------------------------------

--
-- Table structure for table `tbbillingtindakan`
--

CREATE TABLE `tbbillingtindakan` (
  `IdBillingTindakan` int(11) NOT NULL,
  `NoBilling` varchar(15) NOT NULL,
  `IdTindakan` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbbillingtindakan`
--

INSERT INTO `tbbillingtindakan` (`IdBillingTindakan`, `NoBilling`, `IdTindakan`, `Jumlah`, `Harga`) VALUES
(1, 'KB-000001-BIL', 4, 1, 80000),
(2, 'KB-000002-BIL', 5, 1, 120000),
(3, 'KB-000003-BIL', 4, 1, 80000),
(4, 'KB-000004-BIL', 4, 1, 80000),
(5, 'KB-000005-BIL', 4, 1, 80000),
(6, 'KB-000006-BIL', 4, 1, 80000),
(7, 'KB-000007-BIL', 5, 2, 120000),
(8, 'KB-000008-BIL', 4, 1, 80000),
(9, 'KB-000009-BIL', 4, 1, 80000),
(10, 'KB-000010-BIL', 4, 1, 80000),
(11, 'KB-000011-BIL', 4, 1, 80000),
(12, 'KB-000012-BIL', 4, 1, 80000),
(13, 'KB-000013-BIL', 4, 1, 80000),
(14, 'KB-000014-BIL', 4, 1, 80000),
(15, 'KB-000015-BIL', 5, 1, 120000),
(16, 'KB-000016-BIL', 4, 1, 80000),
(17, 'KB-000017-BIL', 4, 1, 80000),
(18, 'KB-000018-BIL', 4, 1, 80000),
(19, 'KB-000018-BIL', 5, 1, 120000),
(20, 'KB-000019-BIL', 4, 1, 80000),
(21, 'KB-000019-BIL', 6, 1, 185000),
(22, 'KB-000020-BIL', 5, 1, 120000),
(23, 'KB-000020-BIL', 9, 1, 400000),
(24, 'KB-000020-BIL', 11, 1, 600000),
(25, 'KB-000020-BIL', 5, 1, 120000),
(26, 'KB-000020-BIL', 9, 1, 400000),
(27, 'KB-000020-BIL', 11, 1, 600000),
(28, 'KB-000020-BIL', 4, 1, 80000),
(29, 'KB-000021-BIL', 5, 1, 120000),
(30, 'KB-000022-BIL', 4, 1, 80000);

-- --------------------------------------------------------

--
-- Table structure for table `tbhistory`
--

CREATE TABLE `tbhistory` (
  `Username` varchar(50) NOT NULL,
  `Activity` varchar(50) NOT NULL,
  `DateAndTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbhistory`
--

INSERT INTO `tbhistory` (`Username`, `Activity`, `DateAndTime`) VALUES
('', 'Berhasil Tambah Data Antrian', '2018-04-21 11:59:35'),
('', 'Berhasil Tambah Data Antrian', '2018-04-21 11:59:40'),
('', 'Berhasil Tambah Data Antrian', '2018-04-21 12:06:03'),
('', 'Berhasil Tambah Data Antrian', '2018-04-23 18:20:37'),
('', 'Berhasil Menghapus Data Antrian', '2018-04-23 18:34:36'),
('', 'Berhasil Tambah Data Antrian', '2018-04-23 18:34:47'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 19:35:07'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 19:35:10'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 19:35:15'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-23 19:51:08'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 19:51:22'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 19:51:25'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-23 20:24:39'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 20:24:58'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 20:25:06'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 20:25:46'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 20:26:04'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-23 21:07:10'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-23 23:35:18'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-23 23:35:22'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-23 23:35:24'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-23 23:35:27'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-24 10:36:51'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-24 10:37:13'),
('ADMIN', 'Berhasil Tambah Data Pemasok', '2018-04-24 14:01:31'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-24 15:22:06'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-25 09:18:09'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-25 09:18:15'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 00:45:35'),
('ADMIN', ' Multi Insert', '2018-04-26 01:03:34'),
('ADMIN', ' Multi Insert', '2018-04-26 01:03:38'),
('ADMIN', ' Multi Insert', '2018-04-26 01:06:08'),
('ADMIN', ' Multi Insert', '2018-04-26 01:06:08'),
('ADMIN', ' Multi Insert', '2018-04-26 01:07:27'),
('ADMIN', ' Multi Insert', '2018-04-26 01:07:27'),
('ADMIN', ' Multi Insert', '2018-04-26 01:09:48'),
('ADMIN', ' Multi Insert', '2018-04-26 01:09:48'),
('ADMIN', ' Multi Insert', '2018-04-26 01:11:27'),
('ADMIN', ' Multi Insert', '2018-04-26 01:11:27'),
('ADMIN', ' Multi Insert', '2018-04-26 01:12:12'),
('ADMIN', ' Multi Insert', '2018-04-26 01:12:13'),
('ADMIN', ' Multi Insert', '2018-04-26 01:13:59'),
('ADMIN', ' Multi Insert', '2018-04-26 01:14:00'),
('ADMIN', ' Multi Insert', '2018-04-26 01:14:00'),
('ADMIN', ' Multi Insert', '2018-04-26 01:23:18'),
('ADMIN', ' Multi Insert', '2018-04-26 01:23:18'),
('ADMIN', ' Multi Insert', '2018-04-26 01:23:18'),
('ADMIN', ' Multi Insert', '2018-04-26 01:23:18'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 01:31:01'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 01:41:22'),
('ADMIN', ' Multi Insert', '2018-04-26 01:41:38'),
('ADMIN', ' Multi Insert', '2018-04-26 01:41:38'),
('ADMIN', ' Multi Insert', '2018-04-26 01:41:38'),
('ADMIN', ' Multi Insert', '2018-04-26 01:41:38'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 01:42:02'),
('ADMIN', ' Multi Insert', '2018-04-26 01:42:15'),
('ADMIN', ' Multi Insert', '2018-04-26 01:43:55'),
('ADMIN', ' Multi Insert', '2018-04-26 01:43:55'),
('ADMIN', ' Multi Insert', '2018-04-26 01:43:56'),
('ADMIN', ' Multi Insert', '2018-04-26 01:43:56'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 01:56:42'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 01:58:30'),
('ADMIN', ' Multi Insert', '2018-04-26 01:58:46'),
('ADMIN', ' Multi Insert', '2018-04-26 01:58:46'),
('ADMIN', ' Multi Insert', '2018-04-26 01:58:46'),
('ADMIN', ' Multi Insert', '2018-04-26 01:58:46'),
('ADMIN', ' Multi Insert', '2018-04-26 02:00:13'),
('ADMIN', ' Multi Insert', '2018-04-26 02:00:13'),
('ADMIN', ' Multi Insert', '2018-04-26 02:00:14'),
('ADMIN', ' Multi Insert', '2018-04-26 02:00:14'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:21:46'),
('ADMIN', ' Multi Insert', '2018-04-26 09:41:36'),
('ADMIN', ' Multi Insert', '2018-04-26 09:41:36'),
('ADMIN', ' Multi Insert', '2018-04-26 09:41:36'),
('ADMIN', ' Multi Insert', '2018-04-26 09:41:36'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:41:45'),
('ADMIN', ' Multi Insert', '2018-04-26 09:42:02'),
('ADMIN', ' Multi Insert', '2018-04-26 09:42:02'),
('ADMIN', ' Multi Insert', '2018-04-26 09:42:02'),
('ADMIN', ' Multi Insert', '2018-04-26 09:42:02'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:45:15'),
('ADMIN', ' Multi Insert', '2018-04-26 09:45:29'),
('ADMIN', ' Multi Insert', '2018-04-26 09:45:29'),
('ADMIN', ' Multi Insert', '2018-04-26 09:45:29'),
('ADMIN', ' Multi Insert', '2018-04-26 09:45:29'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:46:23'),
('ADMIN', ' Multi Insert', '2018-04-26 09:46:45'),
('ADMIN', ' Multi Insert', '2018-04-26 09:46:45'),
('ADMIN', ' Multi Insert', '2018-04-26 09:46:45'),
('ADMIN', ' Multi Insert', '2018-04-26 09:46:45'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:46:57'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:46:59'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:13'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:13'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:13'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:13'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:25'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:25'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:25'),
('ADMIN', ' Multi Insert', '2018-04-26 09:47:25'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 09:48:14'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:23:59'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-04-26 19:29:17'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:29:22'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:29:29'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:29:40'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:31:44'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:31:53'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:33:55'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-26 19:34:41'),
('ADMIN', ' Multi Insert', '2018-04-26 19:35:08'),
('ADMIN', ' Multi Insert', '2018-04-26 19:35:08'),
('ADMIN', ' Multi Insert', '2018-04-26 19:35:08'),
('ADMIN', ' Multi Insert', '2018-04-26 19:35:08'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 08:54:04'),
('ADMIN', ' Multi Insert', '2018-04-27 08:54:31'),
('ADMIN', ' Multi Insert', '2018-04-27 08:54:31'),
('ADMIN', ' Multi Insert', '2018-04-27 08:54:31'),
('ADMIN', ' Multi Insert', '2018-04-27 08:54:31'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:17:30'),
('ADMIN', ' Multi Insert', '2018-04-27 09:17:55'),
('ADMIN', ' Multi Insert', '2018-04-27 09:17:55'),
('ADMIN', ' Multi Insert', '2018-04-27 09:17:55'),
('ADMIN', ' Multi Insert', '2018-04-27 09:17:55'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:27:54'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:30:14'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:32:25'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:57:06'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:57:53'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 09:58:26'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:13:37'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:31:58'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:32:29'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:35:10'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:37:10'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:37:43'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:38:11'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:38:14'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:38:21'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 10:38:23'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 15:58:00'),
('ADMIN', ' Multi Insert', '2018-04-27 17:14:00'),
('ADMIN', ' Multi Insert', '2018-04-27 17:14:00'),
('ADMIN', ' Multi Insert', '2018-04-27 17:14:00'),
('ADMIN', ' Multi Insert', '2018-04-27 17:14:01'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 17:17:21'),
('ADMIN', ' Multi Insert', '2018-04-27 17:17:35'),
('ADMIN', ' Multi Insert', '2018-04-27 17:17:35'),
('ADMIN', ' Multi Insert', '2018-04-27 17:17:35'),
('ADMIN', ' Multi Insert', '2018-04-27 17:17:35'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 17:19:20'),
('ADMIN', ' Multi Insert', '2018-04-27 17:20:18'),
('ADMIN', ' Multi Insert', '2018-04-27 17:20:18'),
('ADMIN', ' Multi Insert', '2018-04-27 17:20:18'),
('ADMIN', ' Multi Insert', '2018-04-27 17:20:18'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-27 17:23:19'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-28 08:56:05'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-28 11:25:13'),
('ADMIN', ' Multi Insert', '2018-04-28 13:00:44'),
('ADMIN', ' Multi Insert', '2018-04-28 13:00:44'),
('ADMIN', ' Multi Insert', '2018-04-28 13:00:44'),
('ADMIN', ' Multi Insert', '2018-04-28 13:00:44'),
('ADMIN', ' Multi Insert', '2018-04-28 13:41:41'),
('ADMIN', ' Multi Insert', '2018-04-28 13:41:42'),
('ADMIN', ' Multi Insert', '2018-04-28 13:41:42'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:18'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:18'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:18'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:18'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:24'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:25'),
('ADMIN', ' Multi Insert', '2018-04-28 14:08:25'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-28 16:23:02'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-29 08:48:21'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:39'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:40'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:40'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:40'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:53'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:53'),
('ADMIN', ' Multi Insert', '2018-04-29 08:48:53'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-29 23:42:48'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-30 17:06:39'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:01'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:01'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:01'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:01'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:10'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:10'),
('ADMIN', ' Multi Insert', '2018-04-30 17:07:10'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-04-30 22:51:45'),
('ADMIN', ' Multi Insert', '2018-04-30 22:53:48'),
('ADMIN', ' Multi Insert', '2018-04-30 22:53:48'),
('ADMIN', ' Multi Insert', '2018-04-30 22:53:48'),
('ADMIN', ' Multi Insert', '2018-04-30 22:53:48'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-01 08:11:07'),
('ADMIN', ' Multi Insert', '2018-05-01 08:11:32'),
('ADMIN', ' Multi Insert', '2018-05-01 08:11:32'),
('ADMIN', ' Multi Insert', '2018-05-01 08:11:32'),
('ADMIN', ' Multi Insert', '2018-05-01 08:11:32'),
('ADMIN', ' Multi Insert', '2018-05-01 08:12:01'),
('ADMIN', ' Multi Insert', '2018-05-01 08:12:02'),
('ADMIN', ' Multi Insert', '2018-05-01 08:12:02'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-01 08:26:02'),
('ADMIN', ' Multi Insert', '2018-05-01 08:26:25'),
('ADMIN', ' Multi Insert', '2018-05-01 08:26:25'),
('ADMIN', ' Multi Insert', '2018-05-01 08:26:25'),
('ADMIN', ' Multi Insert', '2018-05-01 08:26:25'),
('ADMIN', ' Multi Insert', '2018-05-01 08:29:18'),
('ADMIN', ' Multi Insert', '2018-05-01 08:29:18'),
('ADMIN', ' Multi Insert', '2018-05-01 08:29:18'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-01 08:42:06'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:21'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:21'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:21'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:21'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:27'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:28'),
('ADMIN', ' Multi Insert', '2018-05-01 08:42:28'),
('ADMIN', 'Berhasil Tambah Data Permintaan Barang Stok', '2018-05-01 15:40:55'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-01 23:09:25'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-01 23:09:41'),
('ADMIN', ' Multi Insert', '2018-05-01 23:10:43'),
('ADMIN', ' Multi Insert', '2018-05-01 23:10:43'),
('ADMIN', ' Multi Insert', '2018-05-01 23:10:43'),
('ADMIN', ' Multi Insert', '2018-05-01 23:10:43'),
('ADMIN', ' Multi Insert', '2018-05-01 23:11:48'),
('ADMIN', ' Multi Insert', '2018-05-01 23:11:48'),
('ADMIN', ' Multi Insert', '2018-05-01 23:11:48'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 11:03:20'),
('ADMIN', ' Multi Insert', '2018-05-02 11:03:37'),
('ADMIN', ' Multi Insert', '2018-05-02 11:03:37'),
('ADMIN', ' Multi Insert', '2018-05-02 11:03:37'),
('ADMIN', ' Multi Insert', '2018-05-02 11:03:37'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 14:40:55'),
('ADMIN', ' Multi Insert', '2018-05-02 14:41:10'),
('ADMIN', ' Multi Insert', '2018-05-02 14:41:10'),
('ADMIN', ' Multi Insert', '2018-05-02 14:41:10'),
('ADMIN', ' Multi Insert', '2018-05-02 14:41:10'),
('ADMIN', ' Multi Insert', '2018-05-02 21:52:05'),
('ADMIN', ' Multi Insert', '2018-05-02 21:52:05'),
('ADMIN', ' Multi Insert', '2018-05-02 21:52:05'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 22:33:37'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 22:33:51'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 22:33:59'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 22:34:02'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-02 22:34:17'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-03 13:51:15'),
('ADMIN', ' Multi Insert', '2018-05-03 13:51:37'),
('ADMIN', ' Multi Insert', '2018-05-03 13:51:37'),
('ADMIN', ' Multi Insert', '2018-05-03 13:51:37'),
('ADMIN', ' Multi Insert', '2018-05-03 13:51:37'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:16'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:17'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:17'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-03 13:52:23'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:39'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:39'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:39'),
('ADMIN', ' Multi Insert', '2018-05-03 13:52:39'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-03 13:55:15'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 19:30:21'),
('ADMIN', ' Multi Insert', '2018-05-04 20:22:04'),
('ADMIN', ' Multi Insert', '2018-05-04 20:22:04'),
('ADMIN', 'Berhasil Ubah Data Pasien', '2018-05-04 21:20:07'),
('ADMIN', 'Berhasil Ubah Data Pasien', '2018-05-04 21:20:21'),
('ADMIN', 'Berhasil Ubah Data Pasien', '2018-05-04 21:21:20'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:45:56'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:02'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:07'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:12'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:18'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:26'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:31'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:37'),
('ADMIN', 'Berhasil Ubah Data Barang', '2018-05-04 21:46:43'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-04 22:22:17'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-05 01:22:02'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:02'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:03'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:03'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:03'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:33'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:33'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:33'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-05 22:58:39'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:54'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:54'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:54'),
('ADMIN', ' Multi Insert', '2018-05-05 22:58:54'),
('ADMIN', ' Multi Insert', '2018-05-05 22:59:19'),
('ADMIN', ' Multi Insert', '2018-05-05 22:59:19'),
('ADMIN', ' Multi Insert', '2018-05-05 22:59:19'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-05 23:08:21'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:38'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:38'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:38'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:38'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:49'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:49'),
('ADMIN', ' Multi Insert', '2018-05-05 23:08:49'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-05 23:08:55'),
('ADMIN', ' Multi Insert', '2018-05-05 23:09:09'),
('ADMIN', ' Multi Insert', '2018-05-05 23:09:09'),
('ADMIN', ' Multi Insert', '2018-05-05 23:09:09'),
('ADMIN', ' Multi Insert', '2018-05-05 23:09:09'),
('ADMIN', 'Berhasil Menghapus Data Pasien', '2018-05-06 09:11:50'),
('ADMIN', 'Berhasil Tambah Data Pasien', '2018-05-06 09:17:56'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 09:18:46'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 09:20:31'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-05-06 09:20:43'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-05-06 09:20:46'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 09:21:05'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-05-06 09:24:34'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 09:27:48'),
('ADMIN', ' Multi Insert', '2018-05-06 09:28:59'),
('ADMIN', ' Multi Insert', '2018-05-06 09:28:59'),
('ADMIN', ' Multi Insert', '2018-05-06 09:28:59'),
('ADMIN', ' Multi Insert', '2018-05-06 09:29:00'),
('ADMIN', ' Multi Insert', '2018-05-06 09:30:33'),
('ADMIN', ' Multi Insert', '2018-05-06 09:30:33'),
('ADMIN', ' Multi Insert', '2018-05-06 09:30:33'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 10:11:36'),
('ADMIN', ' Multi Insert', '2018-05-06 10:11:50'),
('ADMIN', ' Multi Insert', '2018-05-06 10:11:50'),
('ADMIN', ' Multi Insert', '2018-05-06 10:11:50'),
('ADMIN', ' Multi Insert', '2018-05-06 10:11:50'),
('ADMIN', ' Multi Insert', '2018-05-06 10:12:10'),
('ADMIN', ' Multi Insert', '2018-05-06 10:12:10'),
('ADMIN', ' Multi Insert', '2018-05-06 10:12:10'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 10:41:18'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:31'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:31'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:31'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:31'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:43'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:43'),
('ADMIN', ' Multi Insert', '2018-05-06 10:41:43'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 11:00:53'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:10'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:10'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:10'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:10'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:25'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:25'),
('ADMIN', ' Multi Insert', '2018-05-06 11:01:25'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 11:36:53'),
('ADMIN', ' Multi Insert', '2018-05-06 11:37:08'),
('ADMIN', ' Multi Insert', '2018-05-06 11:37:08'),
('ADMIN', ' Multi Insert', '2018-05-06 11:37:08'),
('ADMIN', ' Multi Insert', '2018-05-06 11:37:08'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 11:47:49'),
('ADMIN', ' Multi Insert', '2018-05-06 12:20:59'),
('ADMIN', ' Multi Insert', '2018-05-06 12:20:59'),
('ADMIN', ' Multi Insert', '2018-05-06 12:20:59'),
('ADMIN', ' Multi Insert', '2018-05-06 12:21:00'),
('ADMIN', ' Multi Insert', '2018-05-06 12:21:00'),
('ADMIN', ' Multi Insert', '2018-05-06 12:21:00'),
('ADMIN', ' Multi Insert', '2018-05-06 12:21:00'),
('ADMIN', ' Multi Insert', '2018-05-06 12:21:00'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 12:31:37'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:55'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:55'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:55'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:55'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:55'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:55'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:56'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:56'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:56'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:56'),
('ADMIN', ' Multi Insert', '2018-05-06 12:31:56'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:16'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:16'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:16'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:16'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:16'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:16'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:17'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:17'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:17'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:17'),
('ADMIN', ' Multi Insert', '2018-05-06 12:32:17'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-06 12:34:08'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', ' Multi Insert', '2018-05-06 12:34:23'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-07 21:52:10'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:24'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:24'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:24'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:24'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:24'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:24'),
('ADMIN', ' Multi Insert', '2018-05-07 22:37:25'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-08 01:03:16'),
('ADMIN', 'Berhasil Ubah Data Dokter', '2018-05-08 19:48:56'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:39'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:39'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:39'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:39'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:39'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:39'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:50'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:50'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:50'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:50'),
('ADMIN', ' Multi Insert', '2018-05-08 21:53:50'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-08 22:02:21'),
('ADMIN', ' Multi Insert', '2018-05-08 22:02:42'),
('ADMIN', ' Multi Insert', '2018-05-08 22:02:42'),
('ADMIN', ' Multi Insert', '2018-05-08 22:02:42'),
('ADMIN', ' Multi Insert', '2018-05-08 22:02:42'),
('ADMIN', ' Multi Insert', '2018-05-08 22:02:42'),
('ADMIN', ' Multi Insert', '2018-05-08 22:02:42'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-09 00:22:31'),
('ADMIN', ' Multi Insert', '2018-05-09 00:22:51'),
('ADMIN', ' Multi Insert', '2018-05-09 00:22:51'),
('ADMIN', ' Multi Insert', '2018-05-09 00:22:52'),
('ADMIN', ' Multi Insert', '2018-05-09 00:22:52'),
('ADMIN', ' Multi Insert', '2018-05-09 00:22:52'),
('ADMIN', ' Multi Insert', '2018-05-09 00:22:52'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-10 00:06:28'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:01'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:01'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:01'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:01'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:01'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:01'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:02'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:02'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:02'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:02'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:02'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:22'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:22'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:22'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:22'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:22'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:22'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:23'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:23'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:23'),
('ADMIN', ' Multi Insert', '2018-05-10 00:07:23'),
('ADMIN', 'Berhasil Menghapus Data Perawatan', '2018-05-10 00:09:33'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:56'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:56'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:56'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', ' Multi Insert', '2018-05-10 00:50:57'),
('ADMIN', 'Berhasil Menghapus Data Perawatan', '2018-05-10 00:51:17'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-11 00:06:16'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 00:06:38'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:25'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', ' Multi Insert', '2018-05-11 01:00:26'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-11 02:34:59'),
('ADMIN', 'Berhasil Ubah Data Dokter', '2018-05-11 02:43:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:34'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:34'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:35'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:36'),
('ADMIN', ' Multi Insert', '2018-05-11 03:03:36'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-14 14:53:29'),
('ADMIN', 'Laporan Stok Gudang Kecil', '2018-05-14 18:56:21'),
('ADMIN', 'Laporan Stok Gudang Besar', '2018-05-14 18:57:29'),
('ADMIN', 'Laporan Stok Gudang Kecil', '2018-05-14 18:59:26'),
('ADMIN', 'Laporan Stok Gudang Kecil', '2018-05-14 18:59:48'),
('ADMIN', 'Laporan Stok Gudang Kecil', '2018-05-15 09:05:19'),
('ADMIN', 'Laporan Stok Gudang Besar', '2018-05-15 09:05:51'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-15 13:44:30'),
('ADMIN', 'Berhasil Tambah Data Permintaan Barang Stok', '2018-05-16 11:32:10'),
('ADMIN', ' Multi Insert', '2018-05-16 11:32:31'),
('ADMIN', ' Multi Insert', '2018-05-16 11:32:31'),
('ADMIN', 'Berhasil Tambah Data Permintaan Barang Stok', '2018-05-16 11:33:06'),
('ADMIN', ' Multi Insert', '2018-05-16 11:33:21'),
('ADMIN', ' Multi Insert', '2018-05-16 11:33:21'),
('ADMIN', ' Multi Insert', '2018-05-16 11:33:21'),
('ADMIN', ' Multi Insert', '2018-05-16 11:33:22'),
('ADMIN', 'Laporan Stok Gudang Kecil', '2018-05-16 14:50:52'),
('ADMIN', 'Laporan Stok Gudang Kecil', '2018-05-16 14:51:09'),
('ADMIN', 'Berhasil Tambah Data User', '2018-05-16 14:55:36'),
('ADMIN', 'Berhasil Tambah Data User', '2018-05-16 14:55:55'),
('ADMIN', 'Berhasil Tambah Data Penyesuaian Stok Gudang Besar', '2018-05-16 17:59:19'),
('ADMIN', 'Berhasil Tambah Data Penyesuaian Stok', '2018-05-16 18:00:15'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-16 19:25:19'),
('ADMIN', ' Multi Insert', '2018-05-16 19:25:31'),
('ADMIN', ' Multi Insert', '2018-05-16 19:25:31'),
('ADMIN', ' Multi Insert', '2018-05-16 19:25:31'),
('ADMIN', ' Multi Insert', '2018-05-16 19:25:31'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-16 19:25:55'),
('ADMIN', ' Multi Insert', '2018-05-16 19:26:08'),
('ADMIN', ' Multi Insert', '2018-05-16 19:26:08'),
('ADMIN', ' Multi Insert', '2018-05-16 19:26:08'),
('ADMIN', ' Multi Insert', '2018-05-16 19:26:08'),
('ADMIN', ' Multi Insert', '2018-05-16 19:40:08'),
('ADMIN', ' Multi Insert', '2018-05-16 19:40:08'),
('ADMIN', ' Multi Insert', '2018-05-16 19:40:08'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-16 20:01:42'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-05-16 20:08:28'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-16 20:24:05'),
('ADMIN', 'Berhasil Menghapus Data Antrian', '2018-05-16 20:24:10'),
('ADMIN', 'Berhasil Tambah Data Antrian', '2018-05-16 20:35:04'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:17'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:17'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:17'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:17'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:28'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:28'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:28'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:35'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:35'),
('ADMIN', ' Multi Insert', '2018-05-16 20:35:35');

-- --------------------------------------------------------

--
-- Table structure for table `tbmbarang`
--

CREATE TABLE `tbmbarang` (
  `IdBarang` int(11) NOT NULL,
  `NamaBarang` varchar(30) NOT NULL,
  `IdJenisBarang` int(11) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Keterangan` text NOT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbmbarang`
--

INSERT INTO `tbmbarang` (`IdBarang`, `NamaBarang`, `IdJenisBarang`, `Harga`, `Keterangan`, `Status`) VALUES
(1, 'WH-2', 1, 110000, ' ', 1),
(2, 'WH-1', 1, 110000, ' ', 1),
(3, 'WH-0', 1, 110000, ' ', 1),
(4, 'SABUN HIJAU', 1, 20000, ' ', 1),
(5, 'SHAMPO', 1, 75000, ' ', 1),
(6, 'SB**', 1, 135000, ' ', 1),
(7, 'SBB LUX', 1, 200000, ' ', 1),
(8, 'SB TUBE', 1, 100000, ' ', 1),
(9, 'SB KK', 1, 70000, ' ', 1),
(10, 'SB NW', 1, 110000, ' ', 1),
(11, 'MD3 GEL', 1, 160000, ' ', 1),
(12, 'MM', 1, 100000, ' ', 1),
(13, 'HG>', 1, 165000, ' ', 1),
(14, 'GENTA (RNI)', 1, 100000, ' ', 1),
(15, 'FKSC (SB-AH)', 1, 120000, ' ', 1),
(16, 'DEMY LOTION', 1, 35000, ' ', 1),
(17, 'DEMY 1', 1, 60000, ' ', 1),
(18, 'C3', 1, 150000, ' ', 1),
(19, 'C2', 1, 200000, ' ', 1),
(20, 'POWDER N', 1, 85000, ' ', 1),
(21, 'BNS', 1, 60000, ' ', 1),
(22, 'BSO', 1, 135000, ' ', 1),
(23, 'AHM-5 >', 1, 160000, ' ', 1),
(24, 'AHM 1<', 1, 90000, ' ', 1),
(25, 'SB A', 1, 70000, ' ', 1),
(26, 'Masker Kuning', 1, 35000, ' ', 1),
(27, 'Powder GL', 1, 110000, ' ', 1),
(28, 'RX-5 Serum', 1, 160000, ' ', 1),
(29, 'RX-2 Serum', 1, 160000, ' ', 1),
(30, 'AV-P', 1, 135000, ' ', 1),
(31, 'AVZ', 1, 110000, ' ', 1),
(32, 'AV-3', 1, 110000, ' ', 1),
(33, 'AHZ', 1, 110000, ' ', 1),
(34, 'AHA', 1, 90000, ' ', 1),
(35, 'AV-2', 1, 110000, ' ', 1),
(36, 'AV-1', 1, 110000, ' ', 1),
(37, 'AV-0', 1, 110000, ' ', 1),
(38, 'SB ACN', 1, 70000, ' ', 1),
(39, 'SB SPC', 1, 70000, ' ', 1),
(40, 'CM ', 1, 55000, ' ', 1),
(41, 'FW <', 1, 45000, ' ', 1),
(42, 'FW >', 1, 75000, ' ', 1),
(43, 'RC', 2, 60000, ' ', 1),
(44, 'FENES', 2, 40000, ' ', 1),
(45, 'COC', 2, 50000, ' ', 1),
(46, 'OBL', 2, 12000, ' ', 1),
(47, 'TL <', 1, 50000, ' ', 1),
(48, 'TP', 1, 70000, ' ', 1),
(49, 'TM', 1, 70000, ' ', 1),
(50, 'PMO', 1, 100000, ' ', 1),
(51, 'PP', 1, 110000, ' ', 1),
(52, 'OX-C', 1, 110000, ' ', 1),
(53, 'LIPBLAM', 1, 60000, ' ', 1),
(54, 'KSA-2', 1, 60000, ' ', 1),
(55, 'KINEXTIN **', 1, 150000, ' ', 1),
(56, 'KINEXTIN A', 1, 130000, ' ', 1),
(57, 'HB-10', 1, 160000, ' ', 1),
(58, 'BGA >', 1, 160000, ' ', 1),
(59, 'BIO GOLD', 1, 80000, ' ', 1),
(60, 'BIO SILVER', 1, 80000, ' ', 1),
(61, 'ACN -1', 1, 160000, ' ', 1),
(62, 'AHM-5 <', 1, 100000, ' ', 1),
(63, 'Masker Bubuk', 3, 0, ' ', 1),
(64, 'Sponge Facial', 3, 0, ' ', 1),
(65, 'AHM-1 >', 1, 160000, ' ', 1),
(66, 'RX-1 Serum', 1, 200000, ' ', 1),
(67, 'OSI.K', 2, 12000, ' ', 1),
(68, 'WH-3', 1, 110000, ' ', 1),
(69, 'WH-4', 1, 110000, ' ', 1),
(70, 'MSO-1', 1, 200000, ' ', 1),
(71, 'MSO-2', 1, 200000, ' ', 1),
(72, 'MSO-5', 1, 200000, ' ', 1),
(73, 'FC', 2, 80000, ' ', 1),
(74, 'LINDAN', 2, 55000, ' ', 1),
(75, 'PAN HAIR', 2, 60000, ' ', 1),
(76, 'Glution', 2, 90000, ' ', 1),
(77, 'Alation', 2, 80000, ' ', 1),
(78, 'Anti-Cell', 1, 90000, ' ', 1),
(79, 'BTMO', 1, 60000, ' ', 1),
(80, 'Compact GL ', 1, 110000, ' ', 1),
(81, 'Serum 2', 3, 50000, ' ', 1),
(82, 'Serum 5', 3, 50000, ' ', 1),
(83, 'Serum 8', 3, 50000, ' ', 1),
(84, 'Masker Collagen LG', 3, 35000, ' ', 1),
(85, 'Masker Collagen AC', 3, 35000, ' ', 1),
(86, 'KSO', 1, 110000, ' ', 1),
(87, 'PRO E', 2, 60000, ' ', 1),
(88, 'Masker biru', 1, 35000, ' ', 1),
(89, 'Kinextin AA', 1, 110000, ' ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbmbeautician`
--

CREATE TABLE `tbmbeautician` (
  `IdBeautician` int(11) NOT NULL,
  `NamaBeautician` varchar(50) NOT NULL,
  `NoTelepon` varchar(20) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Keterangan` text NOT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbmbeautician`
--

INSERT INTO `tbmbeautician` (`IdBeautician`, `NamaBeautician`, `NoTelepon`, `Alamat`, `Keterangan`, `Status`) VALUES
(1, 'Evi', '81274023404', 'jl. prabu siliwangi. RT 07. Kasang', '', 1),
(2, 'Idha', '85210680508', 'The Hok. Lrg. Merah Putih', '', 1),
(3, 'Juju', '', '', '', 1),
(4, 'Wana', '', '', '', 1),
(5, 'Ita', '', '', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbmdokter`
--

CREATE TABLE `tbmdokter` (
  `IdDokter` int(11) NOT NULL,
  `NamaDokter` varchar(50) NOT NULL,
  `NoSIP` varchar(255) NOT NULL,
  `NoTelepon` varchar(20) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Keterangan` text NOT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbmdokter`
--

INSERT INTO `tbmdokter` (`IdDokter`, `NamaDokter`, `NoSIP`, `NoTelepon`, `Alamat`, `Keterangan`, `Status`) VALUES
(1, 'Dr. Tieka Leony Febrianti', '446/087/DU/DINKES/2016', '081223456040', 'Jl. Tp. Sriwijaya. Perum. Melati Indah blok A no.0', '', 1),
(2, 'Dr. Rafika Pramasandy', 'TES/TES/TE/TESTES/TEST', '085282821986', 'Jl.Sunan Bonang No.02 Rt.17 Kel.Simp III Sipin', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbmpasien`
--

CREATE TABLE `tbmpasien` (
  `IdPasien` int(11) NOT NULL,
  `KodePasien` varchar(10) NOT NULL,
  `NamaPasien` varchar(50) NOT NULL,
  `JenisKelamin` enum('L','P') NOT NULL,
  `TanggalDaftar` date NOT NULL,
  `TanggaLahir` date NOT NULL,
  `NoTelpon` varchar(20) NOT NULL,
  `Pekerjaan` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Catatan` varchar(100) NOT NULL,
  `NoKartu` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbmpasien`
--

INSERT INTO `tbmpasien` (`IdPasien`, `KodePasien`, `NamaPasien`, `JenisKelamin`, `TanggalDaftar`, `TanggaLahir`, `NoTelpon`, `Pekerjaan`, `Email`, `Alamat`, `Catatan`, `NoKartu`) VALUES
(1, 'R 1622', 'REMOND AFRIANDA', 'L', '2017-04-23', '1986-04-23', '85266500579', '', '', '', '', ''),
(2, 'L 0873', 'LOLI', 'L', '2017-05-16', '2018-01-01', '85384394009', '', '', 'AUDURI', '', ''),
(3, 'G 0104', 'GIOK PENG', 'P', '2017-05-16', '2018-01-01', '85268681338', '', '', 'TALANG BANJAR', '', ''),
(4, 'D 1208', 'Devie Novallyan', 'P', '2017-04-22', '1982-03-27', '85266088911', 'Dosen IAIN Jambi', 'devienovallyan@yahoo.com', 'pematang Gajah - Mendalo', '', ''),
(5, 'M 0130', 'Meilan Dewina', 'P', '2017-04-22', '1980-05-25', '85266848855', 'Swasta', '', 'Jl. amangkurat - Tanjung Pinang', '', ''),
(6, 'L 0941', 'Lina Wati', 'P', '2017-04-22', '1974-09-06', '82152525218', 'IRT', '', 'Pakuan Baru', '', ''),
(7, 'K 0280', 'Karinda Wardani', 'P', '2017-04-22', '1990-11-28', '818546000', 'Pegawai BUMN', 'karindawardani@gmail.com', 'Perum. vila Kenali Permai', '', ''),
(8, 'P 0295', 'Putri A', 'P', '2017-04-22', '1997-09-04', '81373559629', 'Mahasiswa', '', 'Selincah', '', ''),
(9, 'L 0942', 'Lia Afrianti', 'P', '2017-04-22', '1995-04-23', '85208011133', 'Mahasiswi Unja', 'liaafrianti65@yahoo.com', 'Kel. tahtul Yaman. Kec. Pelayangan Sebrang', '', ''),
(10, 'S 1599', 'SEPTI ', 'P', '2017-04-22', '1993-09-15', '85267355731', 'mahasiswi unbari', 'septi.1234@gmail.com', 'tanjung sari rt 15', '', ''),
(11, 'Y 0933', 'YUNITA', 'P', '2017-04-22', '1995-09-23', '81366483531', 'MAHASISWI UNJA', '', 'PASIR PUTIH', 'ACNE, KOMEDO,KERING,BERUNTUSAN', ''),
(12, 'T 0001', 'TATI SETIAWAN', 'P', '2017-04-22', '1990-12-31', '82373300000', 'HONOR', '', 'SABAK', '', ''),
(13, 'E 116', 'ERMA OKTAVIANI', 'P', '2017-04-22', '1991-10-18', '85266562985', 'TENAGA KONTRAK', '', 'SMA 11 JAMBI', '', ''),
(14, 'D 0313', 'DYNA', 'P', '2017-04-22', '1982-04-26', '82177434105', 'SWASTA', 'DYNAAFRYANTI@GMAIL.COM', 'EKA JAYA', '', ''),
(15, 'N 1160', 'NANA', 'P', '2017-04-22', '2018-01-01', '82279661768', '', '', 'H. KAMIL', '', ''),
(16, 'S 1354', 'SERI', 'P', '2017-04-22', '1994-03-03', '85266955645', 'PNS', '', 'JELUTUNG', '', ''),
(17, 'N 1225', 'NOVITA SRI YANTI', 'P', '2017-04-22', '2018-01-01', '1366044173', 'PNS KESEHATAN ', 'NOVITASRIYANTI@YAHOO.COM', 'TALANG BANJAR', '', ''),
(18, 'W 0001', 'WISMA WATI', 'P', '2017-04-22', '1977-04-11', '81273507929', '', '', 'KUMPEH, PAAL 8', '', ''),
(19, 'V 0001', 'VIVI', 'P', '2017-04-22', '1989-05-17', '8538286330', '', '', 'THEHOK HARAPAN', '', ''),
(20, 'D 0982', 'DEWI KASMIATI', 'P', '2017-04-22', '1986-04-21', '85382597464', 'PERAWAT', '', 'TANJAB TIMUR', '', ''),
(21, 'T  0546', 'TIARA', 'P', '2017-04-22', '1979-10-01', '81366657665', 'IRT', '', 'TAMAN ACI', '', ''),
(22, 'R 1476', 'RATNA', 'P', '2017-04-23', '1973-10-21', '81367198898', 'IRT', '', 'PASIR PUTIH', '', ''),
(23, 'S 0001', 'SRI RAHAYU', 'P', '2017-04-23', '1969-07-19', '81366669311', 'GURU', '', 'JL. MADRASAH RT.10 N0 33 PAYO SELINCAH', '', ''),
(24, 'H 0317', 'HELPI', 'P', '2017-04-23', '1969-02-20', '81274133838', 'PNS GURU', '', 'MAYANG', '', ''),
(25, 'R 1624', 'RITA RIZKI AMELIA', 'P', '2017-04-23', '1996-09-27', '82371700020', 'MAHASISWI UNBARI', 'RITA.RIZKI96@GMAIL.COM', 'KASANG', '', ''),
(26, 'E 0849', 'ETI', 'P', '2017-04-23', '1963-09-08', '81368185469', 'IRT', '', 'SIMPANG KAWAT', '', ''),
(27, 'S 1090', 'SELLY', 'P', '2017-04-23', '2018-01-01', '85377733737', '', '', 'SIMPANG DUREN', '', ''),
(29, 'S 0838', 'SISKA', 'P', '2017-04-23', '2018-01-01', '85767111272', '', '', 'TEMBESI', '', ''),
(30, 'S 1476', 'SALAMAH', 'P', '2017-04-23', '2018-01-01', '82180318598', '', '', 'LORONG MARENE', '', ''),
(31, 'D 0001', 'DELISA', 'P', '2017-04-24', '1995-12-12', '82280254814', 'PUKESMAS', 'delisia zainudin@yahoo.com', 'Abadi 1 perum griya arahim lintas barat. Terminal ', '', ''),
(32, 'N 1232', 'NURI', 'P', '2017-04-24', '1992-06-26', '81367412561', 'PRAKTEK DOKTER', '', 'KAMPUNG LAUT', '', ''),
(33, 'N 1259', 'NEGA', 'P', '2017-04-24', '1997-01-07', '85381909680', 'MAHASISWI', '', 'MENDALO', '', ''),
(34, 'E 1192', 'EBIT', 'L', '2017-04-25', '1997-06-15', '82280064106', '', 'Franando@gmail.com', 'TALANG BANJAR', '', ''),
(35, 'W 0020', 'WATI', 'P', '2017-04-25', '1975-08-11', '7417554809', 'IRT', '', 'CEMPAKA PUTIH', '', ''),
(36, 'O 0105', 'OKTAVIANI', 'P', '2017-04-25', '2018-01-01', '82375148400', 'MAHASISWI', '', 'PERUMNAS AUDURI', '', ''),
(37, 'S 1619', 'SANTI HARIANI', 'P', '2017-04-25', '1980-02-16', '81366357564', 'IRT', '', 'PAKUAN BARU', '', ''),
(38, 'R 1212', 'RINI', 'P', '2017-04-25', '1984-01-20', '81366870316', 'IRT', '', 'KENALI BAWAH', '', ''),
(39, 'Y 0184', 'YANTI', '', '2017-04-25', '2018-01-01', '82176335554', '', '', 'TALANG BANJAR', '', ''),
(40, 'H 0456', 'HERY', 'L', '2017-04-25', '2018-01-01', '81366388888', '', '', 'BUDIMAN', '', ''),
(41, 'N 0005', 'NIKE', 'P', '2017-04-25', '2018-01-01', '81367733336', '', '', 'LORONG GEMBIRA', '', ''),
(42, 'E 0633', 'ENI', 'P', '2017-04-25', '2018-01-01', '85266810852', '', '', 'PAAL MERAH LAMA', '', ''),
(43, 'B 0065', 'BADRIAH', 'P', '2017-04-25', '2018-01-01', '8,19946E+11', '', '', 'SIPIN', '', ''),
(44, 'E 0845', 'EVA', 'P', '2017-04-27', '2018-01-01', '85377621201', '', '', 'KASANG PUDAK', '', ''),
(45, 'R 1625', 'RIZKI', 'P', '2017-04-27', '1994-11-05', '85384321266', 'MAHASISWA UNJA', 'Rizkysutami05@gmail.com', 'SETIA BUDI', '', ''),
(46, 'T 0467', 'TINA', 'P', '2017-04-27', '2018-01-01', '81333565629', '', '', 'SIMPANG RIMBO', '', ''),
(47, 'S 1601', 'SUKMA', 'P', '2017-04-27', '2018-01-01', '81366570234', '', '', 'LORONG PIPA', '', ''),
(49, 'Z 0117', 'ZULAIHA', 'P', '2017-04-27', '2017-04-27', '85709687393', '', '', 'TALANG BAKUNG', '', ''),
(50, 'R 1532', 'ROMAULI', 'P', '2017-04-27', '2018-01-01', '', '81373452304', '', 'TELANAI', '', ''),
(51, 'E 1195', 'ERNA', 'P', '2017-04-27', '1985-06-02', '85246229770', 'SWASTA', '', 'KASANG PUDAK', '', ''),
(52, 'Z 0121', 'ZARRA MAULIDYA', 'P', '2017-04-27', '1996-08-09', '82282495545', 'MAHASISWI UNJA', '', 'RAWASARI , KOTA BARU', '', ''),
(53, 'D 1231', 'DEVI', 'P', '2017-04-27', '1988-11-02', '8117455443', 'BUMN', 'trihandayani.devi@yahoo.com', 'TANJUNG PINANG', '', ''),
(54, 'E 1075', 'EVITA', 'P', '2017-04-27', '1994-08-12', '8127211298', 'MAHASISWA UNBARI', '', 'KASANG PUDAK', '', ''),
(55, 'W 0460', 'WATI', 'P', '2017-04-27', '2018-01-01', '82280710014', '', '', 'JL.LETKOL M ISYA BELAKANG DKT', '', ''),
(56, 'O 0104', 'ODY', 'L', '2017-04-27', '2018-01-01', '85269846710', 'BANK BRI', '', 'KONI', '', ''),
(57, 'O 0108', 'OSMA', 'P', '2017-04-27', '1982-05-18', '81365633074', 'IRT', '', 'PAAL MERAH LAMA', '', ''),
(58, 'A 1498', 'AMI SALAMH', 'P', '2017-04-27', '2018-01-01', '81366208802', '', '', 'SUNGAI RENGAS', '', ''),
(59, 'L 0931', 'LIA', 'P', '2017-04-27', '2018-01-01', '85266122897', '', '', 'BULURAN', '', ''),
(60, 'L 0493', 'LIE MEI SHE', 'P', '2017-04-27', '2018-01-01', '81271198891', '', '', 'TALANG BANJAR', '', ''),
(61, 'N 1261', 'NINING', 'P', '2017-04-27', '1989-10-11', '85269953024', 'PERAWAT RS. MATTAHER', '', 'KOMP, BELIUNG INDAH', '', ''),
(62, 'R.1618', 'RINA', 'P', '2017-04-27', '2018-01-01', '85214907112', '', '', 'THEHOK', '', ''),
(63, 'I 0828', 'INAS TRI RHAMADHANTI', 'P', '2017-04-27', '1998-12-25', '82384655212', 'MAHASISWI UNJA', '', 'KOTABARU', '', ''),
(64, 'Y 1232', 'YUFDAUL IKSAN', 'P', '2017-04-27', '1996-09-13', '8975011912', 'MAHASISWA B. JEPANG', '', 'EKA JAYA', '', ''),
(65, 'Y 0912', 'YULIA', 'P', '2017-04-27', '2018-01-01', '81271023815', '', '', 'TELANAI', '', ''),
(66, 'D 1232', 'DAVID', 'L', '2017-04-27', '1992-04-08', '85266085683', 'Swasta', '', 'TALANG BAKUNG', '', ''),
(67, 'M 1316', 'MARWIYAH', 'P', '2017-04-28', '2018-01-01', '81368445793', 'PNS GURU', '', 'PEMATANG SULUR', '', ''),
(68, 'M 1283', 'MAYANG', 'P', '2017-04-28', '2018-01-01', '82273612935', '', '', 'PIJOAN', '', ''),
(69, 'N 1260', 'NILAM SARI', 'P', '2017-04-28', '2059-04-21', '81366090194', 'PNS GURU', '', 'TELANAI PURA', '', ''),
(70, 'S 1620', 'SRI RAJA GUKGUK', 'P', '2017-04-28', '2062-11-30', '81262129570', 'PNS GURU', '', 'DEPATI PURBA RT 16', '', ''),
(71, 'A 1525', 'ARDIYA RISWANA', 'P', '2017-04-28', '2018-01-01', '82242850895', 'PELAJAR', 'ardiyarisuanaa@gmail.com', 'KOTA BARU', '', ''),
(72, 'A 1524', 'ANDI KURANTO', 'P', '2017-04-28', '2018-01-01', '85378686869', '', '', 'THEHOK', '', ''),
(73, 'H 0373', 'HERNIDA GINTING', 'P', '2017-04-28', '2018-01-01', '85262371326', '', '', 'SIMPANG RIMBO', '', ''),
(74, 'R 1626', 'RODHIYAH MARDHIYA', 'P', '2017-04-28', '1999-09-29', '89626972228', 'MAHASISWI ', '', 'PASIR PUTIH', '', ''),
(75, 'S 1482', 'SISKA', 'P', '2017-04-28', '2018-01-01', '89607093438', '', '', 'KEBUN HANDIL', '', ''),
(76, 'N 0098', 'NONI', 'L', '2017-05-16', '2018-01-01', '89632852284', '', '', 'THEHOK', '', ''),
(77, 'I 0754', 'IMMANITA', 'P', '2017-05-16', '2018-01-01', '85266862874', '', '', 'BULIAN', '', ''),
(78, 'D 0056', 'DARMA', 'P', '2017-04-28', '2018-01-01', '81272530882', '', '', 'NIPAH PANJANG (MENDALO', '', ''),
(79, 'M 0714', 'MITA', 'P', '2017-04-28', '2018-01-01', '85380090186', '', '', 'BRONI', '', ''),
(80, 'M 1188', 'MURSILAWATI', 'P', '2017-04-28', '2018-01-01', '85267418495', '', '', 'JERAMBAH BOLONG', '', ''),
(81, 'S 1525', 'SOFIA', 'P', '2017-04-28', '2018-01-01', '81279021320', '', '', 'KUMPE', '', ''),
(82, 'R 1294', 'RIA', '', '2017-04-28', '1991-04-29', '81272306774', 'SWASTA', '', 'PANGLIMA POLIM NO 18', '', ''),
(83, 'K 0210', 'Kiptia', 'P', '2017-04-28', '1972-01-01', '85266042404', 'Swasta', '', 'Jl. KH. Hasan Anam No. 36', '', ''),
(84, 'K 0276', 'KINANTY HALIM', 'P', '2017-04-29', '2018-01-01', '82178529888', '', '', 'BUDIMAN', '', ''),
(85, 'T 0515', 'TIJA ATITA SARI', 'P', '2017-04-29', '2018-01-01', '82307121542', '', '', 'SIPIN TELUK DUREN', '', ''),
(86, 'L 0943', 'LIANA', 'P', '2017-04-29', '2067-06-06', '8127494153', 'IRT', '', 'THEHOK', '', ''),
(87, 'I 0804', 'ISTIFATUL LAELA', 'P', '2017-04-30', '2018-01-01', '85263449601', '', '', 'KASANG PUDAK', '', ''),
(88, 'S 0816', 'SITI KOMDIAH', 'P', '2017-04-30', '2018-01-01', '81366612578', '', '', 'SUNGAI GELAM', '', ''),
(89, 'L 0880', 'LARAS', 'P', '2017-04-30', '2018-01-01', '81272524961', '', '', 'TALANG BANJAR', '', ''),
(90, 'S 1471', 'SADARIAH', 'P', '2017-04-30', '2018-01-01', '82176773612', '', '', 'KOMPLEK KEHUTANAN', '', ''),
(91, 'A 1517', 'ANGGIE', 'P', '2017-04-30', '2018-01-01', '82373303985', '', '', 'KEBUN HANDIL', '', ''),
(92, 'W 0387', 'WIDIA', 'P', '2017-05-01', '2018-01-01', '85384179111', '', '', 'TELANAI', '', ''),
(93, 'D 0002', 'DINA MITI', 'P', '2017-05-01', '2018-01-01', '82182211115', '', '', 'KENALI ATAS', '', ''),
(94, 'Y 0001', 'YULIAWATI', 'P', '2017-05-01', '2066-08-30', '82181690062', '', '', 'TALANG BANJAR', '', ''),
(95, 'S 1579', 'SAKTI MAULISA', 'P', '2017-05-01', '2018-01-01', '82376793107', '', '', 'SUNGAI PENUH', '', ''),
(96, 'S 0131', 'SULISTIANI', 'P', '2017-05-02', '2018-01-01', '81366787060', '', '', 'KEBUN HANDIL', '', ''),
(97, 'M 1319', 'MAHDALENA', 'P', '2017-05-02', '2018-01-01', '82280891761', '', '', 'KUMPE', '', ''),
(98, 'R 1627', 'RUDI SAPUTRA', 'P', '2017-05-02', '2018-01-01', '81366074828', '', '', 'KASANG', '', ''),
(99, 'A 1526', 'ANI VANITA', 'P', '2017-05-02', '2018-01-01', '85217933771', 'IBU RUMAH TANGGA', '', 'PURI CANTIK', '', ''),
(100, 'W 0426', 'WINDA', 'P', '2017-05-02', '2018-01-01', '81251237800', '', '', 'PAAL MERAH', '', ''),
(101, 'S  1330', 'SUMARTINI', 'P', '2017-05-02', '2018-01-01', '85266092916', '', '', 'MENDALO', '', ''),
(102, 'L 0907', 'LAMIA', 'P', '2017-05-02', '2018-01-01', '82378451248', '', '', 'KERINCI', '', ''),
(103, 'A 1415', 'AYU', 'P', '2017-05-16', '2018-01-01', '82281238121', '', '', 'SIPIN', '', ''),
(104, 'N 1263', 'NOVIA', 'P', '2017-05-02', '2018-01-01', '85384069276', '', '', 'LEBAK BANDUNG', '', ''),
(105, 'L 0936', 'LENSI', 'P', '2017-05-02', '2018-01-01', '85269876032', 'IRT', '', 'TANGKIT', '', ''),
(106, 'I 0829', 'IKE AGNESTIA SANJAYA', 'P', '2017-05-02', '1998-08-27', '85384958915', 'MAHASISWI POLTEKKES', '', 'KASANG', '', ''),
(107, 'N 1264', 'NANI SURYANI', 'P', '2017-05-02', '1977-07-29', '8,53797E+11', 'IRT', '', 'KASANG JAYA', '', ''),
(108, 'S 1544', 'SANTI', 'P', '2017-05-04', '2018-01-01', '82372860606', '', '', 'TEMBESI', '', ''),
(109, 'S 1593', 'SALSA', 'P', '2017-05-04', '2018-01-01', '81373222544', '', '', 'STM ATAS', '', ''),
(110, 'F  0472', 'FITRI', 'P', '2017-05-04', '2018-01-01', '82281548084', '', '', 'KASANG', '', ''),
(111, 'I 0812', 'ISNA DEWI', 'P', '2017-05-04', '2018-01-01', '85273103304', '', '', 'TANJUNG  SARI', '', ''),
(112, 'M 0811', 'MAYA', 'P', '2017-05-04', '2018-01-01', '87885844853', '', '', 'KASANG  JAYA', '', ''),
(113, 'S 1418', 'SATRIA', 'P', '2017-05-04', '2018-01-01', '81366610662', '', '', 'TANJUNG PINANG', '', ''),
(114, 'R  1599', 'RENI', 'P', '2017-05-04', '2018-01-01', '82379583727', '', '', 'KONI', '', ''),
(115, 'S 1622', 'SONIA', 'P', '2017-05-04', '1986-04-02', '81284265092', '', '', 'PANCAKARIA', '', ''),
(116, 'N 1014', 'NETI', 'P', '2017-05-04', '2018-01-01', '85383183888', '', '', 'TALANG BANJAR', '', ''),
(117, 'Y 0970', 'YENI SUHANNI', 'P', '2017-05-04', '1975-05-20', '85266496428', 'WIRASWASTA', '', 'TAMBAK SARI', '', ''),
(118, 'S 0063', 'SISKA', 'P', '2017-05-04', '1994-06-23', '85266593003', 'IRT', '', 'KASANG PUDAK', '', ''),
(119, 'H 0560', 'HIDAYATI', 'P', '2017-05-04', '1971-05-04', '85266593002', 'IRT', '', 'KASANG PUDAK', '', ''),
(120, 'U 0115', 'UMI MAIMUNAH', 'P', '2017-05-04', '2018-01-01', '82180105888', '', '', 'PURI MAYANG', '', ''),
(121, 'Y 0845', 'YUSNANI', 'P', '2017-05-04', '2018-01-01', '85268670954', '', '', 'KUMPE', '', ''),
(122, 'M 1263', 'MERRY', 'P', '2017-05-04', '2018-01-01', '82185829898', '', '', 'KEBUN HANDIL', '', ''),
(123, 'T 0547', 'TRISNA', 'P', '2017-05-04', '1986-01-11', '82380907788', 'PERAWAT', '', 'JELUTUNG', '', ''),
(124, 'S 1621', 'SIWI', 'P', '2017-05-04', '2018-01-01', '82211700883', '', '', 'SIMP PULAI', '', ''),
(125, 'N 1122', 'NOVI', 'P', '2017-05-04', '2018-01-01', '85369252300', '', '', 'BRONI', '', ''),
(126, 'S 1481', 'SRI', 'P', '2017-05-04', '2018-01-01', '853689497', '', '', 'CEMPAKA PUTIH', '', ''),
(127, 'I 0727', 'IDA', 'P', '2017-05-04', '2018-01-01', '85377482000', '', '', 'GRAGAI', '', ''),
(128, 'I 0770', 'IIN', 'P', '2017-05-04', '2018-01-01', '82186314834', '', '', 'SAROLANGUN', '', ''),
(129, 'M 1309', 'MUSLIFA', 'P', '2017-05-04', '2018-01-01', '85215913880', '', '', 'KASANG PUDAK', '', ''),
(130, 'M 1320', 'MEGA APRIANA .M', 'P', '2017-05-04', '1999-04-10', '81257548399', 'PELAJAR', '', 'TELANAI', '', ''),
(131, 'M 0769', 'MESI', 'P', '2017-05-04', '2018-01-01', '89665374022', '', '', 'PAKUAN BARU', '', ''),
(132, 'D 1130', 'DILA', 'P', '2017-05-04', '2018-01-01', '8127490191', '', '', 'MUARO JAMBI', '', ''),
(133, 'A 1176', 'ANISA', 'P', '2017-05-04', '2018-01-01', '85380393777', '', '', 'SEBRANG', '', ''),
(134, 'M 1113', 'MEGA', 'P', '2017-05-04', '2018-01-01', '81290907070', '', '', 'TELANAI', '', ''),
(135, 'T 0493', 'TUTI', 'P', '2017-05-04', '2018-01-01', '853781888', '', '', 'BRONI', '', ''),
(136, 'C 0175', 'CINTIA', 'P', '2017-05-05', '2018-01-01', '85373386402', 'PELAJAR', '', 'BULIAN', '', ''),
(137, 'L 0944', 'LEONARD', '', '2017-05-05', '2001-12-02', '8117404388', 'PELAJAR', '', 'BUDIMAN', '', ''),
(138, 'W 0461', 'WIDYA NITA SAHRONI', 'P', '2017-05-05', '2018-01-01', '82306052002', '', '', 'SIPIN', '', ''),
(139, 'G 0118', 'GINA ', 'P', '2017-05-05', '2018-01-01', '81373198323', '', '', 'KEBUN JERUK', '', ''),
(140, 'S 1614', 'SANTI', 'P', '2017-05-05', '2018-01-01', '', '', '', 'BULIAN', '', ''),
(141, 'S 1623', 'SITI MARIAM', 'P', '2017-05-05', '1972-03-06', '82297059534', 'IRT', '', 'TUNGKAL', '', ''),
(142, 'I 0830', 'INDAH EFRIYANTI', 'P', '2017-05-05', '1998-10-30', '82175609923', '', '', 'BULURAN', '', ''),
(143, 'F 0606', 'FARAS RAMADHANI', 'P', '2017-05-05', '1999-12-18', '81394906934', '', '', 'TALANG BAKUNG', '', ''),
(144, 'D 1160', 'DIANA', 'P', '2017-05-05', '2018-01-01', '82376353994', '', '', 'JAMBI TULO', '', ''),
(145, 'D 1108', 'DEWI', 'P', '2017-05-05', '2018-01-01', '81372059444', '', '', 'SELINCAH', '', ''),
(146, 'T 0548', 'TIA', 'P', '2017-05-05', '1998-02-15', '85357169836', 'SWASTA', '', 'KASANG PUDAK', '', ''),
(147, 'T 0317', 'TRI WIDYANINGSIH', 'P', '2017-05-05', '2018-01-01', '8127431221', '', '', 'TANJUNG PINANG', '', ''),
(148, 'D 1207', 'DINA KRISNAWATI', 'P', '2017-05-05', '2018-01-01', '85266936996', '', '', 'PURNAMA', '', ''),
(149, 'E 0531', 'EVI', 'P', '2017-05-05', '2018-01-01', '81539971099', '', '', 'MAYANG', '', ''),
(150, 'Y 0971', 'YUSDARSUTRIANI', 'P', '2017-05-05', '1984-09-25', '85379532005', 'POLRI', '', 'PERUM TEGUH PERMAI', '', ''),
(151, 'Y 0256', 'YUNI', 'P', '2017-05-05', '2018-01-01', '81366002566', '', '', 'PAAL MERAH LAMA', '', ''),
(152, 'Y 0960', 'YENI MAYGHITA', 'P', '2017-05-05', '2018-01-01', '82280449054', '', '', 'SELINCAH', '', ''),
(153, 'A 1527', 'ANINDYA DESI PRAMESWARI', 'P', '2017-05-05', '1998-12-28', '81233333807', 'MAHASISWI STIKOM', '', 'TALANG BAKUNG', '', ''),
(154, 'E 1176', 'ELIS', 'P', '2017-05-05', '2018-01-01', '85266752929', '', '', 'JERAMBAH BOLONG', '', ''),
(155, 'L 0261', 'LIA', 'P', '2017-05-05', '2018-01-01', '81366517571', '', '', 'TALANG BAKUNG', '', ''),
(156, 'W 0382', 'WINDI INDRIANI', 'P', '2017-05-05', '1991-01-19', '81278136739', '', '', 'TERMINAL BARU', '', ''),
(157, 'R 1503', 'RINA', 'P', '2017-05-05', '2018-01-01', '85292307255', 'IRT', '', 'MENDALO', '', ''),
(158, 'O 0056', 'ORY', 'P', '2017-05-05', '2018-01-01', '85382640118', '', '', 'BULIAN', '', ''),
(159, 'I 0816', 'IIN INAWATI', 'P', '2017-05-08', '2018-01-01', '89676851821', '', '', 'EKAJAYA', '', ''),
(160, 'E 1197', 'ERWIN LESTARI', 'P', '2017-05-08', '2018-01-01', '85262598117', '', '', 'MUARO SEBO', '', ''),
(161, 'I 0831', 'IRYANI', 'P', '2017-05-08', '1975-10-25', '82282261044', 'IRT', '', 'SAROLANGUN', '', ''),
(162, 'R 0533', 'RUSMINI', 'P', '2017-05-08', '2018-01-01', '81994744639', '', '', 'SELINCAH', '', ''),
(163, 'R 0981', 'RIANA', 'P', '2017-05-08', '1984-08-14', '81366455738', 'IRT', '', 'BUKIT BALING', '', ''),
(164, 'R 1628', 'RISA', 'P', '2017-05-08', '1986-11-10', '81367136260', 'IRT', '', 'BUKIT BALING', '', ''),
(165, 'M 1259', 'MEI-MEI', 'P', '2017-05-08', '2018-01-01', '82214152772', '', '', 'KONI', '', ''),
(166, 'N 1249', 'NUR HAYULIS', 'P', '2017-05-08', '2018-01-01', '82392351020', '', '', 'BRONI', '', ''),
(167, 'A 1445', 'ANI', 'P', '2017-05-08', '2018-01-01', '81339665453', '', '', 'TALANG BAKUNG', '', ''),
(168, 'T 0512', 'TITIN', 'P', '2017-05-08', '2018-01-01', '85266680988', '', '', 'TALANG BAKUNG', '', ''),
(169, 'H 0468', 'HURIAH', 'P', '2017-05-08', '2018-01-01', '85366416434', '', '', 'BULIAN ( LR. HIDAYAT)', '', ''),
(170, 'A 1495', 'AYU', 'P', '2017-05-08', '2018-01-01', '81272062342', '', '', 'KEBUN KOPI', '', ''),
(171, 'E 1066', 'ENA', 'P', '2017-05-08', '2018-01-01', '85266999853', '', '', 'AUDURI', '', ''),
(172, 'Y 0926', 'YOPI', 'L', '2017-05-08', '2018-01-01', '89676060896', '', '', 'PAL 5 KOTA BARU', '', ''),
(173, 'S 1008', 'SARAH', 'P', '2017-05-08', '2018-01-01', '81594539274', '', '', 'TELANAI PURA', '', ''),
(174, 'N 1044', 'NURUL AINI', 'P', '2017-05-08', '2018-01-01', '89681373511', '', '', 'PERUM AUDURI', '', ''),
(175, 'L 0945', 'LUSIA SYAFRIAN TAMA', 'P', '2017-05-08', '1992-01-03', '82284270238', 'SWASTA', '', 'TELANAI PURA', '', ''),
(176, 'M 1269', 'MUJIATI', 'P', '2017-05-08', '2018-01-01', '82371808209', '', '', 'MERSAM', '', ''),
(177, 'R 0018', 'RITA SINAGA', 'P', '2017-05-08', '2018-01-01', '8127847653', '', '', 'AUDURI', '', ''),
(178, 'E 0009', 'EMA FATMA', 'P', '2017-05-16', '2018-01-01', '81274689200', '', '', 'SUNGAI PENUH', '', ''),
(179, 'Y 0972', 'YENI', 'P', '2017-05-08', '1988-03-05', '85266625050', 'BUMD', '', 'NUSA INDAH', '', ''),
(180, 'S 1166', 'SUSI', 'P', '2017-05-08', '2018-01-01', '82378465664', '', '', 'KUMPEH', '', ''),
(181, 'M  1321', 'MEDIA', 'P', '2017-05-08', '2018-01-01', '82297407557', '', '', 'BANYU LINCIR', '', ''),
(182, 'R 1630', 'RAHMAWATI', 'P', '2017-05-16', '1972-10-10', '82387496634', '', '', 'JERAMBAH BOLONG', '', ''),
(183, 'E 1145', 'EWILDA', 'P', '2017-05-08', '2018-01-01', '85379360331', '', '', 'MAYANG', '', ''),
(184, 'A 1505', 'ADE', 'P', '2017-05-08', '2018-01-01', '81291496499', '', '', 'SLINCAH', '', ''),
(185, 'T 0549', 'TITA WIDYA UTARI', 'P', '2017-05-08', '1997-11-11', '81278136855', 'MAHASISWI', '', 'PATTIMURA', '', ''),
(186, 'H 0383', 'HIKMA', 'P', '2017-05-08', '2018-01-01', '85271967951', '', '', 'KARYA MAJU', '', ''),
(187, 'A 1512', 'ASRINI', 'P', '2017-05-08', '2018-01-01', '81366148199', '', '', 'KEBUN KOPI', '', ''),
(188, 'Y 0579', 'YESIA', 'P', '2017-05-08', '2018-01-01', '82380086067', '', '', 'TELANAI PURA', '', ''),
(189, 'D 1202', 'DEWI', 'P', '2017-05-08', '2018-01-01', '82183735956', '', '', 'TALANG BAKUNG', '', ''),
(190, 'W 0462', 'WIDI ROSI NOVRIAN', 'P', '2017-05-08', '1996-11-05', '85709980242', 'MAHASISWI STIKBA JAMBI', '', 'KOTA BARU', '', ''),
(191, 'R 1629', 'RINI', 'P', '2017-05-08', '1971-01-01', '85319398559', 'SWASTA', '', '', '', ''),
(192, 'c 0013', 'cynthia', '', '2017-05-08', '2017-05-08', '', '', '', '', '', ''),
(193, 'G 0098', 'GALUH', 'P', '2017-05-08', '2018-01-01', '82377171109', '', '', 'TALANG BAKUNG', '', ''),
(194, 'T 0490', 'TATA', 'P', '2017-05-08', '2018-01-01', '81274232376', '', '', 'KEBUN KOPI', '', ''),
(196, 'R 1557', 'RIKA', 'P', '2017-05-08', '2018-01-01', '81363377994', '', '', 'SUNGAI BAHAR', '', ''),
(197, 'M 1224', 'MARDIAH', 'P', '2017-05-09', '2018-01-01', '81278371977', '', '', 'MAYANG', '', ''),
(198, 'M 1282', 'MIFTAHUL JANNAH', 'P', '2017-05-09', '2018-01-01', '85266737324', '', '', 'EKAJAYA', '', ''),
(199, 'S 1626', 'SISKA', 'P', '2017-05-09', '1974-01-25', '82299866566', 'IRT', '', 'PERSIJAM', '', ''),
(200, 'S 0287', 'SAMINAH', 'P', '2017-05-09', '2018-01-01', '', '', '', 'PAYO SELINCAH', '', ''),
(201, 'L 0946', 'LETI', 'P', '2017-05-09', '1995-08-09', '82186846200', '', '', 'TALANG BAKUNG', '', ''),
(203, 'E 0681', 'ERNAWATI', 'P', '2017-05-10', '2018-01-01', '85382384004', '', '', 'NIPAH PANJANG', '', ''),
(204, 'S 1625', 'SHINTA', 'P', '2017-05-10', '1992-01-08', '85366238899', 'KONTRAKTOR', '', 'SELINCAH', '', ''),
(205, 'S 1624', 'SRI WAHYUNI', 'P', '2017-05-10', '1990-07-17', '82312695565', 'TRAPIS SPA', '', 'KASANG PUDAK', '', ''),
(206, 'D 1166', 'DIANAA', 'P', '2017-05-10', '2018-01-01', '85379812759', '', '', 'TALANG BAKUNG', '', ''),
(207, 'A 1132', 'ASIH', 'P', '2017-05-10', '2018-01-01', '81366193699', 'BIDAN', '', 'SELINCAH', '', ''),
(208, 'B 0165', 'BETY', 'P', '2017-05-10', '2018-01-01', '82183980133', '', '', 'KENALI', '', ''),
(209, 'T 0545', 'TATI NURHAYATI', 'P', '2017-05-10', '2018-01-01', '81366744371', '', '', 'SAROLANGUN', '', ''),
(210, 'A 0786', 'AYU', 'P', '2017-05-10', '2018-01-01', '85789900867', '', '', 'PALL MERAH', '', ''),
(211, 'A 1521', 'ASIH', 'P', '2017-05-10', '2018-01-01', '85366299289', '', '', 'KASANG PUDAK', '', ''),
(212, 'A 1319', 'APRILIA', 'P', '2017-05-10', '2018-01-01', '81373074024', '', '', 'SAROLANGUN', '', ''),
(213, 'I 0111', 'IDHA', 'P', '2017-05-10', '2018-01-01', '81219335229', '', '', 'SAROLANGUN', '', ''),
(214, 'R 0229', 'RABA\'AH', 'P', '2017-05-10', '2018-01-01', '85367696508', '', '', 'KUMPE', '', ''),
(215, 'N 1265', 'NORMAWATI', 'P', '2017-05-10', '1985-07-20', '85265968413', 'IRT', '', 'TANGKIT LAMA', '', ''),
(216, 'W 0454', 'WIWIN', 'P', '2017-05-10', '2018-01-01', '85266456987', '', '', 'KEBUN KOPI', '', ''),
(217, 'S  1590', 'SUMARTONO', 'L', '2017-05-10', '2018-01-01', '81274921190', '', '', 'SEBAPO', '', ''),
(218, 'F 0607', 'FENTI OKTARIANI', 'P', '2017-05-10', '2018-01-01', '82281354009', 'AKBID BUDI MULIA', '', 'SIMPANG RIMBO', '', ''),
(219, 'M 1146', 'MIDA', 'P', '2017-05-10', '2018-01-01', '82185926000', '', '', 'SIMPANG PULAI', '', ''),
(220, 'A 1528', 'ANA ', 'P', '2017-05-10', '1988-08-31', '82307608335', 'IRT', '', 'SELINCAH', '', ''),
(221, 'A 1340', 'ANI', 'P', '2017-05-10', '2018-01-01', '85266320304', '', '', 'RAJAWALI', '', ''),
(222, 'L 0927', 'LIA', 'P', '2017-05-11', '2018-01-01', '85366588111', '', '', 'PAL 5', '', ''),
(223, 'I 0724', 'IPAH SARIFAH', 'P', '2017-05-11', '2018-01-01', '85279226564', '', '', 'UNGGUL SAKTI', '', ''),
(224, 'S 1606', 'SRI AYU LESTARI', 'P', '2017-05-11', '2018-01-01', '85896129199', '', '', 'KOTA BARU', '', ''),
(225, 'A 0870', 'AYU', 'P', '2017-05-11', '2018-01-01', '85288406877', '', '', 'KOMPLEK BTN', '', ''),
(226, 'H 0438', 'HABIBAH', 'P', '2017-05-11', '1982-04-24', '85366924063', '', '', 'TUNGKAL', '', ''),
(227, 'N 0740', 'NURHAYATI', 'P', '2017-05-11', '2018-01-01', '85269876623', '', '', 'TALANG BANJAR', '', ''),
(228, 'Y 0747', 'YAYA', 'P', '2017-05-13', '2018-01-01', '85266770604', '', '', 'THEHOK', '', ''),
(229, 'N 1221', 'NONING', 'P', '2017-05-13', '2018-01-01', '8127339019', '', '', 'MESTONG', '', ''),
(230, 'H 0487', 'HARYANTINI', 'P', '2017-05-16', '2018-01-01', '82181543062', '', '', 'PAL MERAH', '', ''),
(231, 'T 0386', 'TINA', 'P', '2017-05-13', '2018-01-01', '81274811788', '', '', 'KEBUN HANDIL', '', ''),
(232, 'E 1178', 'ENII', 'P', '2017-05-13', '2018-01-01', '82373848852', '', '', 'SIPIN', '', ''),
(233, 'M 1322', 'MELANI', 'P', '2017-05-13', '1994-08-01', '82279080514', 'MAHASISWI UNJA', '', 'MUARA SABAK', '', ''),
(234, 'E 1119', 'HELNI', 'P', '2017-05-13', '2018-01-01', '82280427456', '', '', 'SUNGAI BAHAR', '', ''),
(235, 'J 0160', 'JUNI NOVITA', 'P', '2017-05-13', '2018-01-01', '89614984064', '', '', 'PERUMNAS AUDURI', '', ''),
(236, 'S 1414', 'SAMSIDAH', 'P', '2017-05-13', '2018-01-01', '82182729023', '', '', 'TELANAI', '', ''),
(237, 'M 1037', 'MELATI SUKMA', 'P', '2017-05-13', '1997-05-23', '82375544461', '', '', 'AUDURI', '', ''),
(238, 'Y 0973', 'YANTI', 'P', '2017-05-13', '1985-04-27', '85357676467', '', '', 'SIMPANG KAWAT', '', ''),
(239, 'S 0774', 'SRI SUMIATI', 'P', '2017-05-13', '2018-01-01', '81994441212', '', '', 'TALANG BANJAR', '', ''),
(240, 'C 0020', 'CACA', 'P', '2017-05-13', '2018-01-01', '8576429094', '', '', 'PERSIJAM', '', ''),
(241, 'A 1529', 'ALFIAN', 'P', '2017-05-13', '1977-10-08', '81339827551', 'TNI', '', 'KASANG PUDAK', '', ''),
(242, 'Y 0967', 'YUNI', 'P', '2017-05-13', '2018-01-01', '85170333473', '', '', 'KASANG', '', ''),
(243, 'R 1337', 'RISKA', 'P', '2017-05-16', '2018-01-01', '89614470749', '', '', 'LEBAK BANDUNG', '', ''),
(244, 'B 0168', 'ISA BELLA', 'P', '2017-05-13', '1989-09-17', '85266129633', 'BUMD', '', 'MAYANG', '', ''),
(245, 'W 0238', 'WAWA', 'P', '2017-05-13', '2018-01-01', '81272931194', '', '', '', '', ''),
(246, 'A 1402', 'ANGELINA', 'P', '2017-05-13', '2018-01-01', '8127418210', '', '', 'PASAR', '', ''),
(247, 'K 0254', 'KRISTINA', 'P', '2017-05-13', '2018-01-01', '85262052141', '', '', 'PAL 6', '', ''),
(248, 'E 1070', 'ERNI', 'P', '2017-05-13', '2018-01-01', '8127361314', '', '', 'KUMPE', '', ''),
(249, 'F 0608', 'FITRI HAMELINDA', 'P', '2017-05-13', '1992-03-30', '81271541742', 'BANK', '', 'BERINGIN', '', ''),
(250, 'Y 0325', 'YANTI', 'P', '2017-05-13', '2018-01-01', '85266010867', '', '', 'TANJUNG PINANG', '', ''),
(251, 'S 1611', 'SARAS', 'P', '2017-05-13', '2018-01-01', '85268207706', '', '', 'TALANG BAKUNG', '', ''),
(252, 'W 0463', 'WASA HARYANTI', 'P', '2017-05-13', '1997-11-11', '85322483307', 'MAHASISWI AKBID BUNDA', '', 'TALANG BAKUNG', '', ''),
(253, 'Y 0963', 'YULI RATNASARI', 'P', '2017-05-13', '2018-01-01', '81278633356', '', '', 'TALANG BAKUNG', '', ''),
(254, 'S 1603', 'SUSI HERMAWATI', 'P', '2017-05-13', '2018-01-01', '82186358246', '', '', 'TALANG BAKUNG', '', ''),
(255, 'A 1522', 'ASTONI GUNAWAN', 'L', '2017-05-13', '2018-01-01', '81364194300', '', '', 'TELANAI PURA', '', ''),
(256, 'R 1416', 'RATU MAS ASIA', 'P', '2017-05-13', '2018-01-01', '81366745023', '', '', 'SEBERANG', '', ''),
(257, 'M 1323', 'MITA', 'P', '2017-05-16', '1999-03-07', '81368654459', 'PELAJAR', '', 'BANGKO', '', ''),
(258, 'H 0508', 'HARPIYATI', 'P', '2017-05-16', '2018-01-01', '85357147287', '', '', 'SELINCAH', '', ''),
(259, 'A 0401', 'ADE', 'P', '2017-05-16', '2018-01-01', '8127407553', '', '', 'SIMPANG RIMBO', '', ''),
(260, 'T 0551', 'TAMARA', 'P', '2017-05-16', '2000-07-22', '82281793915', '', '', 'TANJUNG SARI', '', ''),
(261, 'G 0105', 'GITA OKTARIANI', 'P', '2017-05-16', '1996-10-19', '82178656461', '', '', 'KENALI ATAS', '', ''),
(262, 'W 0464', 'WULAN', 'P', '2017-05-16', '2018-01-01', '85266689020', '', '', 'CEMPAKA', '', ''),
(263, 'N 1247', 'NURUL', 'P', '2017-05-16', '2018-01-01', '89676728653', '', '', 'TELANAI', '', ''),
(264, 'M 1310', 'MELZA', 'P', '2017-05-16', '2018-01-01', '82312022285', '', '', 'SELINCAH', '', ''),
(265, 'H 0558', 'HAWANI', 'P', '2017-05-16', '2018-01-01', '8127449849', '', '', 'THEHOK', '', ''),
(266, 'A 1474', 'ASIH', 'P', '2017-05-17', '2018-01-01', '85379575791', '', '', 'TALANG BAKUNG', '', ''),
(267, 'L 0937', 'LINA', 'P', '2017-05-17', '2018-01-01', '82281366797', '', '', 'SIJENJANG', '', ''),
(268, 'A 1468', 'ASNIMAR', 'P', '2017-05-17', '2018-01-01', '82186563031', '', '', 'SIMPANG RIMBO', '', ''),
(269, 'S 0003', 'SILVIA DWI RANTY', 'P', '2017-05-17', '1999-09-18', '82280879006', '', '', 'SIMPANG RIMBO', '', ''),
(270, 'L 0948', 'LIA', '', '2017-05-17', '2018-01-01', '82278643411', '', '', 'KUMPE', '', ''),
(271, 'J 0243', 'JULIANI', 'P', '2017-05-17', '2018-01-01', '85382672527', '', '', 'SIPIN', '', ''),
(272, 'M 1324', 'MASRIAH', 'P', '2017-05-17', '1970-06-13', '85377174545', '', '', 'TUNGKAL', '', ''),
(273, 'H 0550', 'HALIMAH', 'P', '2017-05-17', '2018-01-01', '85266996237', '', '', 'BAYUNG', '', ''),
(274, 'D 1046', 'DANIATI', 'P', '2017-05-17', '1981-04-27', '85267853770', '', '', 'JELUTUNG', '', ''),
(275, 'D 1218', 'DIAN WAHYUNI', 'P', '2017-05-17', '2018-01-01', '82180189991', '', '', 'SIMPANG AHOK', '', ''),
(276, 'F 0612', 'FATMA DEWI', 'P', '2017-05-17', '1975-01-13', '85269870090', '', '', 'THEHOK', '', ''),
(277, 'V 0170', 'VENNY', '', '2017-05-17', '2018-01-01', '85279678191', '', '', 'TANJUNG PINANG', '', ''),
(278, 'M 1154', 'MEI', 'P', '2017-05-18', '2018-01-01', '81367790135', '', '', 'H.KAMIL', '', ''),
(279, 'N 1231', 'NURBAITI', 'P', '2017-05-18', '2018-01-01', '85266136384', '', '', 'PAGAR DRUM', '', ''),
(280, 'R 1572', 'RINA', 'P', '2017-05-18', '2018-01-01', '81273870517', '', '', 'BULIAN', '', ''),
(281, 'Y 0959', 'YENI', 'P', '2017-05-18', '2018-01-01', '85363486268', '', '', 'KASANG', '', ''),
(282, 'F 0479', 'FATIAH', 'P', '2017-05-18', '2018-01-01', '85369220565', '', '', 'SEBRANG', '', ''),
(283, 'I 0731', 'INDRI', 'P', '2017-05-18', '2018-01-01', '85288760505', '', '', 'KASANG JAYA', '', ''),
(284, 'S 1627', 'SHINTA ', 'P', '2017-05-18', '1985-04-28', '85266666000', '', '', 'PASAR BARU', '', ''),
(285, 'M 1241', 'MAIDINA G', 'P', '2017-05-19', '2018-01-01', '82278171798', '', '', 'BULURAN', '', ''),
(286, 'R 1484', 'REZA PAHLEVI', 'L', '2017-05-19', '2018-01-01', '82378467374', '', '', 'TALANG BANJAR', '', ''),
(287, 'F 0180', 'FAUZIA', 'P', '2017-05-19', '2018-01-01', '85263992019', '', '', 'SIMPANG RIMBO', '', ''),
(288, 'S 1280', 'SUMARNI', 'P', '2017-05-19', '1982-06-25', '85379457682', '', '', 'BOLONG', '', ''),
(289, 'H 0518', 'HASNAH', 'P', '2017-05-19', '2018-01-01', '82377904394', '', '', 'PAL 16', '', ''),
(290, 'M 0963', 'MARINA', 'P', '2017-05-19', '2018-01-01', '81277854900', 'TALANG BAKUNG', '', '', '', ''),
(291, 'J 0161', 'JELY', 'P', '2017-05-20', '2018-01-01', '81274212181', '', '', 'KONI', '', ''),
(292, 'M 0687', 'MERLISA ISSE PS', 'P', '2017-05-20', '2018-01-01', '85210463152', '', '', 'NUSA INDAH 3', '', ''),
(293, 'F 0610', 'FELICIA', 'P', '2017-05-20', '1995-02-27', '81296074345', '', '', 'JELUTUNG', '', ''),
(294, 'R 1631', 'RATIH', 'P', '2017-05-20', '1982-10-19', '85838256141', '', '', 'MAYANG', '', ''),
(295, 'D 0823', 'DESMA', 'P', '2017-05-20', '2018-01-01', '85266611932', '', '', 'TELANAI PURA', '', ''),
(296, 'N 0754', 'NIA', 'P', '2017-05-20', '2018-01-01', '899244588', '', '', 'TELANAI PURA', '', ''),
(297, 'S 1540', 'SARIMAH', 'P', '2017-05-21', '2018-01-01', '85357187339', '', '', 'KUMPEH', '', ''),
(298, 'T 0552', 'TUGIYEM', 'P', '2017-05-21', '2018-01-01', '85266263419', '', '', 'SELINCAH', '', ''),
(299, 'E 1118', 'ELVINA', 'P', '2017-05-21', '2018-01-01', '82306333737', '', '', 'SAROLANGUN', '', ''),
(300, 'W 0443', 'WINDA', 'P', '2017-05-21', '2018-01-01', '82183819706', '', '', 'MENDALO', '', ''),
(301, 'E 1200', 'ESIH', 'P', '2017-05-21', '1971-12-05', '85321124040', 'SWASTA', '', 'NIPAH PANJANG', '', ''),
(302, 'D 1233', 'DARMI', 'P', '2017-05-21', '1975-01-21', '82299366502', '', '', 'MERANGIN', '', ''),
(303, 'R 1632', 'RIA', 'P', '2017-05-21', '1989-06-22', '85266781764', 'WIRASWASTA', '', 'BAJUBANG', '', ''),
(304, 'V 0197', 'VAVAL', 'P', '2017-05-21', '1993-12-13', '82307780007', 'MAHASISWA UNJA', '', 'TELANAI PURA', '', ''),
(305, 'F 0595', 'FANI', 'P', '2017-05-21', '2018-01-01', '8163285936', '', '', 'JALAN GAJAH MADA', '', ''),
(306, 'B0127', 'BOBY', 'P', '2017-05-21', '2018-01-01', '85266511194', '', '', 'KOTA BARU', '', ''),
(307, 'G 0127', 'GANDI', 'L', '2017-05-21', '2018-01-01', '8526651194', '', '', 'JERAMBAH BOLONG', '', ''),
(308, 'I 0573', 'IMA', 'P', '2017-05-21', '2018-01-01', '89649215003', '', '', 'MAYANG', '', ''),
(309, 'S 1607', 'SUKA TINI', 'P', '2017-05-21', '1975-04-27', '82372635226', 'WIRASWATA', '', 'TEBO', '', ''),
(310, 'N 1266', 'NOVIA SUSDIANTI', 'P', '2017-05-21', '1984-11-18', '82378433817', 'PNS', '', 'SAROLANGUN', '', ''),
(311, 'S 1628', 'SUSNUR IQBAL ARIFIN', 'L', '2017-05-21', '1984-05-27', '85268859060', 'POLRI', '', 'SAROLANGUN', '', ''),
(312, 'S 1610', 'SELFI', 'P', '2017-05-21', '2018-01-01', '85383191211', '', '', 'THEHOK', '', ''),
(313, 'A 1516', 'ALYA IQLIMA', 'P', '2017-05-21', '2018-01-01', '85383191211', '', '', 'THEHOK', '', ''),
(314, 'G 0103', 'GLORIA', 'P', '2017-05-21', '2018-01-01', '82278171123', '', '', 'SIMPANG AHOK', '', ''),
(315, 'L 0910', 'LINA', '', '2017-05-21', '2018-01-01', '82374505890', '', '', 'MERLUNG', '', ''),
(316, 'T 0430', 'TRI YURNI AMALIA', 'P', '2017-05-21', '2018-01-01', '081278954953.', '', '', 'MUARA BULIAN', '', ''),
(317, 'D 0988', 'DESI', 'P', '2017-05-21', '1982-12-14', '85266482012', '', '', 'SABAK', '', ''),
(318, 'F 0038', 'FAUZIA', 'P', '2017-05-23', '2018-01-01', '8127385505', '', '', 'BANGKO', '', ''),
(319, 'L 0921', 'LINUS SUSANTI', 'P', '2017-05-23', '2018-01-01', '85211988902', '', '', 'PAKUAN BARU', '', ''),
(320, 'R 1596', 'REHAN', 'L', '2017-05-24', '2018-01-01', '85366302007', '', '', 'TALANG BANJAR', '', ''),
(321, 'A 0001', 'AULIA', 'P', '2017-05-24', '2018-01-01', '85366302007', '', '', 'TALANG BANJAR', '', ''),
(322, 'M 1272', 'MERY AYU AGUSTIN', 'P', '2017-05-24', '2018-01-01', '81217793260', '', '', 'SELINCAH', '', ''),
(323, 'S 1629', 'STEPANI', 'P', '2017-05-24', '2018-01-01', '82175798550', '', '', 'TANJUNG LUMUT', '', ''),
(324, 'L 0930', 'LENI', 'P', '2017-05-24', '2018-01-01', '81366404149', '', '', 'KENALI ASAM BAWAH', '', ''),
(325, 'H 0552', 'HALIMAH', 'P', '2017-05-24', '2018-01-01', '82352694780', '', '', 'EKAJAYA', '', ''),
(326, 'E 1045', 'ELMI', 'P', '2017-05-24', '2018-01-01', '81279060809', '', '', 'TALANG BAKUNG', '', ''),
(327, 'I 0797', 'IRSAM', 'P', '2017-05-24', '2018-01-01', '8526617788', '', '', 'MUARA SABAK', '', ''),
(328, 'F 0555', 'FRANSISKA', 'P', '2017-05-24', '2018-01-01', '85378676776', '', '', 'LINGKAR SELATAN', '', ''),
(329, 'H 0516', 'HOSIAH', 'P', '2017-05-24', '2018-01-01', '82380438555', '', '', 'PASAR ANGSO DUO', '', ''),
(330, 'R 1604', 'RUSLIANA', 'P', '2017-05-24', '2018-01-01', '85379380622', '', '', 'EKA JAYA', '', ''),
(331, 'P 0319', 'PAHALA SIREGAR', 'P', '2017-05-24', '2018-01-01', '82176990100', '', '', 'KENALI', '', ''),
(332, 'L 0949', 'LINA', 'P', '2017-05-24', '1987-08-19', '85273007919', 'SWASTA', '', 'SUNGAI BAHAR', '', ''),
(333, 'E 1201', 'EMA', 'P', '2017-05-24', '2063-04-12', '85266380781', 'PNS', '', 'TALANG BAKUNG', '', ''),
(334, 'D 1223', 'DEWI', 'P', '2017-05-24', '2018-01-01', '85327144433', '', '', 'KASANG PUDAK', '', ''),
(335, 'H 0010', 'HELEN', 'P', '2017-05-24', '2018-01-01', '81366615700', '', '', 'TALANG BANJAR', '', ''),
(336, 'N 1219', 'NANA', 'P', '2017-05-25', '2018-01-01', '82178519807', '', '', 'ASTON VILLA', '', ''),
(337, 'S 0530', 'SONDANG', 'P', '2017-05-25', '2018-01-01', '85366939668', '', '', 'MENDALO', '', ''),
(338, 'L 0028', 'LINDA', 'P', '2017-05-25', '2018-01-01', '85368744168', '', '', 'SELINCAH', '', ''),
(339, 'S 1257', 'SALLI TAN', 'P', '2017-05-26', '2018-01-01', '', '', '', '', '', ''),
(340, 'A 0261', 'AISYAH', 'P', '2017-05-26', '2018-01-01', '85266292922', '', '', 'STM ATAS', '', ''),
(341, 'I 0714', 'ICHA', 'P', '2017-05-26', '2018-01-01', '85379496976', '', '', 'TELANAI PURA', '', ''),
(342, 'S 1618', 'SAODAH', 'P', '2017-05-26', '2018-01-01', '81369549948', '', '', 'KONI', '', ''),
(343, 'R 1633', 'RENI', 'P', '2017-05-26', '1986-10-16', '81274966139', '', '', 'MUARA BULIAN', '', ''),
(344, 'S 1630', 'SITI NURAINI', 'P', '2017-05-26', '1981-03-01', '85268197161', '', '', 'SABAK', '', ''),
(345, 'S 1631', 'SUPRIHATI', 'P', '2017-05-26', '1985-07-09', '82283028310', 'IRT', '', 'SABAK', '', ''),
(346, 'C 0176', 'CEVIN CHENG', 'P', '2017-05-26', '2018-01-01', '81220822475', '', '', 'PASAR BARU', '', ''),
(347, 'N 1062', 'NURHAYATI', 'P', '2017-05-26', '2018-01-01', '82373572070', '', '', 'KENALI', '', ''),
(348, 'E 1140', 'EKA', 'P', '2017-05-26', '2018-01-01', '85257003470', '', '', 'KENALI BAWAH', '', ''),
(349, 'Y 0303', 'YANTI', 'P', '2017-05-26', '2018-01-01', '82181388451', '', '', 'MUARA BULIAN', '', ''),
(350, 'Z 0108', 'ZUBAIDAH', 'P', '2017-05-26', '2018-01-01', '85266846897', '', '', 'PAKUAN BARU', '', ''),
(351, 'W 0465', 'WEIYANA', 'P', '2017-05-26', '2002-11-23', '81367790135', '', '', 'H.KAMIL', '', ''),
(352, 'P 0181', 'VITA', 'P', '2017-05-26', '2018-01-01', '85266482254', '', '', 'MUARO BULIAN', '', ''),
(353, 'R 1051', 'RATNA', 'P', '2017-05-26', '2018-01-01', '85263599753', '', '', 'PENYENGAT RENDAH', '', ''),
(354, 'N 0903', 'NURHAYATI', 'P', '2017-05-26', '2018-01-01', '85367386789', '', '', 'SEBRANG', '', ''),
(355, 'I 0674', 'ITA', 'P', '2017-05-26', '2018-01-01', '85269190852', '', '', 'TEBO HULU', '', ''),
(356, 'F 0613', 'FADILATUL HUSNA', 'P', '2017-05-26', '1994-05-24', '82282868272', '', '', 'SIJENJANG', '', ''),
(357, 'I 0670', 'ISTI', 'P', '2017-05-26', '1995-07-10', '8986421934', '', '', 'TELANAI', '', ''),
(358, 'E 1005', 'EMIATI', 'P', '2017-05-28', '2018-01-01', '85266350570', '', '', 'KASANG', '', ''),
(359, 'L 0950', 'LENA', 'P', '2017-05-28', '1976-07-26', '8127489190', 'PNS', '', 'KASANG', '', ''),
(360, 'D 0442', 'DEWI', 'P', '2017-05-28', '2018-01-01', '81273438278', '', '', 'TALANG BAKUNG', '', ''),
(361, 'U 0247', 'UMI', 'P', '2017-06-06', '1991-01-11', '82377404191', 'SWASTA', '', 'KASANG PUDAK', '', ''),
(362, 'M 1294', 'MARYANTI', 'P', '2017-05-28', '2018-01-01', '81366637916', '', '', 'PAL MERAH LAMA', '', ''),
(363, 'I 0013', 'INDA', 'P', '2017-05-28', '2018-01-01', '81919168009', '', '', 'MAYANG', '', ''),
(364, 'E 1136', 'ELA', 'P', '2017-05-28', '2018-01-01', '82375151541', '', '', 'KOTA BARU', '', ''),
(365, 'L 0729', 'LINDA CANDRA', 'P', '2017-05-28', '1977-03-26', '82371737273', '', '', 'KASANG', '', ''),
(366, 'R 1634', 'ROSITA', 'P', '2017-05-28', '1998-05-24', '82331914669', 'PORLI', '', 'PAL MERAH', '', ''),
(367, 'E 0877', 'EVI', 'P', '2017-05-28', '2069-02-04', '81272660048', '', '', 'PAKUAN BARU', '', ''),
(368, 'W 0429', 'WINDA', 'P', '2017-05-28', '1994-10-14', '82281179072', 'SWASTA', '', 'HANDIL', '', ''),
(369, 'R 1210', 'RAFITA', 'P', '2017-05-28', '1994-05-29', '85381199723', 'MAHASISWI PRIMA', '', 'KEBUN HANDIL', '', ''),
(370, 'L 0951', 'LINDA', 'P', '2017-05-30', '1970-11-11', '82177623836', '', '', 'KASANG LUAR', '', ''),
(371, 'L 0876', 'LINDA', 'P', '2017-05-30', '2018-01-01', '85266755810', '', '', 'SIMPANG RIMBO', '', ''),
(372, 'T 0210', 'TURSIATI', 'P', '2017-05-30', '2018-01-01', '85266002594', '', '', 'TALANG BAKUNG', '', ''),
(373, 'D 1191', 'DESI', 'P', '2017-05-30', '2018-01-01', '82177305656', '', '', 'SELINCAH', '', ''),
(374, 'R 1635', 'RANI HERMALIAWATI ', 'P', '2017-05-31', '2018-01-01', '82178656461', '', '', 'KENALI ASAM ATAS ', '', ''),
(375, 'K 0275', 'KIAUTCU', 'P', '2017-05-31', '2018-01-01', '85268113800', '', '', 'SIMPANG RIMBO', '', ''),
(376, 'G 0102', 'GRASELLA', 'P', '2017-05-31', '2018-01-01', '82269029400', '', '', 'SIMPANG RIMBO\\', '', ''),
(377, 'M 1314', 'MARDIANA', 'P', '2017-05-31', '2018-01-01', '81373551037', '', '', 'SIMPANG RIMBO', '', ''),
(378, 'F 0589', 'FITRIANI', 'P', '2017-05-31', '2018-01-01', '82372613816', '', '', 'ARIZONA', '', ''),
(379, 'M 1177', 'MARTA', 'P', '2017-05-31', '2018-01-01', '82380756754', '', '', 'PAL MERAH', '', ''),
(380, 'S 1556', 'SARI', 'P', '2017-05-31', '2018-01-01', '85379099943', '', '', 'SUNGAI KAMBANG', '', ''),
(381, 'R 1517', 'RATIH', 'P', '2017-05-31', '2018-01-01', '82377688355', '', '', 'TALANG BAKUNG', '', ''),
(382, 'A 0148', 'ASMAULINA POHAN', 'P', '2017-05-31', '2018-01-01', '85266686658', '', '', 'KOTA BARU', '', ''),
(383, 'K 0277', 'KAROLINA', 'P', '2017-05-31', '1977-10-14', '81366612853', 'GURU', '', 'EKA JAYA', '', ''),
(384, 'A 1531', 'ANANDA', 'P', '2017-05-31', '2001-07-09', '85366410168', 'PELAJAR', '', 'SELINCAH', '', ''),
(385, 'T 0497', 'TITIN', 'P', '2017-06-02', '2018-01-01', '85268572380', '', '', 'SELINCAH', '', ''),
(386, 'I 0677', 'INA NURVIANTI', 'P', '2017-06-02', '1995-07-06', '87793044700', '', '', 'MENDALO', '', ''),
(387, 'A 1508', 'ASIH', 'P', '2017-06-02', '2018-01-01', '85266888010', '', '', 'SELINCAH', '', ''),
(388, 'M 1329', 'MERI MARWATI', 'P', '2017-06-02', '1974-03-04', '85273110888', 'IRT', '', 'TAMBAK SARI', '', ''),
(389, 'B 0121', 'BONA', 'P', '2017-06-02', '2018-01-01', '81994704805', '', '', 'TELANAI', '', ''),
(390, 'A 0072', 'Dr. ANDI PADA', 'P', '2017-06-02', '2062-03-18', '812740358', '', '', '', '', ''),
(391, 'M 1327', 'MERIATI', 'P', '2017-06-02', '2058-07-16', '82281584317', '', '', 'PASAR BARU', '', ''),
(392, 'R 1602', 'RISKA WILARA', 'P', '2017-06-02', '2018-01-01', '81372720618', '', '', 'SIMPANG RIMBO', '', ''),
(393, 'N 1268', 'NURFANI', 'P', '2017-06-02', '1998-05-17', '89532424686', 'SWASTA', '', 'JERAMBAH BOLONG', '', ''),
(394, 'M 1328', 'MARDALENA', 'P', '2017-06-02', '2062-03-10', '82383723340', '', '', 'BUDIMAN', '', ''),
(395, 'D 1237', 'DESI SETIAWATI', 'P', '2017-06-02', '1998-03-10', '82180333041', '', '', 'TALANG BAKUNG', '', ''),
(396, 'A 1532', 'AFFANTI', 'P', '2017-06-02', '1974-04-14', '85357213551', '', '', 'KASANG PUDAK', '', ''),
(397, 'S 1632', 'SULASTRI', 'P', '2017-06-02', '1970-09-03', '82176666685', '', '', 'KOTA BARU', '', ''),
(398, 'R 1597', 'RISKA', 'P', '2017-06-03', '2018-01-01', '85368285725', '', '', 'BRONI', '', ''),
(400, 'L 0886', 'LOKA', 'P', '2017-06-03', '2018-01-01', '85265667949', '', '', 'THEHOK', '', ''),
(401, 'N 1094', 'NOVI', 'P', '2017-06-03', '2018-01-01', '89608100058', '', '', 'KONI', '', ''),
(402, 'P 0323', 'PUTRI MELVINA', 'P', '2017-06-03', '1995-05-04', '82143176008', '', '', 'SOEKARNO HATTA', '', ''),
(403, 'N 0485', 'NILAWATI', 'P', '2017-06-03', '2018-01-01', '85266727711', '', '', 'SIMPANG PUNCAK', '', ''),
(404, 'I 0694', 'IDA', 'P', '2017-06-03', '2018-01-01', '81279405957', '', '', 'MENDALO', '', ''),
(405, 'W 0291', 'WINDA', 'P', '2017-06-03', '2018-01-01', '8992428447', '', '', 'PASIR PUTIH', '', ''),
(406, 'D 1039', 'DINA', 'P', '2017-06-03', '1982-07-13', '85378404844', '', '', 'AIR HITAM', '', ''),
(407, 'N 1139', 'NADIA', 'P', '2017-06-03', '2018-01-01', '8127430744', '', '', 'KEBUN KOPI', '', ''),
(408, 'S 1634', 'SELVI', 'P', '2017-06-06', '1993-09-29', '82285343129', 'SWASTA', '', 'PUDAK', '', ''),
(409, 'S 1633', 'SUMIATI', 'P', '2017-06-06', '2018-01-01', '', '', '', '', '', ''),
(410, 'T 0536', 'TARI', 'P', '2017-06-06', '2018-01-01', '82377989877', '', '', 'SELINCAH', '', ''),
(411, 'V 0210', 'VENI', 'P', '2017-06-06', '2018-01-01', '81367136913', '', '', 'ASRAMA POLISI', '', ''),
(412, 'M 1074', 'META', 'P', '2017-06-06', '1976-05-14', '81210322936', '', '', 'EKAJAYA', '', ''),
(413, 'M 1330', 'MARIA ULFA', 'P', '2017-06-06', '1997-06-04', '81373379933', '', '', 'IBRAHIM', '', ''),
(414, 'R 0724', 'RASIFA', 'P', '2017-06-06', '1985-09-04', '82372523220', 'PNS', '', 'BRONI', '', ''),
(415, 'R 1612', 'RIO ANTONI', 'L', '2017-06-06', '2018-01-01', '85266198650', '', '', 'PEMANCAR TVRI', '', ''),
(416, 'N 1199', 'NINGSIH', 'P', '2017-06-06', '2018-01-01', '82373827737', '', '', 'TALANG DUKU', '', ''),
(417, 'A 1533', 'ANJONG', 'P', '2017-06-06', '2018-01-01', '85266997025', '', '', 'TELANAI', '', ''),
(418, 'D 0600', 'DEWI', 'P', '2017-06-06', '2018-01-01', '8127491361', '', '', 'KASANG', '', ''),
(419, 'R 1534', 'RATIH', 'P', '2017-06-06', '2018-01-01', '82186261126', '', '', 'JELUTUNG', '', ''),
(420, 'K 0206', 'KARTI', 'P', '2017-06-06', '1981-06-10', '81339827551', '', '', 'TALANG BANJAR', '', ''),
(421, 'M 1331', 'MUSINAH', 'P', '2017-06-06', '1970-04-05', '85267084157', 'IRT', '', 'SIMPANG KARYA', '', ''),
(422, 'S 1503', 'SANTI', 'P', '2017-06-06', '2018-01-01', '85266938419', '', '', 'PERSIJAM', '', ''),
(423, 'S 1635', 'SUHARTINI', 'P', '2017-06-06', '2068-01-22', '85366031327', 'PNS', '', 'KUMPEH', '', ''),
(424, 'N 1110', 'NOVITA', 'P', '2017-06-06', '2018-01-01', '', '', '', 'DEPAM MERANTI', '', ''),
(425, 'N 1241', 'NABILA', 'P', '2017-06-06', '2018-01-01', '82284147364', '', '', 'JELUTUNG', '', ''),
(426, 'R 1606', 'ROTUA', 'P', '2017-06-06', '2018-01-01', '81271705747', '', '', 'TELANAI', '', ''),
(427, 'S 1431', 'SANDRA', 'P', '2017-06-06', '2018-01-01', '81369702654', '', '', 'TALANG DUKU', '', ''),
(428, 'H 0323', 'HETY', 'P', '2017-06-06', '2018-01-01', '85789003883', '', '', 'KOTA BARU', '', ''),
(429, 'I 0833', 'IRAWATI', 'P', '2017-06-06', '2018-01-01', '85366409981', 'SWASTA', '', 'PASIR PUTIH', '', ''),
(430, 'D 1225', 'DELLY', 'P', '2017-06-06', '2018-01-01', '8117100525', '', '', 'TALANG BAKUNG', '', ''),
(431, 'R 1481', 'RIA', 'P', '2017-06-06', '2018-01-01', '85273568233', '', '', 'TALANG BANJAR', '', ''),
(432, 'Y 0976', 'YENI', 'P', '2017-06-06', '1984-03-10', '82368625137', '', '', 'TALANG BAKUNG', '', ''),
(433, 'D 1238', 'DIMAS', 'L', '2017-06-06', '2001-07-01', '85266073153', '', '', 'TANGKIT LAMA', '', ''),
(434, 'Y 0371', 'YUN ARNIDA SARI', 'P', '2017-06-06', '2018-01-01', '81279667575', '', '', 'PAAL MERAH LAMA', '', ''),
(435, 'M 0932', 'MELI', 'P', '2017-06-06', '2018-01-01', '81366632966', '', '', 'JERAMBAH BOLONG', '', ''),
(436, 'E 1059', 'ERMA', 'P', '2017-06-06', '2018-01-01', '8127400159', '', '', 'MAYANG', '', ''),
(437, 'L 0952', 'LINA', 'P', '2017-06-06', '1978-11-23', '', '', '', 'TANJUNG PINANG', '', ''),
(438, 'E 1202', 'E HARTINI', 'P', '2017-06-06', '2063-05-13', '85268448833', 'GURU SWASTA', '', 'THEHOK', '', ''),
(439, 'A 1499', 'AHUN', 'P', '2017-06-06', '2018-01-01', '82177328810', '', '', 'KEBUN HANDIL', '', ''),
(440, 'Y 0951', 'YUNIARTI', 'P', '2017-06-06', '2018-01-01', '82371871177', '', '', 'TUNGKAL', '', ''),
(441, 'A 1273', 'AYEN', 'P', '2017-06-06', '2018-01-01', '81366085981', '', '', 'BAWAH DKT', '', ''),
(442, 'Y 0829', 'YUSNANI', 'P', '2017-06-06', '2018-01-01', '8127305705', '', '', 'NUSA INDAH', '', ''),
(443, 'R 1548', 'RESVI', 'P', '2017-06-06', '2018-01-01', '85210682883', '', '', 'MENDALO', '', ''),
(444, 'S 0079', 'SRIYANTI', 'P', '2017-06-06', '2018-01-01', '81312005151', '', '', 'PALL 5', '', ''),
(445, 'Z 0123', 'ZUL IRFAN', 'L', '2017-07-09', '1970-07-24', '85266324266', 'SWASTA', '', 'JERAMBAH BOLONG', '', ''),
(446, 'D 1154', 'DEVI', 'P', '2017-06-08', '2018-01-01', '85279621676', '', '', 'KASANG', '', ''),
(447, 'H 0561', 'HERAWATI', 'P', '2017-06-08', '1971-05-09', '85266567645', 'IRT', '', 'THEHOK', '', ''),
(448, 'I 0744', 'IDA', 'P', '2017-06-08', '2018-01-01', '81274643199', '', '', 'AUDURI', '', ''),
(449, 'A 1520', 'AMALIA', 'P', '2017-06-08', '2018-01-01', '81272657367', '', '', 'HJ. KAMIL', '', ''),
(450, 'E 0836', 'EFFA. Y', 'P', '2017-06-08', '2018-01-01', '', '', '', 'MAYANG', '', ''),
(451, 'N 1255', 'NONI', 'P', '2017-06-08', '2018-01-01', '82269553330', '', '', 'MAYANG', '', ''),
(452, 'H 0541', 'HERA', 'P', '2017-06-08', '2018-01-01', '85266017333', '', '', 'JL. AMANG KURAT', '', ''),
(453, 'S 0498', 'SITI MAHMUDAH', 'P', '2017-06-08', '2069-08-19', '85268559714', 'PNS GURU', '', 'PAL MERAH', '', ''),
(454, 'R 1614', 'RAMLAH', 'P', '2017-06-08', '2018-01-01', '81539859247', '', '', 'TALANG BAKUNG', '', ''),
(455, 'L 0884', 'LAURA', 'P', '2017-06-08', '2018-01-01', '89608402314', '', '', 'SELINCAH', '', ''),
(456, 'Z 0119', 'ZULFA', 'P', '2017-06-08', '2018-01-01', '85664202015', '', '', 'BERINGIN', '', ''),
(457, 'N 1198', 'NYIMAS SARI DEWI', 'P', '2017-06-08', '2018-01-01', '85382848668', '', '', 'SIPIN', '', ''),
(458, 'N 1269', 'NINA', 'P', '2017-06-08', '1997-04-17', '81273272374', 'WIRASWASTA', '', 'KASANG PUDAK', '', ''),
(459, 'Y 0801', 'YENNI', 'P', '2017-06-08', '2018-01-01', '85382581825', '', '', 'KUMPE', '', ''),
(460, 'V 0225', 'VIVI', 'P', '2017-06-08', '1984-04-29', '85379356588', 'SWASTA', '', 'TALANG BAKUNG', '', ''),
(461, 'I 0834', 'ITIN DESRIATI', 'P', '2017-06-08', '1994-12-02', '82184074867', '', '', 'SELINCAH', '', ''),
(462, 'N 1164', 'NADIA', 'P', '2017-06-08', '2018-01-01', '81277658592', '', '', 'SELINCAH', '', ''),
(463, 'S 1345', 'SUSIANTI HARAHAP', 'P', '2017-06-08', '2018-01-01', '85382144618', '', '', 'KASANG PUDAK', '', ''),
(464, 'P 0087', 'PARTI', 'P', '2017-06-08', '2018-01-01', '85269307783', '', '', 'TANJUNG PINANG', '', ''),
(465, 'P 0322', 'PIPI SAFITRI', 'P', '2017-06-08', '2018-01-01', '82281047246', '', '', 'TALANG BAKUNG', '', ''),
(466, 'L 0895', 'LINDA', 'P', '2017-06-09', '2018-01-01', '82282282770', '', '', 'TALANG BAKUNG', '', ''),
(467, 'V 0090', 'VICHA', 'P', '2017-06-09', '2018-01-01', '85764200492', '', '', 'TALANG BAKUNG', '', ''),
(468, 'S 1609', 'SITI MARHAMA', 'P', '2017-06-09', '2018-01-01', '82180200545', '', '', 'KENALI', '', ''),
(469, 'N 1270', 'NIKI ASTRIA', 'P', '2017-06-10', '1991-09-01', '81919084142', 'SWASTA', '', 'KASANG', '', ''),
(470, 'D 1239', 'DEWI', 'P', '2017-06-10', '1997-06-21', '82374967376', '', '', 'TALANG BAKUNG', '', ''),
(471, 'E 1203', 'ELI', 'P', '2017-06-10', '1989-10-17', '82380950566', 'SEJINJANG', '', 'SWASTA', '', ''),
(472, 'T 0553', 'TRIANA', 'P', '2017-06-10', '1982-10-09', '89688047969', '', '', 'KENALI ASAM ATAS', '', ''),
(473, 'S 1615', 'SHERLY', 'P', '2017-06-10', '2018-01-01', '87887009001', '', '', 'THEHOK', '', '');
INSERT INTO `tbmpasien` (`IdPasien`, `KodePasien`, `NamaPasien`, `JenisKelamin`, `TanggalDaftar`, `TanggaLahir`, `NoTelpon`, `Pekerjaan`, `Email`, `Alamat`, `Catatan`, `NoKartu`) VALUES
(474, 'E 1204', 'ELI', 'P', '2017-06-11', '1984-10-20', '82282157104', 'SWASTA', '', 'EKA JAYA', '', ''),
(475, 'T 0517', 'TIARA', 'P', '2017-06-11', '2018-01-01', '82373553993', '', '', 'SUNGAI SAWANG', '', ''),
(476, 'N 0950', 'NISA', 'P', '2017-06-11', '2018-01-01', '82176215154', '', '', 'KOTA BARU', '', ''),
(477, 'B 0137', 'BOY WIBOWO', 'L', '2017-06-11', '1987-06-24', '81286080999', 'BANK BPR', '', 'PASIR PUTIH', '', ''),
(478, 'G 0067', 'GAYATRI', 'P', '2017-06-11', '1986-09-26', '85266733624', '', '', 'TUNGKAL WKS', '', ''),
(479, 'R 1264', 'RIRIN', 'P', '2017-06-11', '1991-10-04', '81271173345', '', '', 'TALANG BAKUNG', '', ''),
(480, 'F 0614', 'FATMAWATI', 'P', '2017-06-11', '2018-01-01', '853665780', '', '', 'TANJUNG SARI', '', ''),
(481, 'R 1275', 'RASYIDAH', 'P', '2017-06-11', '1978-01-21', '81373566860', '', '', 'JELUTUNG', '', ''),
(482, 'D 1240', 'DINA', 'P', '2017-06-11', '2000-10-06', '81274197763', '', '', 'TANJUNG PINANG', '', ''),
(483, 'A 1535', 'ASRI', 'P', '2017-06-11', '1992-07-02', '82176427731', '', '', 'SELINCAH', '', ''),
(484, 'M 0082', 'MAIMANA', 'P', '2017-06-11', '2018-01-01', '85378627119', '', '', 'MERSAM', '', ''),
(485, 'R 1637', 'REGITA CAHYANI', 'P', '2017-06-11', '2003-02-25', '81366404149', '', '', 'KENALI ASAM BAWAH', '', ''),
(486, 'A 1515', 'ANI', 'P', '2017-06-12', '2018-01-01', '82182200989', '', '', 'MUARA BULIAN', '', ''),
(487, 'D 1205', 'DIANA', 'P', '2017-06-12', '2018-01-01', '82178325691', '', '', 'KOTA BARU', '', ''),
(488, 'P 0321', 'PEBY', 'P', '2017-06-12', '2018-01-01', '82372297193', '', '', 'KASANG PUDAK', '', ''),
(489, 'O 0109', 'OGA SATRIASDA', 'L', '2017-06-12', '1991-05-11', '82298199801', '', '', 'TELANAI', '', ''),
(490, 'M 1215', 'MARLIA', 'P', '2017-06-12', '2018-01-01', '81366517571', '', '', 'TALANG BAKUNG', '', ''),
(491, 'Y 0886', 'YENNI', 'P', '2017-06-12', '2018-01-01', '811749286', '', '', 'SELINCAH', '', ''),
(492, 'D 1126', 'DINI WULANDARI', 'P', '2017-06-12', '2018-01-01', '82185643848', '', '', 'SAROLANGUN', '', ''),
(493, 'E 0784', 'ESI', 'P', '2017-06-12', '2018-01-01', '1,08992E+11', '', '', 'ARIZONA', '', ''),
(494, 'D 0969', 'DINDA', 'P', '2017-06-12', '2018-01-01', '81994671090', '', '', 'ARIZONA', '', ''),
(495, 'H 0444', 'HELEN', 'P', '2017-06-12', '1988-05-05', '85366032890', '', '', 'TELANAI', '', ''),
(496, 'M 0300', 'M SITORUS', 'P', '2017-06-12', '2018-01-01', '85266592703', '', '', 'TELANAI', '', ''),
(497, 'H 0564', 'HJ. ASMARITA', 'P', '2017-06-14', '2018-01-01', '82389594458', 'SWASTA', '', 'TANJAB TIMUR', '', ''),
(498, 'S 1586', 'SRI WIDYATI', 'P', '2017-06-14', '2018-01-01', '85380880019', '', '', 'KASANG', '', ''),
(499, 'H 0309', 'HILDA JASTRI', 'P', '2017-06-14', '2018-01-01', '82184645321', '', '', 'BULIAN', '', ''),
(500, 'N 1271', 'NILAWATI', 'P', '2017-06-14', '1993-05-13', '82312298291', 'SWASTA', '', 'TALANG BANJAR', '', ''),
(501, 'U 0248', 'ULFA', 'P', '2017-06-14', '2018-01-01', '8117442100', '', '', 'TALANG BANJAR', '', ''),
(502, 'E 0705', 'ETI', 'P', '2017-06-15', '2018-01-01', '811743816', '', '', 'TALANG BAKUNG', '', ''),
(503, 'F 0502', 'FITRIE', 'P', '2017-06-15', '2018-01-01', '82282541155', '', '', 'BAKUNG', '', ''),
(504, 'R 0007', 'RITA PURNAMA', 'P', '2017-06-15', '2018-01-01', '81368330639', '', '', 'PAKUAN BARU', '', ''),
(505, 'F 0615', 'FITRI HANDAYANI', 'P', '2017-06-15', '1988-06-15', '81368329699', '', '', 'TANJUNG LUMUT', '', ''),
(506, 'E 1205', 'EKA', 'P', '2017-06-15', '1985-09-05', '81274841413', '', '', 'KOTA BARU', '', ''),
(507, 'A 1537', 'ANGGUN AMELIA', 'P', '2017-06-15', '1999-12-04', '82183965388', 'MAHASISWI UNJA', '', 'KOTA BARU', '', ''),
(508, 'A 1514', 'ADE', 'L', '2017-06-15', '2018-01-01', '8,95338E+11', '', '', 'PAL MERAH', '', ''),
(509, 'D 1242', 'DWI PUSPITA RANI', 'P', '2017-06-15', '1998-09-01', '89634412102', '', '', 'PAL MERAH', '', ''),
(510, 'E 1033', 'EVA', 'P', '2017-06-15', '2018-01-01', '81366577095', '', '', 'TELANAI', '', ''),
(511, 'R 1616', 'RESTY', 'P', '2017-06-15', '2018-01-01', '85365381426', '', '', 'KENALI ASAM ATAS', '', ''),
(512, 'L 0953', 'LINA', 'P', '2017-06-15', '1981-01-20', '85261751788', 'IRT', '', 'PASAR BARU', '', ''),
(513, 'W 0467', 'WITHA', 'P', '2017-06-15', '1984-06-02', '85384596006', '', '', 'BAJUBANG', '', ''),
(514, 'M 1332', 'MAYA', 'P', '2017-06-15', '1991-05-07', '85266412393', '', '', 'KASANG', '', ''),
(515, 'Y 0618', 'YANTI', 'P', '2017-06-15', '2018-01-01', '89608099971', 'BIDAN', '', 'TAKANG BAKUNG', '', ''),
(516, 'M 1334', 'MISCA', 'P', '2017-06-16', '1983-12-12', '82383852522', '', '', 'PASAR ANGSO DUO', '', ''),
(517, 'M 1333', 'MILA', 'P', '2017-06-16', '1990-11-10', '81271228741', '', '', 'PASAR ANGSO DUO', '', ''),
(518, 'M 0386', 'MIRANTI', 'P', '2017-06-16', '1989-05-06', '85378550838', '', '', 'JL. ASOKA RT.14 BELIUNG INDAH. NO 16', '', ''),
(519, 'L 0844', 'LIA', 'P', '2017-06-16', '1996-03-17', '85266466738', 'MAHASISWI', '', 'THEHOK', '', ''),
(520, 'A 0105', 'ANNY HIDAYAT', 'P', '2017-06-16', '1983-01-02', '8117450241', '', '', 'JL.NATUNA RT.20 NO 17 JELUTUNG', '', ''),
(521, 'T 0101', 'TARTI BUDI', 'P', '2017-06-16', '2018-01-01', '74161668', '', '', 'KASTURI', '', ''),
(522, 'R 0333', 'RATNA', 'P', '2017-06-19', '2068-09-10', '81273193235', 'IRT', '', 'SUNGAI GELAM', '', ''),
(523, 'R 1638', 'RENY', 'P', '2017-06-19', '1981-07-15', '81273378000', 'IRT', '', 'PURI MAYANG', '', ''),
(524, 'Y 0956', 'YENI', 'P', '2017-06-19', '2018-01-01', '', '', '', 'MENDALO', '', ''),
(525, 'E 1208', 'ESIKA', 'P', '2017-06-19', '1993-06-20', '85290942682', '', '', 'SABAK', '', ''),
(526, 'D 1244', 'DANI JULITA', 'P', '2017-06-19', '1985-07-15', '81274697757', 'GURU HONOR', '', 'SELINCAH', '', ''),
(527, 'F 0617', 'FAJAR', 'P', '2017-06-19', '1971-06-27', '81325737799', 'SWASTA', '', 'PASIR PUTIH', '', ''),
(528, 'L 0956', 'LIA', 'P', '2017-06-19', '1998-12-28', '8,9561E+11', '', '', 'TALANG BAKUNG', '', ''),
(529, 'S 1412', 'SITI ASIAH', 'P', '2017-06-19', '2018-01-01', '85266002087', '', '', 'RAJAWALI', '', ''),
(530, 'H  0421', 'HANA', 'P', '2017-06-19', '2018-01-01', '8975303368', '', '', 'JELUTUNG', '', ''),
(531, 'P 0001', 'PASKA LINA', 'P', '2017-06-19', '1985-05-25', '85266939366', '', '', 'SAROLANGUN', '', ''),
(532, 'R 1571', 'ROSNANI', 'P', '2017-06-19', '2018-01-01', '81366049006', '', '', 'KOTA BARU', '', ''),
(533, 'M 1335', 'MULDAYATI', '', '2017-06-19', '2017-06-19', '', '', '', 'TELANAI PURA', '', ''),
(534, 'Y 0930', 'YENNI', 'P', '2017-06-19', '2018-01-01', '82383720889', '', '', 'PASIR PUTIH', '', ''),
(535, 'S 1211', 'SUMINA', 'P', '2017-06-19', '2018-01-01', '82175993171', 'PAAL LIMA', '', '', '', ''),
(537, 'L 0955', 'LINA', 'P', '2017-06-19', '1976-08-17', '85238473847', '', '', 'TANJUNG PINANG', '', ''),
(538, 'A 1440', 'ARIYANTO', 'P', '2017-06-19', '2018-01-01', '', '', '', 'TANJUNG PINANG', '', ''),
(539, 'D 1243', 'DELI DAHLIA', 'P', '2017-06-19', '1982-12-20', '85314130578', '', '', 'KUMPE ULU', '', ''),
(540, 'L 0954', 'LINDA AGUS TRIANI', 'P', '2017-06-19', '1981-08-11', '85266522481', '', '', 'PONDOK MEJA', '', ''),
(541, 'F 0616', 'FENIA', 'P', '2017-06-19', '2001-02-18', '82181344144', '', '', 'BANGKO', '', ''),
(543, 'E 1186', 'ERNA WATI', 'P', '2017-06-19', '2018-01-01', '82282149570', '', '', 'BANGKO', '', ''),
(544, 'D 1214', 'DEWITA', 'P', '2017-06-19', '2018-01-01', '82280052909', '', '', 'PAKUAN BARU', '', ''),
(545, 'E 1207', 'ETI SUSILAWATI', 'P', '2017-06-19', '1976-01-01', '81366735210', '', '', 'MENDALO', '', ''),
(546, 'E 1206', 'ELIZABERT', 'P', '2017-06-19', '2062-12-10', '', '', '', 'TELANAI PURA', '', ''),
(547, 'T 0531', 'TANTRI', 'P', '2017-06-19', '2018-01-01', '82182715636', '', '', 'PAL 9', '', ''),
(548, 'A 1321', 'AZKA WIRRA', 'P', '2017-06-19', '2018-01-01', '85208257775', '', '', 'KUMPEH', '', ''),
(549, 'R 1593', 'RATNA', 'P', '2017-06-19', '2018-01-01', '85294022255', '', '', 'BUNGO', '', ''),
(550, 'L 0744', 'LINA NOPLAYANA', 'P', '2017-06-19', '1996-11-08', '82372224633', '', '', 'TALANG BAKUNG', '', ''),
(551, 'L 0923', 'LENI', 'P', '2017-06-19', '2018-01-01', '81366711644', '', '', 'MENDALO', '', ''),
(552, 'D 1125', 'DEWI', 'P', '2017-06-19', '2018-01-01', '85339000027', '', '', 'RCTI', '', ''),
(553, 'A 0754', 'ANA', 'P', '2017-06-19', '2018-01-01', '', '', '', 'PAL MERAH LAMA', '', ''),
(554, 'I 0704', 'INDRI PRATIWI', 'P', '2017-06-19', '2018-01-01', '85278662534', '', '', 'RAJAWALI', '', ''),
(555, 'A 1538', 'ANIS', 'P', '2017-06-19', '1999-12-11', '82373077622', '', '', 'KUMPE', '', ''),
(556, 'L 0040', 'LIE KHIM', 'P', '2017-06-20', '2018-01-01', '8127338876', '', '', 'YUNUS SANIS NO.7', '', ''),
(557, 'N 0780', 'NADIA', 'P', '2017-06-20', '2018-01-01', '85383384597', '', '', 'ASTON VILLA', '', ''),
(558, 'S 0728', 'SISKA', 'P', '2017-06-20', '2018-01-01', '82183317799', '', '', 'KASANG', '', ''),
(559, 'L 0224', 'LIDIYA', 'P', '2017-06-20', '2018-01-01', '85380601610', '', '', 'SELINCAH', '', ''),
(560, 'L 0935', 'LICU', 'P', '2017-06-20', '2018-01-01', '82371446173', '', '', 'TANJUNG SARI', '', ''),
(561, 'D 0435', 'DORA', 'P', '2017-06-20', '2018-01-01', '81366425099', '', '', 'KASANG PUDAK', '', ''),
(562, 'M 0033', 'MEI', 'P', '2017-06-20', '2018-01-01', '81366569370', '', '', 'PERSIJAM', '', ''),
(563, 'T 0135', 'TATI', 'P', '2017-06-21', '2018-01-01', '81366982564', 'DINKES PROV', '', 'TELANAI PURA', '', ''),
(564, 'E 0666', 'EKA', 'P', '2017-06-21', '2018-01-01', '81366583808', '', '', 'SELINCAH', '', ''),
(565, 'R 1639', 'RATU KHODIJAH FHASA', 'P', '2017-06-21', '1997-01-11', '82228184332', 'WIRASWASTA', '', 'TANGKIT', '', ''),
(566, 'R 0121', 'R SITORUS', 'P', '2017-06-21', '2018-01-01', '85266355369', '', '', 'TERMINAL BARU', '', ''),
(567, 'O 0050', 'ONE', 'P', '2017-06-21', '2018-01-01', '85267931975', '', '', 'TALANG BANJAR', '', ''),
(568, 'F 0531', 'FITRI', 'P', '2017-06-21', '2018-01-01', '81273875254', '', '', 'JELUTUNG', '', ''),
(569, 'S 0061', 'SUMIYATI', 'P', '2017-06-28', '2018-01-01', '81278200070', 'KASANG', '', '', '', ''),
(570, 'D 1183', 'DESI', 'P', '2017-06-28', '2018-01-01', '89620297578', 'EKA JAYA', '', '', '', ''),
(571, 'D 1245', 'DASMAWATI', 'P', '2017-06-28', '1983-12-12', '85268904150', 'IRT', '', 'PAKUAN BARU', '', ''),
(572, 'I 0832', 'INDRI', 'P', '2017-06-28', '1990-06-22', '82307000122', '', '', 'KASANG', '', ''),
(573, 'N 1273', 'NURMALA', 'P', '2017-06-28', '1988-02-05', '82373028305', 'HONOR', '', 'SIMPANG DUREN', '', ''),
(574, 'Y 0707', 'YULIANA', 'P', '2017-06-28', '2018-01-01', '81274978990', '', '', 'KUMPEH', '', ''),
(575, 'S 0848', 'SUSIANI', 'P', '2017-06-28', '2018-01-01', '81366288877', '', '', 'BAJURI', '', ''),
(576, 'A 1433', 'ANGGI', 'P', '2017-06-28', '2018-01-01', '85266723277', '', '', 'NUSA INDAH', '', ''),
(577, 'A 1348', 'ANISA', 'P', '2017-06-28', '2018-01-01', '82376606760', '', '', 'MAYANG', '', ''),
(578, 'I 0836', 'IKE', 'P', '2017-06-28', '2018-01-01', '85384116988', 'SWASTA', '', 'EKAJAYA', '', ''),
(579, 'L 0565', 'LINDA SUSANA', 'P', '2017-06-28', '1990-02-04', '81394030430', 'SWASTA', '', 'KUMPE', '', ''),
(580, 'Y 0702', 'YUSHANA SILALANI', 'P', '2017-06-28', '2018-01-01', '82186361444', 'IRT', '', 'JERAMBAH BOLONG', '', ''),
(581, 'I 0837', 'IDA', 'P', '2017-06-28', '1973-07-05', '82181494471', '', '', 'SABAK', '', ''),
(582, 'E 0827', 'ERLINDA', 'P', '2017-06-28', '2018-01-01', '81274161444', '', '', 'THEHOK', '', ''),
(583, 'E 0751', 'ELI', 'P', '2017-06-28', '2018-01-01', '82371431231', '', '', 'SELINCAH', '', ''),
(584, 'E 1172', 'ELI', 'P', '2017-06-28', '2018-01-01', '82371411221', '', '', 'PAYO SELINCAH', '', ''),
(585, 'W 021', 'WINA', 'P', '2017-06-28', '1989-03-10', '85271006013', '', '', 'RAJAWALI', '', ''),
(586, 'S 0855', 'SINTIA', 'P', '2017-06-30', '2018-01-01', '89624398885', '', '', 'SELINCAH', '', ''),
(587, 'E 1209', 'ELI', 'P', '2017-06-30', '1972-11-16', '82374152019', '', '', 'SAROLANGUN', '', ''),
(588, 'L 0890', 'LISA', 'P', '2017-06-30', '2018-01-01', '81368002181', '', '', 'KONI', '', ''),
(589, 'A 1540', 'ARDI', 'P', '2017-06-30', '1994-04-02', '82376632362', '', '', 'PAYO SELINCAH', '', ''),
(590, 'L 0957', 'LILI ARYANI', 'P', '2017-06-30', '1973-05-27', '8117431451', '', '', 'THEHOK SUKAREJO', '', ''),
(591, 'S 1637', 'SALWA AZZAHRA', 'P', '2017-06-30', '2002-03-20', '82279429219', '', '', 'THEHOK', '', ''),
(592, 'A 0476', 'AFIANI', 'P', '2017-06-30', '2018-01-01', '81367730830', '', '', 'SIPIN', '', ''),
(593, 'A 1235', 'ADE', 'P', '2017-06-30', '1996-11-28', '8999649599', '', '', 'PAAL MERAH LAMA', '', ''),
(594, 'L 0878', 'LE GIOK', 'P', '2017-06-30', '2018-01-01', '82372611188', '', '', 'JL.ORANG KAYO HITAM', '', ''),
(595, 'B 0154', 'BEBBY', 'P', '2017-06-30', '2018-01-01', '82374557999', '', '', 'KOBAR', '', ''),
(596, 'R 1640', 'RINI FRANSISCA', 'P', '2017-06-30', '1985-10-22', '82281583302', 'SWASTA', '', 'SIMPANG GADO GADO', '', ''),
(597, 'R 1641', 'RIRI', 'P', '2017-06-30', '1991-05-10', '85320492340', '', '', 'KASANG', '', ''),
(598, 'E 0576', 'ERNITA', 'P', '2017-06-30', '1979-12-30', '85368965611', '', '', 'SINGKUT', '', ''),
(599, 'K 0278', 'KHAYLAN', 'P', '2017-06-30', '2061-05-29', '81279957222', '', '', 'MAYANG', '', ''),
(600, 'I 0011', 'INAR', 'P', '2017-06-30', '2018-01-01', '81274939172', '', '', 'JELUTUNG', '', ''),
(601, 'F 0550', 'FITRI', 'P', '2017-06-30', '2018-01-01', '81382216405', '', '', 'TELANAI PURA', '', ''),
(602, 'E 0277', 'ERLIYANA', 'P', '2017-07-02', '2018-01-01', '82184565878', '', '', 'IBRAHIM', '', ''),
(603, 'A 1493', 'AJIE', 'L', '2017-07-02', '2018-01-01', '89562000882', '', '', 'KOTABARU', '', ''),
(604, 'M 0257', 'MASTINA', 'P', '2017-07-02', '2018-01-01', '85268458972', '', '', 'EKAJAYA', '', ''),
(605, 'N 0405', 'NIRWANA', 'P', '2017-07-02', '2018-01-01', '85377664141', '', '', 'KASANG', '', ''),
(606, 'K 0236', 'KEVIN', 'P', '2017-07-02', '2018-01-01', '85266020004', '', '', 'PASAR', '', ''),
(607, 'A 1384', 'ALDI', 'L', '2017-07-02', '2018-01-01', '85222800525', '', '', 'TALANG BAKUNG', '', ''),
(608, 'L 0776', 'LISA', 'P', '2017-07-02', '2018-01-01', '82183246415', '', '', 'ARIZONA', '', ''),
(609, 'W 0468', 'WINDA', 'P', '2017-07-02', '1989-12-15', '85273443489', 'HONOR', '', 'JELUTUNG', '', ''),
(610, 'R 1610', 'RIKA SEPTIANI', 'P', '2017-07-02', '2018-01-01', '82176458576', '', '', 'BERINGIN', '', ''),
(611, 'Y 0980', 'YENI', 'P', '2017-07-02', '1990-11-23', '82183522868', '', '', 'KASANG PUDAK', '', ''),
(612, 'E 1210', 'ESMARALDA', 'P', '2017-07-02', '1993-10-21', '81994726689', '', '', 'MAYANG', '', ''),
(613, 'R 0114', 'RAFIKA', 'P', '2017-07-04', '2018-01-01', '85764242404', '', '', 'KOTA BARU', '', ''),
(614, 'I 0533', 'IGA', 'P', '2017-07-04', '2018-01-01', '85268832765', '', '', 'SEBRANG', '', ''),
(615, 'S 1501', 'SERLY', 'P', '2017-07-04', '2018-01-01', '85378077474', '', '', 'SELINCAH', '', ''),
(616, 'K 0262', 'KASMAH', 'P', '2017-07-04', '2018-01-01', '81366497624', '', '', 'SUNGAI GELAM', '', ''),
(617, 'N 1197', 'NURLELA', 'P', '2017-07-05', '2018-01-01', '82306114177', '', '', 'SELINCAH', '', ''),
(618, 'E 1211', 'EKA NUR', 'P', '2017-07-05', '1982-04-13', '85367798695', 'IRT', '', 'PAKUAN BARU', '', ''),
(619, 'R 1455', 'RITA', 'P', '2017-07-05', '2018-01-01', '85266600707', '', '', 'KENALI', '', ''),
(620, 'M 0001', 'MUTIARA', 'P', '2017-07-05', '2018-01-01', '85266600707', '', '', 'KENALI', '', ''),
(621, 'T 0555', 'TIA MAHARANI', 'P', '2017-07-05', '1995-10-02', '85838884424', '', '', 'SELINCAH', '', ''),
(622, 'A 0109', 'ANI', 'P', '2017-07-05', '2069-03-31', '85266005090', '', '', 'JELUTUNG', '', ''),
(623, 'Z 0122', 'ZULKIFLI', 'L', '2017-07-05', '1970-12-27', '85266045866', '', '', 'KERINCI', '', ''),
(624, 'H 0565', 'HELBI SAGITA', 'P', '2017-07-05', '1989-09-07', '81366872828', '', '', 'TALANG BAKUNG', '', ''),
(625, 'M 1304', 'MIRA', 'P', '2017-07-06', '2018-01-01', '81272062342', '', '', 'KEBUN KOP[I', '', ''),
(627, 'N 0747', 'NOVIA', 'P', '2017-07-06', '2018-01-01', '81274511108', '', '', 'PASIR PUTIH', '', ''),
(628, 'L 0958', 'LIDIA', 'P', '2017-07-06', '1975-08-03', '82185800077', 'GURU', '', 'MENDALO', '', ''),
(629, 'E 1213', 'ENI', 'P', '2017-07-06', '1970-08-01', '85266973175', 'GURU', '', 'KENALI BESAR', '', ''),
(630, 'D 1249', 'DWI HASTUTI', 'P', '2017-07-06', '1988-08-02', '85266811855', '', '', 'PASIR PUTIH', '', ''),
(631, 'D 0888', 'DARA PERMATA SARI', 'P', '2017-07-06', '2018-01-01', '82176474079', '', '', 'H. BADAR', '', ''),
(632, 'N 1279', 'NURHAYATI', 'P', '2017-07-08', '2018-01-01', '85281639644', 'KOTA BARU', '', '', '', ''),
(633, 'F 0604', 'FARADILA', 'P', '2017-07-08', '2018-01-01', '81373248900', '', '', 'BRONI', '', ''),
(634, 'I 0839', 'IMELDA', 'P', '2017-07-08', '1979-01-22', '85274778879', '', '', 'TELANAI PURA', '', ''),
(635, 'I 0819', 'IFIT', 'P', '2017-07-08', '2018-01-01', '81271784226', '', '', 'KASANG PUDAK', '', ''),
(636, 'T 0513', 'TIYEM', 'P', '2017-07-08', '2018-01-01', '82178407868', '', '', 'KEBUN DUREN', '', ''),
(637, 'M 1337', 'MUTIA', 'P', '2017-07-08', '1999-02-21', '81224250081', '', '', 'KOTA BARU', '', ''),
(638, 'H 0566', 'HALILI', 'P', '2017-07-08', '1983-05-31', '85266811680', '', '', 'TANJUNG PINANG', '', ''),
(639, 'N 1278', 'NANI', 'P', '2017-07-08', '1985-06-15', '85378673747', '', '', 'MUARO JAMBI', '', ''),
(640, 'S 1640', 'SARA JUMIATI SARI', 'P', '2017-07-08', '1993-04-18', '81278663882', '', '', 'MUARO JAMBI', '', ''),
(641, 'D 1251', 'DESMAWATI', 'P', '2017-07-08', '2064-12-02', '82392703057', '', '', 'JELUTUNG', '', ''),
(642, 'N 1277', 'NURHAYATI', 'P', '2017-07-08', '1989-05-19', '82183235926', '', '', 'BERINGIN', '', ''),
(643, 'R 1643', 'RAHMAN', 'P', '2017-07-08', '2018-01-01', '82373199239', 'SWASTA', '', 'KAMPUNG MANGGIS', '', ''),
(644, 'M 0325', 'MINAR', 'P', '2017-07-08', '2018-01-01', '81366106578', '', '', 'TELANAI PURA', '', ''),
(645, 'F 0618', 'FEBY', 'P', '2017-07-08', '2004-04-27', '8,21354E+11', 'SWASTA', '', 'MENDALO', '', ''),
(646, 'H 0538', 'HERAWATI', 'P', '2017-07-08', '1977-12-14', '85266675888', '', '', 'JELUTUNG', '', ''),
(647, 'H 0537', 'HALIMAH', 'P', '2017-07-08', '2018-01-01', '81274052308', '', '', 'MAYANG', '', ''),
(648, 'A 1541', 'ALDY IFIANTO', 'P', '2017-07-09', '1999-08-31', '81366323274', '', '', 'PAL MERAH', '', ''),
(649, 'L 0928', 'LITA', 'P', '2017-07-09', '2018-01-01', '82178149797', '', '', 'KASANG', '', ''),
(650, 'I 0122', 'INDAH', 'P', '2017-07-09', '2018-01-01', '85764200099', '', '', 'MAYANG', '', ''),
(651, 'N 1253', 'NOVI', 'P', '2017-07-09', '2018-01-01', '85366300582', '', '', 'KENALI ASAM BAWAH', '', ''),
(652, 'N 1280', 'NESYA', 'P', '2017-07-09', '1992-11-22', '85378489562', '', '', 'PASAR', '', ''),
(653, 'I 0840', 'IFAN', 'P', '2017-07-09', '1992-05-07', '82373032009', 'WIRASWASTA', '', 'BANGKO', '', ''),
(654, 'V 0202', 'VIKA', 'P', '2017-07-09', '2018-01-01', '82282255010', '', '', 'MAYANG', '', ''),
(655, 'F 0590', 'FITRI YANTI', 'P', '2017-07-09', '2018-01-01', '82306437980', '', '', 'MAYANG', '', ''),
(656, 'R 1644', 'RIBELIA', 'P', '2017-07-12', '1989-01-26', '82261399581', '', '', 'PAYO SELINCAH', '', ''),
(657, 'N 0677', 'NANI', 'P', '2017-07-12', '2018-01-01', '85266816371', '', '', 'MUARA BULIAN', '', ''),
(658, 'L 0959', 'LARA JUITA', 'P', '2017-07-12', '1997-12-05', '82373847264', '', '', 'EKA JAYA', '', ''),
(659, 'D 1219', 'DEVITA', 'P', '2017-07-12', '2018-01-01', '82176161874', '', '', 'KOTA BARU', '', ''),
(660, 'N 1281', 'NURLELI', 'P', '2017-07-12', '2018-01-01', '89533662406', '', '', 'KASANG', '', ''),
(661, 'M 1338', 'MISWATI', 'P', '2017-07-12', '1984-01-05', '85266899917', '', '', 'KEBUN HANDIL', '', ''),
(662, 'A 1542', 'ADE', 'L', '2017-07-12', '2018-01-01', '81365601227', '', '', 'KENALI', '', ''),
(663, 'E 0804', 'ESA', 'P', '2017-07-12', '2018-01-01', '85355585748', '', '', 'KASANG', '', ''),
(664, 'A 1374', 'AMELIA', 'P', '2017-07-12', '2018-01-01', '82380158885', '', '', 'MUARA BULIAN', '', ''),
(665, 'M 1238', 'MINA', 'P', '2017-07-12', '2018-01-01', '85266748382', '', '', 'KUMPE', '', ''),
(666, 'H 0001', 'HELENA', 'P', '2017-07-12', '1986-08-25', '85357176910', '', '', 'JERAMBAH BOLONG', '', ''),
(667, 'M 1218', 'MARHAMAH', 'P', '2017-07-12', '2018-01-01', '85268086807', '', '', 'PAL 16', '', ''),
(668, 'L 0001', 'LILIS', 'P', '2017-07-12', '2018-01-01', '85368831557', '', '', 'PERSIJAM', '', ''),
(669, 'P 0291', 'PUTRI', 'P', '2017-07-12', '2018-01-01', '82184535925', '', '', 'BAYUNG LINCIR', '', ''),
(670, 'I 0722', 'IMELDA MANURUNG', 'P', '2017-07-12', '2018-01-01', '81232417905', '', '', 'BAYUNG', '', ''),
(671, 'F 0619', 'FAHMI', 'P', '2017-07-12', '2004-05-24', '82297407557', '', '', 'BAYUNG LINCIR', '', ''),
(672, 'M 0151', 'MERRY CANDRA', 'P', '2017-07-12', '2018-01-01', '82170557777', '', '', 'KEBUN HANDIL', '', ''),
(673, 'F 0620', 'FATMAWATI', 'P', '2017-07-12', '2060-12-08', '81366824408', 'PNS', '', 'KASANG JAYA', '', ''),
(674, 'N 1282', 'NURMA NELI', 'P', '2017-07-12', '1976-06-01', '85264930577', 'IRT', '', 'TALANG BAKUNG', '', ''),
(675, 'E 1212', 'EKA LIA', 'P', '2017-07-12', '1988-11-28', '85268972684', '', '', 'SIMPANG ACAI', '', ''),
(676, 'F 0621', 'FEBIANA NOVA', 'P', '2017-07-12', '1985-02-17', '8127492472', 'PNS', '', 'TALANG BAKUNG', '', ''),
(677, 'B 0163', 'BELINDA MONALISA', 'P', '2017-07-14', '2018-01-01', '81366127266', '', '', 'SUNGAI PENUH', '', ''),
(678, 'R 0288', 'RINA', 'P', '2017-07-14', '1987-03-05', '85266180809', 'SWASTA', '', 'NUSA INDAH', '', ''),
(679, 'S 0050', 'SRI WAHYUNI', 'P', '2017-07-14', '2018-01-01', '81366523734', '', '', 'PAYO SELINCAH', '', ''),
(680, 'M 1180', 'MELIANA', 'P', '2017-07-14', '2018-01-01', '85266811955', '', '', 'PASIR PUTIH', '', ''),
(681, 'K 0272', 'KEVIN', 'L', '2017-07-14', '2018-01-01', '89684567899', '', '', 'PALL MERAH LAMA', '', ''),
(682, 'L 0825', 'LISA ANGGAINI', 'P', '2017-07-14', '2018-01-01', '81273370722', '', '', 'TELANAI', '', ''),
(683, 'N 1202', 'NIKEN', 'P', '2017-07-14', '2018-01-01', '85266770991', '', '', 'THEHOK', '', ''),
(684, 'D 0003', 'DITA ARSITA', 'P', '2017-07-14', '1996-01-25', '82306237352', '', '', 'SAROLANGUN', '', ''),
(685, 'K 0001', 'KHUSNA', 'P', '2017-07-14', '2018-01-01', '85266465877', '', '', 'PERUM BOGUNVILLA LESTARI BLOK AF NO. 8', '', ''),
(686, 'D 1253', 'DIANA', 'P', '2017-07-14', '1981-11-20', '82183578185', 'PNS', '', 'PERSIJAM', '', ''),
(687, 'D 1084', 'DIANA', 'P', '2017-07-14', '2018-01-01', '85366791978', 'PERAWAT', '', 'BERINGIN', '', ''),
(688, 'S 0363', 'SULASNI', 'P', '2017-07-14', '2018-01-01', '85266663582', '', '', 'SAROLANGUN', '', ''),
(689, 'R 1340', 'RITA', 'P', '2017-07-15', '2018-01-01', '81210496118', '', '', 'TANJUNG PINANG', '', ''),
(690, 'D 1254', 'DASLIR KADIR', 'P', '2017-07-15', '2058-08-08', '81377906258', '', '', 'MENDALO', '', ''),
(691, 'R 1607', 'RINI MARLINA', 'P', '2017-07-15', '2018-01-01', '85366972065', '', '', 'SIJENJANG', '', ''),
(692, 'I 0097', 'IMELDA', 'P', '2017-07-15', '2018-01-01', '81539865822', '', '', 'TELANAI', '', ''),
(693, 'Y 0982', 'YUNI', 'P', '2017-07-15', '1983-06-25', '85266690340', '', '', 'KASANG', '', ''),
(694, 'S 1641', 'SITI KHOLIFAH', 'P', '2017-07-15', '2069-10-05', '81224397971', 'PNS', '', 'KASANG', '', ''),
(695, 'F 0121', 'FITRI', 'P', '2017-07-15', '2018-01-01', '', '', '', 'TELANAI', '', ''),
(696, 'A 1424', 'ASLAMIAH', 'P', '2017-07-17', '2018-01-01', '85357179840', '', '', 'JELUTNUNG', '', ''),
(697, 'N 1267', 'NOVA', 'P', '2017-07-17', '1977-12-13', '82371570474', '', '', 'SIPIN', '', ''),
(698, 'C 0178', 'CITRA', 'P', '2017-07-17', '1998-03-01', '85215600967', 'SWASTA', '', 'PERSIJAM', '', ''),
(699, 'L 0960', 'LELI', 'P', '2017-07-17', '1978-01-24', '82183248103', 'IRT', '', 'PAL MERAH', '', ''),
(700, 'A 1543', 'AFITA', 'P', '2017-07-18', '1994-04-28', '82307319368', '', '', 'SIMPANG AHOK', '', ''),
(701, 'C 0171', 'CELIN', 'P', '2017-07-18', '2018-01-01', '85269536274', '', '', 'KUMPE', '', ''),
(702, 'H 0169', 'HARIATI', 'P', '2017-07-18', '2018-01-01', '85384700612', '', '', 'JELUTUNG', '', ''),
(703, 'R 0303', 'RISKA', '', '2017-07-18', '1986-02-01', '85357188673', '', '', 'SEBRANG', '', ''),
(704, 'D 1255', 'DR. TETI ASNAWI', 'P', '2017-07-18', '2058-09-02', '81994765767', '', '', 'TELANAI PURA', '', ''),
(705, 'K 0079', 'KASMANELI', 'P', '2017-07-18', '2068-04-08', '', '', '', 'PASAR', '', ''),
(706, 'M 1092', 'MANSYUR', 'P', '2017-07-18', '2018-01-01', '81933486679', '', '', 'PASIR PUTIH', '', ''),
(707, 'N 1283', 'NANA', 'P', '2017-07-18', '1992-08-03', '81368284986', '', '', 'TANGKIT BARU', '', ''),
(708, 'R 1645', 'RIA', 'P', '2017-07-18', '1993-09-23', '82280521093', '', '', 'TANGKIT BARU', '', ''),
(709, 'T 0561', 'TATA', 'P', '2017-09-07', '2003-03-24', '81221568251', '', '', 'KASANG RAJAWALI', '', ''),
(710, 'I 0001', 'IDDANI', 'P', '2017-09-07', '2018-01-01', '81366747700', '', '', 'TALANG BANJAR', '', ''),
(711, 'Y 0002', 'YANI', 'P', '2017-07-19', '1995-02-27', '82327823344', '', '', 'TEBO', '', ''),
(712, 'N 0781', 'NINDI', 'P', '2017-07-19', '1990-04-12', '82216178617', '', '', 'PANGLIMA POLIM', '', ''),
(713, 'A 1518', 'ARDIA GITA', 'P', '2017-07-19', '2018-01-01', '85380462955', '', '', 'KASANG', '', ''),
(714, 'S 1642', 'SRI ENY', 'P', '2017-07-19', '1977-02-23', '81273591381', '', '', 'SIMPANG RIMBO', '', ''),
(715, 'R 1646', 'REISA', 'P', '2017-07-19', '2018-01-01', '85366129766', '', '', 'JALUKO', '', ''),
(716, 'A 1237', 'ANA', 'P', '2017-07-19', '2018-01-01', '85380700099', '', '', 'PENURUNAN DKT', '', ''),
(717, 'C 0177', 'CICI CAHYATI', 'P', '2017-07-19', '1998-02-10', '82297856321', '', '', 'JERAMBAH BOLONG', '', ''),
(718, 'R 0860', 'RISKA AULIA', 'P', '2017-07-19', '2018-01-01', '85367888056', '', '', 'TANJUNG LUMUT', '', ''),
(719, 'V 0226', 'VERIA', 'P', '2017-07-19', '2018-01-01', '85293689888', 'SWASTA', '', 'KONI', '', ''),
(720, 'S 1406', 'SRIWIYANTI', 'P', '2017-07-21', '2018-01-01', '81366090194', '', '', 'TELANAI', '', ''),
(721, 'W 0470', 'WIDIA ROMANA', 'P', '2017-07-21', '1993-10-10', '82280231953', '', '', 'TALANG BAKUNG', '', ''),
(722, 'T 0466', 'TITIN', 'P', '2017-07-21', '2018-01-01', '85216132224', '', '', 'KASANG PUDAK', '', ''),
(723, 'N 1284', 'NURMALA SARI', 'P', '2017-07-21', '1999-02-18', '85216132224', '', '', 'KASANG PUDAK', '', ''),
(724, 'A 0789', 'ATI SIREGAR', 'P', '2017-07-21', '2018-01-01', '81366222026', '', '', 'JELUTUNG', '', ''),
(725, 'S 1231', 'SITI UMARAH', 'P', '2017-07-21', '2018-01-01', '', '', '', 'KASANG', '', ''),
(726, 'T 0534', 'TIARA', 'P', '2017-07-21', '2018-01-01', '85669859394', '', '', 'BAJUBANG', '', ''),
(727, 'S 1643', 'SURYATI', 'P', '2017-07-21', '1994-10-02', '85369797966', '', '', 'PALL MERAH LAMA', '', ''),
(728, 'L 0882', 'LIA', 'P', '2017-07-21', '2018-01-01', '82281989260', '', '', 'TANJUNG SARI', '', ''),
(729, 'S 0595', 'SITI NURJANAH', 'P', '2017-07-21', '2018-01-01', '85266591926', '', '', 'H.KAMIL', '', ''),
(730, 'W 0469', 'WIDIA NITA S', 'P', '2017-07-21', '1993-04-22', '82306052002', '', '', 'TALANG BANJAR', '', ''),
(731, 'I 0841', 'IMANUEL', 'P', '2017-07-21', '1997-06-12', '81272666646', '', '', 'TALANG BAKUNG', '', ''),
(732, 'E 0001', 'ENDA FATMA', 'P', '2017-07-21', '1994-07-13', '', '', '', 'TANGKIT', '', ''),
(733, 'S 0577', 'SUMARNI', 'P', '2017-07-25', '2018-01-01', '811748253', '', '', 'HASIM ASARI N0 5', '', ''),
(734, 'S 1557', 'SARTIKA', 'P', '2017-07-25', '2018-01-01', '85266668747', '', '', 'SUNANGIN', '', ''),
(735, 'S 1644', 'SISKA', 'P', '2017-07-25', '1994-05-24', '81273911585', '', '', 'SIMPANG PULAI', '', ''),
(736, 'V 0227', 'VITA', 'P', '2017-07-25', '1995-12-28', '85377789495', '', '', 'KEBUN KOPI', '', ''),
(737, 'N 1069', 'NURYANI', 'P', '2017-07-25', '2018-01-01', '1273145402', '', '', 'TANJUNG SARI', '', ''),
(738, 'Y 0979', 'YENNI ANGGRAINI', 'P', '2017-07-25', '1988-03-05', '85266625050', '', '', 'NUSA INDAH', '', ''),
(739, 'E 0823', 'ERNI', 'P', '2017-07-25', '2018-01-01', '85378995806', '', '', 'TALANG BAKUNG', '', ''),
(740, 'E 0812', 'ENI', 'P', '2017-07-25', '1985-12-19', '81377791032', '', '', 'MENDALO', '', ''),
(741, 'M 1284', 'MAULIA', 'P', '2017-07-25', '2018-01-01', '81266211884', '', '', 'SELINCAH', '', ''),
(742, 'D 1257', 'DEBBY', 'P', '2017-07-25', '1997-05-11', '82186679026', '', '', 'MENDALO', '', ''),
(743, 'K 0279', 'KIKI', 'P', '2017-07-25', '1987-12-02', '85268109110', '', '', 'SIJINJANG', '', ''),
(744, 'A 1489', 'ADE YOLANDA', 'P', '2017-07-25', '2018-01-01', '85357674241', '', '', 'MAYANG', '', ''),
(745, 'S 1466', 'SITU ZAINI', 'P', '2017-07-25', '2018-01-01', '81366339357', '', '', 'SIMPANG KAWAT', '', ''),
(746, 'I 0650', 'ISA', 'P', '2017-07-25', '2018-01-01', '85266072009', '', '', 'JALAN BARU', '', ''),
(747, 'S 1645', 'SARIFAH AISYAH', 'P', '2017-07-25', '1973-05-09', '81366006039', '', '', 'EKA JAYA', '', ''),
(748, 'D 1258', 'DONI', 'P', '2017-07-25', '1998-01-07', '82279125133', '', '', 'PAL MERAH', '', ''),
(749, 'S 1541', 'SUSMAWATI', 'P', '2017-07-25', '2018-01-01', '82377656560', '', '', 'TELANAI', '', ''),
(750, 'H 0106', 'HUSNAWATI', 'P', '2017-09-07', '2018-01-01', '85266465877', '', '', 'TERMINAL BARU', '', ''),
(751, 'S 1638', 'SALAMAH', 'P', '2017-07-25', '1985-04-04', '82176454563', '', '', 'MERLUNG', '', ''),
(752, 'S 1432', 'SRI KANTI', 'P', '2017-07-25', '2018-01-01', '85384096699', '', '', 'KUMPE', '', ''),
(753, 'F 0622', 'FARIDA', 'P', '2017-07-25', '1973-04-29', '81272666646', '', '', 'TALANG BAKUNG', '', ''),
(754, 'Y 0945', 'YENNI', 'P', '2017-07-25', '2018-01-01', '85366519373', '', '', 'KUMPE', '', ''),
(755, 'K', 'KRISTIE', 'P', '2017-07-25', '2018-01-01', '', '', '', '', '', ''),
(756, 'S 1646', 'SARAH', 'P', '2017-07-26', '1995-04-09', '89602959225', '', '', 'BOHOK', '', ''),
(757, 'N 1112', 'NOVI', 'P', '2017-07-26', '2018-01-01', '85258422727', '', '', 'MAYANG', '', ''),
(758, 'Y 0788', 'YUNI', 'P', '2017-07-26', '1982-07-25', '85375607516', '', '', 'MERLUNG', '', ''),
(759, 'M 1130', 'MIA', 'P', '2017-07-26', '2018-01-01', '82280049502', '', '', 'IBRAHIM', '', ''),
(760, 'F 0624', 'FATIMAH', 'P', '2017-07-26', '2018-01-01', '85266391152', '', '', 'PERSIJAM', '', ''),
(761, 'F 0623', 'FITRIASYAH', 'P', '2017-07-26', '2018-01-01', '85266391152', '', '', 'PERSIJAM', '', ''),
(762, 'M 1539', 'SUMARNI', 'P', '2017-07-27', '2018-01-01', '85273935607', '', '', 'HANDIL', '', ''),
(763, 'D 1259', 'DIAN', 'P', '2017-07-27', '1978-05-18', '82320010018', '', '', 'SUNGAI SAWANG', '', ''),
(764, 'R 1647', 'RIRIN', 'P', '2017-07-27', '1995-04-22', '8,22814E+11', '', '', 'BULURAN', '', ''),
(765, 'F 0625', 'FENI', 'P', '2017-07-27', '2062-04-27', '81278878325', '', '', 'MAYANG MANGURAI', '', ''),
(766, 'S 1577', 'SAIDAH', 'P', '2017-07-28', '2018-01-01', '85269536274', '', '', 'KUMPE', '', ''),
(767, 'R 1550', 'RANI', 'P', '2017-07-28', '2018-01-01', '85287419052', '', '', 'JL. LIMGKAR SELATAN', '', ''),
(768, 'N 1286', 'NURJANAH', 'P', '2017-07-28', '1983-06-15', '85379793331', '', '', 'SIMPANG PULAI', '', ''),
(769, 'L 0961', 'LISA', 'P', '2017-07-28', '1981-12-10', '85342651678', '', '', 'SIMPANG PULAI', '', ''),
(770, 'N 1285', 'NURZALIA RAMDANI', 'P', '2017-07-28', '1989-10-20', '85266427000', '', '', 'TANJUNG PINANG', '', ''),
(771, 'D 1247', 'DINDA', 'P', '2017-07-28', '1995-01-12', '82278852720', '', '', 'MENDALO', '', ''),
(772, 'N 1287', 'NITA', 'P', '2017-07-29', '1975-04-20', '81390702640', '', '', 'PAL MERAH', '', ''),
(773, 'L 0962', 'LENI HERLINA', 'P', '2017-07-29', '1987-10-17', '81366294783', 'SWASTA', '', 'TELANAI', '', ''),
(774, 'A 1544', 'ARMILA SARI', 'P', '2017-07-29', '1988-03-23', '8127449784', '', '', 'KUMPE', '', ''),
(775, 'M 1341', 'M. ERFAN ARNOLDI', 'P', '2017-07-29', '1996-05-24', '81278611024', '', '', 'BANYUNG LINCIR', '', ''),
(776, 'M 1340', 'MAYANG NURUL S', 'P', '2017-07-29', '2000-03-27', '8,95621E+11', '', '', 'KOTA BARU', '', ''),
(777, 'U 0249', 'ULI WULAN DARI', 'P', '2017-07-29', '1989-02-21', '81274327422', '', '', 'KASANG', '', ''),
(778, 'H 0512', 'HJ. MARDIANA', 'P', '2017-07-29', '2018-01-01', '85273167147', '', '', 'BOLONG', '', ''),
(779, 'L 0963', 'LELA', 'P', '2017-07-30', '1995-08-10', '85290370713', 'MAHASISWI', '', 'THEHOK', '', ''),
(780, 'Y 0983', 'YOSSY', 'P', '2017-07-30', '1992-11-18', '82337949414', '', '', 'KENALI ASAM BAWAH', '', ''),
(781, 'H 0568', 'HERLINA HARAHAP', 'P', '2017-07-30', '2065-06-28', '85366281711', '', '', 'NUSA INDAH 3', '', ''),
(782, 'A 1509', 'AYU LESTARI', 'P', '2017-07-31', '2018-01-01', '8,9562E+11', '', '', 'SELINCAH', '', ''),
(783, 'H 0503', 'HELMI', 'P', '2017-07-31', '2018-01-01', '82378467374', '', '', 'TALANG BANJAR', '', ''),
(784, 'D 1015', 'DIAN', 'P', '2017-07-31', '2018-01-01', '81272058636', '', '', 'SIPIN', '', ''),
(785, 'F 0626', 'FRANIKA', 'P', '2017-07-31', '1992-02-14', '81221625769', '', '', 'KOTA BARU', '', ''),
(786, 'M 1342', 'MARJANI', 'P', '2017-07-31', '1970-03-05', '82375441315', '', '', 'PENEROKAN', '', ''),
(787, 'E 1214', 'ENI MARLITA', 'P', '2017-07-31', '1975-01-01', '81274483694', '', '', 'MESTONG', '', ''),
(788, 'D 1236', 'DIANA', 'P', '2017-07-31', '1997-03-08', '85783163661', '', '', 'TAMAN ALI', '', ''),
(789, 'A 1475', 'ANIS', 'P', '2017-07-31', '2018-01-01', '85368745535', '', '', 'MUARA BULIAN', '', ''),
(790, 'S 1648', 'SOFIE', 'P', '2017-08-01', '2018-01-01', '81274800678', '', '', 'THEHOK', '', ''),
(791, 'S 1647', 'SURYANTI', 'P', '2017-08-01', '1970-12-14', '82372550094', '', '', 'TALANG BAKUNG', '', ''),
(792, 'S 0118', 'SUMI', 'P', '2017-08-01', '2018-01-01', '81366418291', '', '', 'MANDI ANGIN', '', ''),
(793, 'R 0153', 'ROFIKO', 'P', '2017-08-01', '2018-01-01', '85267798989', '', '', 'TALANG BANJAR', '', ''),
(794, 'E 1084', 'ENI', 'P', '2017-08-01', '2018-01-01', '81274699474', '', '', 'PONDOK MEJA', '', ''),
(795, 'B 0158', 'BELLA', 'P', '2017-08-01', '2018-01-01', '85290904622', '', '', 'PASAR BARU', '', ''),
(796, 'R 1591', 'REGINA', 'P', '2017-08-01', '2018-01-01', '82184075359', '', '', 'BRONI', '', ''),
(797, 'M 0014', 'MURNIATI', 'P', '2017-08-02', '2018-01-01', '8163205596', '', '', 'KEBUN HANDIL', '', ''),
(798, 'E 0448', 'EMILIA', 'P', '2017-08-02', '2018-01-01', '8127404065', '', '', 'PASAR BARU', '', ''),
(799, 'Y 0825', 'YUPIN', 'P', '2017-08-02', '2018-01-01', '81272620545', '', '', 'BUDIMAN', '', ''),
(800, 'R 1551', 'RATIH DINIA FERLY', 'P', '2017-08-02', '2018-01-01', '85379828767', '', '', 'KEBUN HANDIL', '', ''),
(801, 'N 1140', 'NOVITA SARI', 'P', '2017-08-02', '2018-01-01', '82307318994', '', '', 'PASIR PUTIH', '', ''),
(802, 'T 0557', 'TETTY', 'P', '2017-08-02', '1980-04-01', '81372721029', '', '', 'TALANG BAKUNG', '', ''),
(803, 'H 0553', 'HANDRA', 'P', '2017-08-02', '2018-01-01', '8127351677', '', '', 'JELUTUNG', '', ''),
(804, 'E 1063', 'EMILIA', 'P', '2017-08-02', '2018-01-01', '818742319', '', '', 'JL. BAYANGKARA NO. 19', '', ''),
(805, 'M 1343', 'MARTHA', 'P', '2017-08-03', '1987-02-16', '85266424251', '', '', 'THEHOK', '', ''),
(806, 'A 0002', 'ASIS THEBA', 'P', '2017-08-03', '2018-01-01', '82376370000', '', '', 'SELINCAH', '', ''),
(807, 'K 0232', 'KARIANTI', 'P', '2017-08-03', '2018-01-01', '82373831055', '', '', 'KASANG', '', ''),
(808, 'S 1658', 'SUPARTINI', 'P', '2017-09-07', '2068-07-30', '85268314921', '', '', 'TALANG BAKUNG', '', ''),
(809, 'L 08211', 'LILI ROSITA', 'P', '2017-08-04', '2018-01-01', '81366346702', '', '', 'THEHOK', '', ''),
(810, 'N 1288', 'NINA', 'P', '2017-08-04', '1998-07-20', '82393028401', 'SWASTA', '', 'KONI', '', ''),
(811, 'I 0823', 'IKA NANDA', 'P', '2017-08-04', '2018-01-01', '82384371430', '', '', 'TANGKIT', '', ''),
(812, 'H 0559', 'HUZAIMAH', 'P', '2017-08-04', '2018-01-01', '85366022281', '', '', 'JLN. RADEN FATAH RT.02 KEL SEJENJANG', '', ''),
(813, 'M 0323', 'MELY', 'P', '2017-08-04', '2018-01-01', '81366869869', '', '', 'NUSA INDAH', '', ''),
(814, 'A 1545', 'ASMARANI', 'P', '2017-08-04', '1988-09-21', '85373333336', '', '', 'BERINGIN', '', ''),
(815, 'M 1344', 'MARYAM', 'P', '2017-08-04', '2018-01-01', '82380077474', '', '', 'KUMPEH', '', ''),
(816, 'L 0964', 'LUSIANA PUTRI', 'P', '2017-08-04', '1995-09-23', '82281482526', '', '', 'MENDALO', '', ''),
(817, 'S 0004', 'SURYATI', 'P', '2017-08-04', '1993-08-25', '85832665191', '', '', 'SELAT', '', ''),
(818, 'W 0002', 'WIDYA AMRINA', 'P', '2017-08-04', '1995-05-25', '82279777391', '', '', 'MENDALO', '', ''),
(819, 'N 1289', 'NANDANG', 'P', '2017-08-04', '1992-01-25', '85267034255', '', '', 'RIMBO BUJANG', '', ''),
(820, 'C 0179', 'CELIN', 'P', '2017-08-04', '1999-11-24', '82213065611', '', '', 'KEBUN HANDIL', '', ''),
(822, 'A 1429', 'ALFIA', 'P', '2017-08-05', '2018-01-01', '81367003338', '', '', 'KASANG', '', ''),
(823, 'A 1546', 'AYU', 'P', '2017-08-05', '2000-02-08', '81366558151', '', '', 'ANCOL', '', ''),
(824, 'N 0069', 'NANA', 'P', '2017-08-05', '2018-01-01', '81994427027', '', '', 'PALL MERAH', '', ''),
(825, 'S 1489', 'SERUNI', 'P', '2017-08-05', '2018-01-01', '85266663321', '', '', 'TELANAI PURA', '', ''),
(826, 'R 1486', 'RENDI D', 'P', '2017-08-05', '2018-01-01', '85266946114', '', '', 'MENDALO', '', ''),
(827, 'L 0965', 'LILY', 'P', '2017-08-05', '1980-11-27', '81279864966', '', '', 'KASANG', '', ''),
(828, 'Y 0616', 'YENI', 'P', '2017-08-05', '2018-01-01', '85383401619', '', '', 'PASAR', '', ''),
(829, 'D 1179', 'DARA YULIANTI', 'P', '2017-08-05', '2018-01-01', '82176458576', '', '', 'JERAMBAH BOLONG', '', ''),
(830, 'N 0217', 'NURHAYATI', 'P', '2017-08-05', '2018-01-01', '85266655890', '', '', 'SUNGAI BAHAR', '', ''),
(831, 'H 0546', 'HALIJAH', 'P', '2017-08-05', '2018-01-01', '8127410238', '', '', 'SABAK', '', ''),
(832, 'N 1042', 'NURHAJIAH', 'P', '2017-08-07', '2018-01-01', '82364215444', '', '', 'SENGETI', '', ''),
(833, 'H 0429', 'HAPNI NOPITRI ANA', 'P', '2017-08-07', '1990-11-10', '81277617765', '', '', 'BANGKO', '', ''),
(834, 'B 0117', 'BELIYA', 'P', '2017-08-07', '1984-10-04', '85278165116', '', '', 'MENDALO', '', ''),
(835, 'R 1613', 'ROSMALA DEWI', 'P', '2017-08-07', '2018-01-01', '82377656945', '', '', 'TALANG BAKUNG', '', ''),
(836, 'R 1648', 'RIA ROSIDA', 'P', '2017-08-07', '2018-01-01', '81368293737', '', '', 'SEBRANG', '', ''),
(837, 'S 0005', 'SEVIANA', 'P', '2017-08-07', '2018-01-01', '', '', '', 'TANJAB TIMUR', '', ''),
(838, 'Y 0888', 'YULIANA', 'P', '2017-08-08', '2018-01-01', '82281578399', '', '', 'GARUDA 1', '', ''),
(839, 'F 0168', 'FETI', 'L', '2017-08-08', '2018-01-01', '85266067963', '', '', 'TANJUNG PINANG', '', ''),
(840, 'M 1119', 'M AS\'AD', 'P', '2017-08-08', '2018-01-01', '85266960776', '', '', 'SEBRANG', '', ''),
(841, 'E 1215', 'EVA AWALIAH', 'P', '2017-08-08', '2069-11-20', '81274105218', '', '', 'BANGKO', '', ''),
(842, 'S 1334', 'SITI SAIBAH', 'P', '2017-08-08', '2018-01-01', '82175958188', '', '', 'KASANG', '', ''),
(843, 'A 1547', 'AFAFI FADIL', 'P', '2017-08-09', '1986-12-02', '85266662969', '', '', 'KEBUN HANDIL', '', ''),
(844, 'N 1291', 'NURHADISMA', 'P', '2017-08-09', '1999-02-16', '82279397931', '', '', 'SEBRANG', '', ''),
(845, 'R 1650', 'RTS.HOIRIYAH', 'P', '2017-08-09', '1971-01-05', '82279397931', '', '', 'SEBRANG', '', ''),
(846, 'I 0842', 'IRA', 'P', '2017-08-09', '2018-01-01', '82373529984', '', '', 'TANJUNG PINANG', '', ''),
(847, 'R 1649', 'REFQI', 'P', '2017-08-09', '1991-02-17', '81372700754', '', '', 'KOTA BARU', '', ''),
(848, 'R 1458', 'RIANA', 'P', '2017-08-09', '2018-01-01', '85266232223', '', '', 'JELUTUNG', '', ''),
(849, 'N 1290', 'NINA', 'P', '2017-08-09', '1987-07-10', '82282245281', '', '', 'JELUTUNG', '', ''),
(850, 'R 1537', 'ROSA', 'P', '2017-08-09', '2018-01-01', '82371129216', '', '', 'PAKUAN BARU', '', ''),
(851, 'W 0472', 'WIDIA', 'P', '2017-08-09', '1997-03-01', '82247967226', '', '', 'BRONI', '', ''),
(852, 'I 0683', 'INTAN', 'P', '2017-08-09', '2018-01-01', '85269753245', '', '', 'TALANG DUKU', '', ''),
(853, 'E 0820', 'ERNI', 'P', '2017-08-09', '1995-07-20', '82280222722', '', '', 'BAYUNG LINCIR', '', ''),
(854, 'A 1548', 'ANDI SITTI', 'P', '2017-08-09', '2069-10-23', '81272092717', '', '', 'KENALI', '', ''),
(855, 'A 1549', 'ANA', 'P', '2017-08-09', '1993-05-20', '81272052717', '', '', 'KENALI', '', ''),
(856, 'N 1186', 'NIKMAH', 'P', '2017-08-10', '2018-01-01', '81270313332', '', '', 'KUMPE', '', ''),
(857, 'Y 0984', 'YUHA', 'P', '2017-08-10', '2067-12-12', '81366440002', '', '', 'BAYUNG LINCIR', '', ''),
(858, 'M 1345', 'MARINA', 'P', '2017-08-10', '1986-05-04', '81271173345', '', '', 'TALANG BAKUNG', '', ''),
(859, 'M 0488', 'MASLAINI', 'P', '2017-08-10', '2018-01-01', '82178422229', '', '', 'PERUMNAS KOTA BARU', '', ''),
(860, 'W 0436', 'WARIATI', 'P', '2017-08-10', '2018-01-01', '8127407655', '', '', 'KASANG', '', ''),
(861, 'E 1217', 'ELIZA', 'P', '2017-08-10', '2000-05-13', '81366025558', '', '', 'KASANG', '', ''),
(862, 'E 1216', 'ERVINA ROSA', 'P', '2017-08-10', '1999-08-19', '89507493974', '', '', 'KASANG', '', ''),
(863, 'R 1651', 'RAMA DWI SAFITRI', 'P', '2017-08-10', '2000-12-10', '89694572161', '', '', 'SELINCAH', '', ''),
(864, 'W 0473', 'WINDA', 'P', '2017-08-10', '1999-12-31', '85338394036', '', '', 'SELINCAH', '', ''),
(865, 'F 0505', 'FARIDA', 'P', '2017-08-11', '1989-05-24', '85386861988', '', '', 'JELUTUNG', '', ''),
(866, 'A 1228', 'ATIK', 'P', '2017-08-11', '1982-11-07', '82180176825', '', '', 'RAJA BATU', '', ''),
(867, 'K 0281', 'KHOIRIYAH', 'P', '2017-08-11', '1990-09-29', '85380888826', '', '', 'NIFAH PANJANG', '', ''),
(868, 'I 0843', 'IIN', 'P', '2017-08-11', '1974-08-09', '81278556707', '', '', 'THEHOK', '', ''),
(869, 'S 0052', 'SHANTY', 'P', '2017-08-11', '2018-01-01', '82385839089', '', '', 'SIMPANG KAWAT', '', ''),
(870, 'A 1551', 'AFRIZAL', 'P', '2017-08-13', '1992-04-23', '85268041096', '', '', 'THEHOK', '', ''),
(871, 'N 1238', 'NIKE', 'P', '2017-08-13', '2018-01-01', '82372275504', '', '', 'KEBUN KOPI', '', ''),
(872, 'R 1575', 'RINA', 'P', '2017-08-13', '1990-12-15', '81212711147', '', '', 'TALANG BANJAR', '', ''),
(873, 'L 0966', 'LINDA', 'P', '2017-08-13', '1990-09-16', '85931249623', '', '', 'THEHOK', '', ''),
(874, 'F 0587', 'FRISKA', 'P', '2017-08-13', '2018-01-01', '85367989257', '', '', 'MENDALO', '', ''),
(875, 'M 1076', 'MARSIATUN', 'P', '2017-08-15', '1973-10-16', '85382625337', '', '', 'TANGKIT', '', ''),
(876, 'B 0147', 'BRENDA', 'P', '2017-08-15', '2018-01-01', '85268685117', '', '', 'MAYANG', '', ''),
(877, 'M 1346', 'MARNI', 'P', '2017-08-15', '2018-01-01', '81377921531', '', '', 'MARENE', '', ''),
(878, 'R 0002', 'RENI', 'P', '2017-08-15', '2018-01-01', '82183459888', '', '', 'TALANG BANJAR', '', ''),
(879, 'R 1652', 'RISKI', 'P', '2017-08-15', '2000-04-07', '', '', '', 'TANJUNG PINANG', '', ''),
(880, 'T 0550', 'TIARA', 'P', '2017-08-15', '1987-06-15', '85266399998', '', '', 'HANDIL', '', ''),
(881, 'D 1260', 'DEFITA PERMATASARI', 'P', '2017-08-15', '1993-04-02', '85267665454', '', '', 'PEMANCAR RCTI', '', ''),
(882, 'R 1590', 'RINA', 'P', '2017-08-15', '2018-01-01', '82307297949', '', '', 'BRONI', '', ''),
(883, 'E 1180', 'ERNAWATI', 'P', '2017-08-15', '2018-01-01', '85268872511', '', '', 'TALANG BAKUNG', '', ''),
(884, 'K 0226', 'KELI', 'P', '2017-08-15', '2018-01-01', '85266044333', '', '', 'SAROLANGUN', '', ''),
(885, 'H 0548', 'HELENA', 'P', '2017-08-15', '2018-01-01', '85266080909', '', '', 'MAYANG', '', ''),
(886, 'R 1514', 'ROSI', 'P', '2017-08-15', '2018-01-01', '85273191340', '', '', 'SELINCAH', '', ''),
(887, 'A 1550', 'ASMIATI', 'P', '2017-08-15', '2018-01-01', '82380998725', '', '', 'KASANG PUDAK', '', ''),
(888, 'S 1650', 'SALMA', 'P', '2017-08-15', '1996-11-18', '89698721664', '', '', 'PAYO SELINCAH', '', ''),
(889, 'N 0927', 'NENENG', 'P', '2017-08-15', '1987-12-30', '82375482888', '', '', 'TANGKIT', '', ''),
(890, 'R1423', 'RAISA', 'P', '2017-08-15', '1996-01-11', '85378706664', '', '', 'PEMANCAR', '', ''),
(891, 'S 1651', 'SITI MAIMUNAH', 'P', '2017-08-15', '1983-08-05', '85357628360', '', '', 'SUNGAI BAHAR', '', ''),
(892, 'L 0967', 'LENA TEGUH', 'P', '2017-08-15', '2018-01-01', '82184638178', '', '', 'THEHOK', '', ''),
(893, 'A 1552', 'ARNAWATI', 'P', '2017-08-15', '2018-01-01', '82379779738', '', '', 'KUMPEH', '', ''),
(894, 'Y 0632', 'YUNI', 'P', '2017-08-15', '2018-01-01', '31 TAHUN', '', '81366985349', 'MENDALO', '', ''),
(895, 'U 0006', 'ULIYA', 'P', '2017-08-18', '2018-01-01', '0741-667862', '', '', 'SIPIN', '', ''),
(896, 'E 1191', 'ENI SURYANTI', 'P', '2017-08-18', '2018-01-01', '81335688333', '', '', 'MAYANG', '', ''),
(897, 'M 1348', 'MELI', 'P', '2017-08-18', '1993-08-27', '85266000707', '', '', 'PAL V', '', ''),
(898, 'D 1261', 'DESY', 'P', '2017-08-18', '1972-10-19', '85266360940', 'PNS', '', 'PAL MERAH', '', ''),
(899, 'M 1349', 'MUHAMAD HUSNI', 'P', '2017-08-18', '2018-01-01', '', '', '', 'ROYAL', '', ''),
(900, 'S 1652', 'SUSI', 'P', '2017-08-18', '1980-07-09', '8163209209', '', '', 'THEHOK', '', ''),
(901, 'K 0282', 'KATRIN', 'P', '2017-08-19', '1981-08-04', '81366846466', '', '', 'BAGAN PETE', '', ''),
(902, 'T 0516', 'TENTI', 'P', '2017-08-19', '2018-01-01', '85266728202', '', '', 'TALANG BAKUNG', '', ''),
(903, 'L 0826', 'LENI MARLINA', 'P', '2017-08-19', '2018-01-01', '85279357511', '', '', 'TELANAI', '', ''),
(904, 'M 1251', 'MERI', 'P', '2017-08-19', '2018-01-01', '85266460700', '', '', 'MUARA BULIAN', '', ''),
(905, 'A 1554', 'AVIZA CINTIA', 'P', '2017-08-19', '2018-01-01', '81366188984', '', '', 'SEBRANG', '', ''),
(906, 'S 1470', 'SELI', 'P', '2017-08-19', '2018-01-01', '81368871515', '', '', 'PAL 16', '', ''),
(907, 'A 0474', 'ARIF', 'P', '2017-08-19', '1985-02-23', '8526687248', '', '', 'THEHOK', '', ''),
(908, 'F 0627', 'FITRI', 'P', '2017-08-19', '1985-06-30', '85266087248', '', '', 'THEHOK', '', ''),
(909, 'L 0968', 'LARISA', 'P', '2017-08-19', '1970-05-15', '81295585794', '', '', 'PASIR PUTIH', '', ''),
(910, 'D 0004', 'DEBORA', 'P', '2017-08-19', '2018-01-01', '', '', '', 'PASIR PUTIH', '', ''),
(911, 'D 1262', 'DEFRIKA RAHAYU', 'P', '2017-08-19', '1998-11-12', '81367527152', '', '', 'SUNGAI GELAM', '', ''),
(912, 'L 0357', 'LENI', 'P', '2017-08-19', '1976-10-30', '81366198888', '', '', 'KERINCI', '', ''),
(913, 'W 0441', 'WIDIA.S', 'P', '2017-08-19', '2018-01-01', '82181455774', '', '', 'SUNGAI BAHAR', '', ''),
(914, 'A 0176', 'ANIDAWATI', 'P', '2017-08-19', '2018-01-01', '81366825967', '', '', 'KERINCI', '', ''),
(915, 'D 1228', 'DINA DWI LISTIANI', 'P', '2017-08-19', '2018-01-01', '82285554623', '', '', 'SUNAN GIRI', '', ''),
(916, 'D 1263', 'DEWI', 'P', '2017-08-22', '2065-06-28', '85266508870', '', '', 'THEHOK', '', ''),
(917, 'L 0925', 'LENI MARLINA', 'P', '2017-08-22', '2018-01-01', '82376267374', '', '', 'SELINCAH', '', ''),
(918, 'R 1642', 'REVA', 'P', '2017-08-22', '1996-06-24', '81290088778', '', '', 'KEBUN HANDIL', '', ''),
(919, 'T 0559', 'TITI', 'P', '2017-08-22', '1995-01-18', '82280053103', '', '', 'KOTA BARU', '', ''),
(920, 'N  0365', 'NANI', 'P', '2017-08-24', '2018-01-01', '85266717008', '', '', 'PAL MERAH', '', ''),
(922, 'T 0451', 'TIURMA MUNTE', 'P', '2017-08-24', '2018-01-01', '82183575301', '', '', 'KOTA BARU', '', ''),
(923, 'R 1605', 'RASHEEDA', 'P', '2017-08-24', '2018-01-01', '82183779672', '', '', 'TANJUNG PINANG', '', ''),
(924, 'E 1132', 'ENI', 'P', '2017-08-24', '2018-01-01', '85266679889', '', '', 'TELANAI PURA', '', ''),
(925, 'L 0905', 'LITA', 'P', '2017-08-24', '2018-01-01', '85269606860', '', '', 'KEBUN HANDIL', '', ''),
(926, 'N 1294', 'NGATIA', 'P', '2017-08-24', '1970-09-07', '85269307631', '', '', 'SIMPANG KAWAT', '', ''),
(927, 'T 0560', 'TINA', 'P', '2017-08-24', '1995-05-13', '85211223541', '', '', 'KASANG', '', ''),
(928, 'T 0038', 'TINA', 'P', '2017-08-24', '2018-01-01', '81994516198', '', '', 'KOTA BARU', '', ''),
(929, 'A 1556', 'ANTONI PANJAITAN', 'P', '2017-08-24', '1985-09-03', '81370896264', '', '', 'SIMPANG RIMBO', '', ''),
(930, 'I 0844', 'IKA', 'P', '2017-08-24', '1989-09-02', '81273994080', '', '', 'GERAGAI TANJAB TIMUR', '', ''),
(931, 'H 0484', 'HANA KINANTI', 'P', '2017-08-26', '2018-01-01', '82181660419', '', '', 'SABAK', '', ''),
(932, 'S 0980', 'SALMITA', 'P', '2017-08-26', '2068-10-28', '85266360584', '', '', 'SUNGAI PENUH', '', ''),
(933, 'B 0045', 'BONITA', 'P', '2017-08-26', '2018-01-01', '85384181234', '', '', 'PASIR PUTIH', '', ''),
(934, 'N 1227', 'NOVITA ERLINDA', 'P', '2017-08-26', '2018-01-01', '81366703377', '', '', 'KOMPLEK PEMBA', '', ''),
(935, 'L 0902', 'LASMA', 'P', '2017-08-26', '2018-01-01', '811742979', '', '', 'KOTA JAMBI', '', ''),
(936, 'S 1567', 'SINTA', 'P', '2017-08-29', '2018-01-01', '82306136249', '', '', 'SIPIN', '', ''),
(937, 'S 0184', 'SUMARLIN', 'P', '2017-08-29', '1975-06-30', '85377833178', '', '', 'RAJAWALI', '', ''),
(938, 'A 1557', 'AULIA', 'P', '2017-08-29', '2018-01-01', '81326941318', '', '', 'TALANG BAKUNG', '', ''),
(939, 'P 0308', 'PARWATI', '', '2017-08-29', '2018-01-01', '85268008082', '', '', 'SIJENJANG', '', ''),
(940, 'A 0821', 'ATIK', 'P', '2017-08-29', '2018-01-01', '82180752000', '', '', 'CEMPAKA PUTIH', '', ''),
(941, 'I 0845', 'INOY', 'P', '2017-08-29', '1981-06-13', '81126442664', '', '', 'SAROLANGUN', '', ''),
(942, 'D 1264', 'DEDDY SURYAJAYA', 'L', '2017-08-29', '2053-02-18', '82183878888', '', '', 'JAMBI TIMUR', '', ''),
(943, 'N 1210', 'NANDA', 'P', '2017-08-29', '2018-01-01', '85367877088', '', '', 'RAJAWALI', '', ''),
(944, 'S 1653', 'SYUFIKAL ADNANDO', 'P', '2017-08-29', '2018-01-01', '81377542328', '', '', 'KUMPEH', '', ''),
(945, 'H 0562', 'HESTI', 'P', '2017-08-29', '1992-04-09', '82375309354', '', '', 'MENDALO', '', ''),
(946, 'R 1064', 'RIA', 'P', '2017-08-29', '2018-01-01', '81994661551', '', '', 'TANJUNG PINANG', '', ''),
(947, 'K 0251', 'KURNIA', 'P', '2017-08-29', '2018-01-01', '8117455699', '', '', 'TELANAI', '', ''),
(948, 'N 1295', 'NOVI', 'P', '2017-08-29', '2018-01-01', '85268244933', '', '', 'KUMPE', '', ''),
(949, 'R 1250', 'RADIKA', 'L', '2017-08-29', '2018-01-01', '82161624236', '', '', 'KASANG', '', ''),
(950, 'D 0756', 'DESI', 'P', '2017-08-29', '2018-01-01', '85266099878', '', '', 'ARIZONA', '', ''),
(951, 'M 1278', 'MARDIANA', 'P', '2017-08-29', '2018-01-01', '85266683023', '', '', 'KUMPE', '', ''),
(952, 'S 1352', 'SUPARTI', 'P', '2017-08-29', '2018-01-01', '85271340269', '', '', 'TELANAI PURA', '', ''),
(953, 'S 1654', 'SARI', 'P', '2017-08-29', '1974-01-05', '82374638020', '', '', 'PASIR PUTIH', '', ''),
(954, 'R 1654', 'RISA FEBRINA', 'P', '2017-08-29', '2001-02-05', '89679156651', '', '', 'MARENE', '', ''),
(955, 'S 1425', 'SUSANTI', 'P', '2017-08-29', '2018-01-01', '85266242226', '', '', 'SAROLANGUN', '', ''),
(956, 'N 1194', 'NURI', 'P', '2017-08-29', '1995-11-09', '81373007971', '', '', 'PASIR PUTIH', '', ''),
(957, 'O 0001', 'ONA', 'P', '2017-09-07', '2018-01-01', '85384043939', '', '', 'TANJUNG PINANG', '', ''),
(958, 'P 324', 'PASKAH LINA', 'P', '2017-08-29', '1982-05-25', '85266939366', '', '', 'NUSA INDAH', '', '');
INSERT INTO `tbmpasien` (`IdPasien`, `KodePasien`, `NamaPasien`, `JenisKelamin`, `TanggalDaftar`, `TanggaLahir`, `NoTelpon`, `Pekerjaan`, `Email`, `Alamat`, `Catatan`, `NoKartu`) VALUES
(959, 'S 0553', 'SITI FATIAH', 'P', '2017-08-29', '2018-01-01', '81274016002', '', '', 'KOTA BARU', '', ''),
(960, 'N 1296', 'NIA', 'P', '2017-08-29', '1988-10-17', '85264881447', '', '', 'KENALI BAWAH', '', ''),
(961, 'P 0317', 'PUTRI', 'P', '2017-08-31', '2018-01-01', '82371456422', '', '', 'KASANG LUAR', '', ''),
(962, 'Z 0124', 'ZAKIAH RAHMAN', 'P', '2017-08-31', '1978-05-14', '82372659345', '', '', 'SIMPANG RIMBO', '', ''),
(963, 'A 1558', 'ASIANG', 'P', '2017-08-31', '2018-01-01', '82371340222', '', '', 'TALANG BANJAR', '', ''),
(964, 'Y 0985', 'YEK SIANG', 'P', '2017-08-31', '2018-01-01', '81274342015', '', '', 'KASANG', '', ''),
(965, 'E 1058', 'EVA', 'P', '2017-08-31', '2018-01-01', '82372714813', '', '', 'SELINCAH', '', ''),
(966, 'S 1655', 'SURYATI', 'P', '2017-08-31', '1974-09-21', '81356779368', '', '', 'BERINGIN', '', ''),
(967, 'N 1297', 'NITA', 'P', '2017-08-31', '2018-01-01', '82280540052', '', '', 'SIMPANG ACAY', '', ''),
(968, 'N 1208', 'NURIL', 'P', '2017-08-31', '2018-01-01', '85266275522', '', '', 'TANJUNG SARI', '', ''),
(969, 'S', 'SITI BANGKO', '', '2017-08-31', '2017-08-31', '', '', '', '', '', ''),
(970, 'L 0969', 'LAFENI', 'P', '2017-09-02', '1995-12-02', '85266155833', '', '', 'JELUTUNG', '', ''),
(971, 'P 0326', 'PEPPY PUTRI', 'P', '2017-09-02', '1992-07-11', '85266155833', '', '', 'JELUTUNG', '', ''),
(972, 'W 0085', 'WELDA', 'P', '2017-09-02', '2018-01-01', '85377147777', '', '', 'TEMPINO', '', ''),
(973, 'N 1262', 'NURSETIANI', 'P', '2017-09-02', '2017-09-02', '8221380742', '', '', 'TAMAN ACI', '', ''),
(974, 'S 1487', 'SRI', 'P', '2017-09-03', '2018-01-01', '85267330125', '', '', 'JERAMBAH BOLONG', '', ''),
(975, 'W 0453', 'WINDY', 'P', '2017-09-03', '2018-01-01', '82384829796', '', '', 'THEHOK', '', ''),
(976, 'A 0003', 'AKMAL', 'P', '2017-09-03', '2018-01-01', '85266770991', '', '', 'THEHOK', '', ''),
(977, 'L 0898', 'LOLI', 'P', '2017-09-03', '2018-01-01', '8,95336E+11', '', '', 'PAKUAN BARU', '', ''),
(978, 'S 1656', 'SUCI', 'P', '2017-09-03', '1996-06-30', '82176908904', '', '', 'KEMINGKING DALAM', '', ''),
(979, 'Y 0855', 'YESSI', 'P', '2017-09-03', '2018-01-01', '82306354393', '', '', 'THEHOK', '', ''),
(980, 'R 1655', 'RARA', 'P', '2017-09-03', '1996-02-13', '85381729913', '', '', 'MAYANG', '', ''),
(981, 'D 1197', 'DILA', 'P', '2017-09-05', '2018-01-01', '89658613496', '', '', 'ARIZONA', '', ''),
(982, 'R 1657', 'RISA', 'P', '2017-09-05', '1991-09-19', '82279988787', '', '', 'THEHOK', '', ''),
(983, 'K 0264', 'KASMARNI', 'P', '2017-09-05', '2018-01-01', '85268501327', '', '', 'MENDALO', '', ''),
(984, 'R 1658', 'RARA', 'P', '2017-09-05', '2004-10-23', '85380391616', '', '', 'THEHOK', '', ''),
(985, 'R 1617', 'ROSNI TANDIO', 'P', '2017-09-05', '2018-01-01', '8127415668', '', '', 'JL.HAYAM WURUK', '', ''),
(986, 'I 0825', 'IRA', 'P', '2017-09-05', '2018-01-01', '85269432072', '', '', 'TANJUNG LUMUT', '', ''),
(987, 'H 0502', 'HARIS (DR)', 'L', '2017-09-05', '2018-01-01', '85266576800', '', '', 'TUNGKAL', '', ''),
(988, 'P 0210', 'PURNAMASARI', 'P', '2017-09-05', '2018-01-01', '81274811212', '', '', 'KEBUN HANDIL', '', ''),
(989, 'Y 0847', 'YOLANDA', 'P', '2017-09-06', '2018-01-01', '85269191023', '', '', 'KOTA BARU', '', ''),
(990, 'M 0114', 'MAYSAH', 'P', '2017-09-06', '2018-01-01', '81366032591', '', '', 'TELANAI PURA', '', ''),
(991, 'N 1298', 'NENG AYU SAADAH', 'P', '2017-09-06', '2018-01-01', '82176786036', '', '', 'MENDALO', '', ''),
(992, 'N 1030', 'NURUL MAILANI', 'P', '2017-09-06', '2018-01-01', '85384808048', '', '', 'MENDALO', '', ''),
(993, 'D 1265', 'DESNELI', 'P', '2017-09-06', '2057-05-03', '85266243798', '', '', 'BLK', '', ''),
(994, 'J 0171', 'JUWITA SARI', 'P', '2017-09-07', '2018-01-01', '85266882768', '', '', 'JELUTUNG', '', ''),
(995, 'S 0006', 'SRI YUNIARTI', 'P', '2017-09-07', '2018-01-01', '85384146162', '', '', 'PAJAJARAN RT 18 KEL TANJUNG SARI', '', ''),
(996, 'N 0001', 'NOVITA', 'P', '2017-09-07', '2018-01-01', '82180064353', '', '', 'MAYANG', '', ''),
(997, 'E 0799', 'EVA', 'P', '2017-09-07', '2018-01-01', '', '', '', 'PENEROKAN', '', ''),
(998, 'V 0002', 'VIA', 'P', '2017-09-07', '2018-01-01', '85268980854', '', '', 'MENDALO', '', ''),
(999, 'R 1370', 'RESTU', 'P', '2017-09-07', '1995-10-20', '82375442233', 'KARYAWAN HONDA', '', '', '', ''),
(1000, 'A 0004', 'ANDREAN', 'L', '2017-09-07', '1995-03-27', '85269845235', '', '', 'JL. HM YUSUF NASRI PRUM GRAHA PELITA NO 12', '', ''),
(1001, 'H 0002', 'HERI', 'L', '2017-09-07', '2018-01-01', '85215868269', '', '', 'SELINCAH', '', ''),
(1002, 'F 0631', 'FITRIASANI', 'P', '2017-09-08', '1995-03-03', '85641929689', '', '', 'SUNGAI GELAM', '', ''),
(1003, 'D 1266', 'DIANA', 'P', '2017-09-08', '2018-01-01', '85377488813', '', '', 'KASANG LUAR', '', ''),
(1004, 'F 0628', 'FIFI', 'P', '2017-09-08', '2018-01-01', '82217376808', '', '', 'KASANG PUDAK', '', ''),
(1005, 'F 0629', 'FITRA', 'P', '2017-09-08', '1986-01-08', '85266800792', '', '', 'SENGETI', '', ''),
(1006, 'D 0005', 'DEWI', 'P', '2017-09-08', '2018-01-01', '85268885924', '', '', 'TALANG BANJAR', '', ''),
(1007, 'F 0630', 'FENI', 'P', '2017-09-08', '2054-02-21', '81273830833', '', '', 'TANJUNG PINANG', '', ''),
(1008, 'Z 0080', 'ZIA', 'P', '2017-09-09', '2018-01-01', '81373481265', '', '', 'BAHAR', '', ''),
(1009, 'D 0006', 'DESMELINDA', 'P', '2017-09-08', '1993-12-01', '82373537037', 'SWASTA', '', 'JELUTUNG', '', ''),
(1010, 'H 0569', 'HENDRA NAWAWI', 'P', '2017-09-09', '2018-01-01', '81377799212', '', '', 'KASANG DALAM', '', ''),
(1011, 'S 1659', 'SUNDARI', 'P', '2017-09-09', '1989-04-09', '85264552955', '', '', 'PERDABAS', '', ''),
(1012, 'R 1659', 'RAHMA', 'P', '2017-09-09', '1985-07-16', '82371720338', '', '', 'PERDABAS', '', ''),
(1013, 'N 1299', 'NAFISA', 'P', '2017-09-09', '2000-10-17', '81274663533', '', '', 'SELINCAH', '', ''),
(1014, 'M 1351', 'MELIANI', 'P', '2017-09-09', '2000-07-30', '85183093787', '', '', 'TANGKIT', '', ''),
(1015, 'M 1350', 'MELANI', 'P', '2017-09-09', '2000-11-11', '85381817835', '', '', 'SELINCAH', '', ''),
(1016, 'D 1102', 'DARMAWATI', 'P', '2017-09-09', '2018-01-01', '81366732294', '', '', 'THEHOK', '', ''),
(1017, 'N 1300', 'NIRMALA', 'P', '2017-09-10', '1989-08-22', '85269379722', '', '', 'MUARA BULIAN', '', ''),
(1018, 'N 1301', 'NITA', 'P', '2017-09-10', '2069-07-02', '82280710055', '', '', 'TELANAI', '', ''),
(1020, 'T 0002', 'TOPIK', 'L', '2017-09-10', '2018-01-01', '', '', '', '', '', ''),
(1021, 'S 1660', 'SELVI', 'P', '2017-09-11', '1980-08-24', '82281547708', '', '', 'BATANG HARI', '', ''),
(1022, 'A 1559', 'AYU', 'P', '2017-09-11', '1993-06-12', '82375018812', '', '', 'JELUTUNG', '', ''),
(1023, 'E 1218', 'ELIYATI', 'P', '2017-09-11', '2068-02-16', '85266926065', '', '', 'SAROLANGUN', '', ''),
(1024, 'S 0089', 'SOFIA', 'P', '2017-09-11', '2018-01-01', '82176549966', '', '', 'TERMINAL BARU', '', ''),
(1025, 'A 0005', 'ADE NOVIA', 'P', '2017-09-11', '2018-01-01', '85266610017', '', '', 'TERMINAL BARU', '', ''),
(1026, 'S 1201', 'SYAKIROH', 'P', '2017-09-11', '2018-01-01', '', '', '', 'PETALING', '', ''),
(1027, 'P 0327', 'PUTRI', 'P', '2017-09-12', '2000-07-30', '9528087858', '', '', 'MARENE', '', ''),
(1028, 'A 0396', 'HASNAH', 'P', '2017-09-12', '1971-05-10', '82376372838', '', '', 'TELANAI', '', ''),
(1029, 'E 1006', 'EVI', 'P', '2017-09-12', '2018-01-01', '85273365824', '', '', 'KASANG', '', ''),
(1030, 'P 0268', 'PUTRI', 'P', '2017-09-12', '1993-12-26', '82333483681', '', '', 'MENDALO', '', ''),
(1031, 'I 0264', 'IKA', 'P', '2017-09-12', '2018-01-01', '85366586672', '', '', 'PATAHILLA', '', ''),
(1032, 'R 1660', 'RUSDI', 'L', '2017-09-14', '1982-05-16', '81366189272', '', '', 'RAJAWALI', '', ''),
(1033, 'P 0328', 'PANDU', 'L', '2017-09-14', '2018-01-01', '85367320815', '', '', 'TELANAI', '', ''),
(1034, 'K 0283', 'KARTIKA DEWI', 'P', '2017-09-14', '1980-07-25', '81369662550', '', '', 'TALANG BANJAR', '', ''),
(1035, 'S 1661', 'SAHAT TOBING', 'L', '2017-09-14', '2018-01-01', '82280154852', '', '', 'JAMBI SELATAN', '', ''),
(1036, 'R 1661', 'REFNITA', 'P', '2017-09-14', '1977-08-18', '82379893233', '', '', 'BUDIMAN', '', ''),
(1037, 'D 0641', 'DIMAS HARDIA', 'L', '2017-09-14', '1989-06-09', '82280101330', '', '', 'THEHOK', '', ''),
(1038, 'L 0869', 'LILIS', 'P', '2017-09-14', '2018-01-01', '85366919000', '', '', 'TANGKIT', '', ''),
(1039, 'M 1122', 'MITA', 'P', '2017-09-14', '2018-01-01', '81383173732', '', '', 'TALANG BAKUNG', '', ''),
(1040, 'H 0571', 'HATNI', 'P', '2017-09-15', '1986-11-07', '82282293375', '', '', 'KUMPEH', '', ''),
(1041, 'S 1662', 'SURIP', 'P', '2017-09-15', '1984-08-15', '82282293375', '', '', 'KUMPEH', '', ''),
(1042, 'N 1302', 'NOVI', 'P', '2017-09-15', '1992-12-24', '82376113891', '', '', 'BERINGIN', '', ''),
(1043, 'N 1190', 'NURUL HASANAH', 'P', '2017-09-15', '2018-01-01', '85269739022', '', '', 'TEBING TINGGI', '', ''),
(1044, 'S 1663', 'SITI MAISARAH', 'P', '2017-09-15', '1997-03-05', '85832304458', '', '', 'KENALI ASAM BAWAH', '', ''),
(1045, 'Y 0986', 'YANTI', 'P', '2017-09-16', '1989-01-28', '85266233660', '', '', 'KONI', '', ''),
(1046, 'N 1304', 'NANDA', 'P', '2017-09-16', '1991-03-22', '85379573575', '', '', 'JERAMBAH BOLONG', '', ''),
(1047, 'S 0785', 'SUMARNI', 'P', '2017-09-16', '2018-01-01', '85266639137', '', '', 'PAAL MERAH', '', ''),
(1048, 'T 0562', 'TIARA', 'P', '2017-09-16', '1999-02-17', '85384548612', '', '', 'MUARO KUMPEH', '', ''),
(1049, 'N 1303', 'NOVA HERZA', 'P', '2017-09-16', '1993-11-11', '82378708389', '', '', 'IBRAHIM', '', ''),
(1050, 'M 0116', 'MELTA', 'P', '2017-09-17', '2018-01-01', '81366777022', '', '', 'KENALI BESAR', '', ''),
(1051, 'T 0408', 'TIKA', 'P', '2017-09-17', '2018-01-01', '82179334204', 'BIDAN', '', 'BAHAR', '', ''),
(1052, 'L 0642', 'LISA', 'P', '2017-09-17', '2018-01-01', '81927572000', '', '', 'PAYO SELINCAH', '', ''),
(1053, 'D 0217', 'DWI', 'P', '2017-09-17', '2018-01-01', '85789000033', '', '', 'TELANAI PURA', '', ''),
(1054, 'N 1167', 'NITA', 'P', '2017-09-17', '2018-01-01', '81278534665', '', '', 'SIMPANG PULE', '', ''),
(1055, 'N 1246', 'NINA', 'P', '2017-09-17', '2018-01-01', '81343331995', '', '', 'EKA JAYA', '', ''),
(1056, 'B 0149', 'BERLIANA', 'P', '2017-09-17', '2018-01-01', '85266299015', '', '', 'KASANG', '', ''),
(1057, 'I 0794', 'IRMA NASUTION', 'P', '2017-09-18', '2018-01-01', '82282003541', '', '', 'SUNGAI TAMAN', '', ''),
(1058, 'V 0224', 'VENI', 'P', '2017-09-18', '2018-01-01', '85269758957', '', '', 'THEHOK', '', ''),
(1059, 'C 0180', 'CINDY', 'P', '2017-09-18', '2001-04-02', '82375927327', '', '', 'PERSIJAM', '', ''),
(1060, 'M 1352', 'MIRA', 'P', '2017-09-19', '1974-01-09', '81272609473', '', '', 'TALANG BANJAR', '', ''),
(1061, 'G 0106', 'GUSMAWATI', 'P', '2017-09-19', '1972-08-12', '85380704774', '', '', 'STM ATAS', '', ''),
(1062, 'S 1664', 'SITI MARJADI', 'P', '2017-09-19', '1980-05-22', '85266829514', '', '', 'SEBERANG', '', ''),
(1063, 'Y 0987', 'YULI', 'P', '2017-09-19', '2018-01-01', '81367490554', '', '', 'SELINCAH', '', ''),
(1064, 'R 1662', 'RAHMI HIDAYATI', 'P', '2017-09-19', '2067-05-25', '81366017702', '', '', 'MAYANG', '', ''),
(1065, 'M 1353', 'MUTIARA', 'P', '2017-09-21', '2018-01-01', '82280469220', '', '', 'PASIR PUTIH', '', ''),
(1066, 'S 0583', 'SUZIE', 'P', '2017-09-21', '2018-01-01', '81274708090', '', '', 'KARYA MAJU', '', ''),
(1067, 'M 0002', 'MISLASMI', 'P', '2017-09-21', '2018-01-01', '81274708090', '', '', 'SIPIN', '', ''),
(1068, 'D 1267', 'DEMUNTE', 'P', '2017-09-21', '1981-07-04', '81368396379', '', '', 'PAL MERAH', '', ''),
(1069, 'T 0563', 'TIOLINA', 'P', '2017-09-21', '1978-11-21', '81368396379', '', '', 'PAL MERAH', '', ''),
(1070, 'N 0621', 'NARNI', 'P', '2017-09-21', '2018-01-01', '82180743545', '', '', 'TANJUNG SARI', '', ''),
(1071, 'S 1550', 'SUNI', 'P', '2017-09-21', '2018-01-01', '85368645969', '', '', 'SIPIN', '', ''),
(1072, 'S 0655', 'SITI M', 'P', '2017-09-21', '2018-01-01', '81366599736', '', '', 'SUNGAI GELAM', '', ''),
(1073, 'Z 0116', 'ZUKMARINI', 'P', '2017-09-21', '2018-01-01', '85272004036', '', '', 'MAYANG', '', ''),
(1074, 'E 0789', 'ELSA', 'P', '2017-09-21', '2018-01-01', '81927563126', '', '', 'PASAR', '', ''),
(1075, 'K 0124', 'KOMARIAH', 'P', '2017-09-21', '2018-01-01', '85268385357', '', '', 'BAYUNG LINCIR', '', ''),
(1076, 'E 0891', 'ELIS', 'P', '2017-09-21', '2018-01-01', '8,23306E+11', '', '', 'BAYUNG LINCIR', '', ''),
(1077, 'Y 0988', 'YULI RAHAYU', 'P', '2017-09-22', '1981-03-13', '85266002000', '', '', 'MAYANG', '', ''),
(1078, 'R 1319', 'RENNY NOVISKA', 'P', '2017-09-22', '2018-01-01', '85266389411', '', '', 'SERSAN DARPIN', '', ''),
(1079, 'S 1335', 'SHERLY', 'P', '2017-09-22', '2018-01-01', '81241103806', '', '', 'TELANAI PURA', '', ''),
(1080, 'T 0346', 'TIA', 'P', '2017-09-22', '2018-01-01', '81366808978', '', '', 'BRONI', '', ''),
(1081, 'S 1665', 'SODRIA', 'P', '2017-09-23', '1975-04-04', '82247880200', '', '', 'MENDALO', '', ''),
(1082, 'E 0863', 'EKA SUKMA', 'P', '2017-09-23', '2018-01-01', '85266527871', '', '', 'MUARA BULIAN', '', ''),
(1083, 'Y 0978', 'YULIA', 'P', '2017-09-23', '2018-01-01', '85382201887', '', '', 'KOTA BARU', '', ''),
(1084, 'N 0963', 'NUR CAHAYA', 'P', '2017-09-23', '2018-01-01', '81366098100', '', '', 'TELANAI', '', ''),
(1085, 'Y 0447', 'YULIANA', 'P', '2017-09-24', '2018-01-01', '87896978822', '', '', 'PAKUAN BARU', '', ''),
(1086, 'L 0853', 'LIA JAMIL', 'P', '2017-09-24', '2018-01-01', '81274024456', '', '', 'NUSA INDAH 1', '', ''),
(1087, 'S 1666', 'SARAH', 'P', '2017-09-24', '1988-12-31', '89512054448', '', '', 'MUHIBAH', '', ''),
(1088, 'I 0846', 'IRMAWATI', 'P', '2017-09-24', '1974-02-02', '81380031270', '', '', 'BAYUNG LINCIR', '', ''),
(1089, 'A 1560', 'ARLINA', 'P', '2017-09-24', '1977-06-06', '81380031270', '', '', 'BAYUNG LINCIR', '', ''),
(1090, 'K 0244', 'KARMILA', 'P', '2017-09-24', '2068-04-06', '81274949861', '', '', 'JERAMBAH BOLONG', '', ''),
(1091, 'D 1268', 'DANIEL LG', 'L', '2017-09-24', '2002-07-15', '81212013307', '', '', 'KEBUN KOPI', '', ''),
(1092, 'Y 0643', 'YULI', 'P', '2017-09-25', '2018-01-01', '82126462302', '', '', 'TALANG BANJAR', '', ''),
(1093, 'D 1270', 'DEWI', 'P', '2017-09-25', '1992-08-08', '81368296665', '', '', 'TALANG BAKUNG', '', ''),
(1094, 'N 1305', 'NAFIZ', 'P', '2017-09-25', '1982-08-21', '82282761823', '', '', 'MERANGIN', '', ''),
(1095, 'K 0284', 'KHURYATI HASANAH', 'P', '2017-09-25', '1979-11-04', '82377377179', '', '', 'MERANGIN', '', ''),
(1096, 'A 1561', 'ANGGUN', 'P', '2017-09-25', '1986-12-26', '82377288026', '', '', 'TELANAI', '', ''),
(1097, 'N 1174', 'NURHIDAYATI', 'P', '2017-09-25', '2018-01-01', '85366580666', '', '', 'MENDIANGIN', '', ''),
(1098, 'D  1256', 'DAVID', 'L', '2017-09-27', '1977-01-31', '85366668666', '', '', 'KEBUN HANDIL', '', ''),
(1099, 'Y 0989', 'YOLA', 'P', '2017-09-27', '2002-08-03', '81269775728', '', '', 'THEHOK', '', ''),
(1100, 'M 0430', 'MARIATI', 'P', '2017-09-27', '2018-01-01', '85366422133', '', '', 'NUSA INDAH', '', ''),
(1101, 'M 1354', 'MERI', 'P', '2017-09-27', '2066-11-27', '85368566668', '', '', 'SELINCAH', '', ''),
(1102, 'N 1306', 'NUR AINI', 'P', '2017-09-27', '1996-11-24', '81367780304', '', '', 'TANGKIT', '', ''),
(1103, 'W 0474', 'WAHYU', 'P', '2017-09-27', '1996-02-16', '82185931960', '', '', 'BRONI', '', ''),
(1104, 'E 1220', 'ELA', 'P', '2017-09-27', '1993-12-02', '89632001987', '', '', 'TALANG BANJAR', '', ''),
(1105, 'L 0914', 'LINDA INDRIATI', 'P', '2017-09-27', '2018-01-01', '85268421888', '', '', 'PATTIMURA', '', ''),
(1106, 'T 0449', 'TRI ULANDARI', 'L', '2017-09-27', '2018-01-01', '89624427308', '', '', 'JERAMBAH BOLONG', '', ''),
(1107, 'E 1219', 'ETI HUSNA', 'P', '2017-09-27', '1980-08-22', '82262229772', '', '', 'SEBRANG', '', ''),
(1108, 'A 1447', 'ANI', 'P', '2017-09-27', '2018-01-01', '81994666268', '', '', 'GRAHA 16', '', ''),
(1109, 'D 1252', 'DEWI GITA WARSA', 'P', '2017-09-27', '1995-12-16', '82311422478', '', '', 'EKA JAYA', '', ''),
(1110, 'I 0847', 'INDRI', 'P', '2017-09-27', '2018-01-01', '82376958828', '', '', 'SEBRANG', '', ''),
(1111, 'D 1271', 'DEWI KARTIKA', 'P', '2017-09-27', '2018-01-01', '81272930545', '', '', 'PEMATANG SULUR', '', ''),
(1112, 'L 0929', 'LINDRAYANI', 'P', '2017-09-28', '2018-01-01', '81366777522', '', '', 'TELANAI', '', ''),
(1113, 'M 1356', 'MELANI SARILIM', 'P', '2017-09-28', '2062-12-22', '85266091179', '', '', 'KONI', '', ''),
(1114, 'M 1355', 'MURSIDAH', 'P', '2017-09-28', '2068-06-11', '85266064395', '', '', 'SIPIN', '', ''),
(1115, 'M 1240', 'MILA', 'P', '2017-09-28', '2018-01-01', '82377904394', '', '', '16 TAHUN', '', ''),
(1116, 'S 1667', 'SOLEKA', 'P', '2017-09-28', '1980-08-04', '81274292333', '', '', 'MERLUNG', '', ''),
(1117, 'N 1245', 'NITA', 'P', '2017-09-28', '2018-01-01', '89629575079', '', '', 'JERAMBAH BOLONG', '', ''),
(1118, 'I 0796', 'INA KERNARUTA', 'P', '2017-09-29', '2018-01-01', '82181691089', '', '', 'BULURAN', '', ''),
(1119, 'N 0897', 'NANA', 'P', '2017-09-29', '1979-07-20', '85381702000', '', '', 'VILLA KENALI', '', ''),
(1120, 'D 1272', 'DINA', 'P', '2017-09-29', '1989-08-06', '81373287227', '', '', 'SENGETI', '', ''),
(1121, 'T 0540', 'TRIA', 'P', '2017-09-29', '2018-01-01', '85269813238', '', '', 'TELANAI', '', ''),
(1122, 'N 1307', 'NUR AINI SARI', 'P', '2017-09-29', '1982-06-19', '85320342497', '', '', 'KASANG', '', ''),
(1123, 'D 1221', 'DEWI GIRSANG', 'P', '2017-09-29', '2018-01-01', '8121818308', '', '', 'PAL 5', '', ''),
(1124, 'I 0838', 'IDA', 'P', '2017-09-29', '1979-05-02', '82307474893', '', '', 'KASANG', '', ''),
(1125, 'W 0379', 'WULANDARI', 'P', '2017-10-02', '1988-12-05', '85377379331', '', '', 'MUARA BULIAN', '', ''),
(1126, 'A 0006', 'ANGGA', 'L', '2017-10-02', '2018-01-01', '85381585858', '', '', '', '', ''),
(1127, 'S 1668', 'SRI RAHAYU', 'P', '2017-10-02', '1983-06-12', '81274789963', '', '', 'KUMPEH', '', ''),
(1128, 'P 0330', 'PUSPITA SARI', 'P', '2017-10-02', '1994-09-21', '85218797901', '', '', 'KASANG PUDAK', '', ''),
(1129, 'A 0007', 'AULIA', 'P', '2017-10-02', '2018-01-01', '85366302007', '', '', 'TALANG BANJAR', '', ''),
(1130, 'N 1309', 'NUR HASANAH', 'P', '2017-10-02', '1998-04-27', '82375351097', '', '', 'TALANG BAKUNG', '', ''),
(1131, 'S 1120', 'SRI MARYANI', 'P', '2017-10-02', '2018-01-01', '82183794243', '', '', 'SIMPANG KAWAT', '', ''),
(1132, 'D 1124', 'DINA', 'P', '2017-10-02', '2018-01-01', '82182211115', '', '', 'KASANG', '', ''),
(1133, 'A 1562', 'ANGGUN TIARA', 'P', '2017-10-02', '1997-08-03', '85273923280', '', '', 'KASANG PUDAK', '', ''),
(1134, 'A 1497', 'ALTATI PANJAITAN', 'P', '2017-10-02', '2018-01-01', '81397765568', '', '', 'SUNGAI RENGAS', '', ''),
(1135, 'P 0329', 'PUTRI ARFIANA', 'P', '2017-10-02', '2000-09-19', '89633664243', '', '', 'KEBUN KOPI', '', ''),
(1136, 'E 1221', 'EMA', 'P', '2017-10-02', '2018-01-01', '85382641134', '', '', 'MUARA BULIAN', '', ''),
(1137, 'M 1201', 'MURNI', 'P', '2017-10-02', '2018-01-01', '', '', '', 'NIPAH PANJANG', '', ''),
(1138, 'N 1182', 'NURUL IKWAN', 'P', '2017-10-02', '2018-01-01', '89508513464', '', '', 'TELANAI', '', ''),
(1139, 'Y 0990', 'YULI', 'P', '2017-10-02', '1999-08-23', '81379814715', '', '', 'KUMPEH', '', ''),
(1140, 'M 1357', 'MELANIA', 'P', '2017-10-02', '1984-03-22', '81274067474', '', '', 'MERLUNG', '', ''),
(1141, 'N 1308', 'NETI YULFA', 'P', '2017-10-02', '1990-01-07', '82372711990', '', '', 'BATANG HARI', '', ''),
(1142, 'H 0201', 'HAFIFAH', '', '2017-10-02', '2017-10-02', '', '', '', '', '', ''),
(1143, 'H 0572', 'HILA LIANI', 'P', '2017-10-02', '1999-09-08', '82372224952', '', '', 'SUNGAI DUREN', '', ''),
(1144, 'P 0232', 'PARSINI', 'P', '2017-10-02', '2018-01-01', '82378023322', '', '', 'PUDAK', '', ''),
(1145, 'M 1358', 'MIDA', 'P', '2017-10-02', '1992-06-26', '85266003898', '', '', 'KOTA BARU', '', ''),
(1146, 'Y 0991', 'YUNI PUSPITA', 'P', '2017-10-02', '1994-06-22', '85267201223', '', '', 'MUARA BULIAN', '', ''),
(1147, 'N  1310', 'NOVIA SUSYANTI', 'P', '2017-10-03', '1977-11-10', '81274511108', '', '', 'PASIR PUTIH', '', ''),
(1148, 'P 0332', 'PUTRI', 'P', '2017-10-03', '1991-07-22', '85367695637', '', '', 'SELINCAH', '', ''),
(1149, 'H 0492', 'HARTIN', 'P', '2017-10-03', '2018-01-01', '85381856160', '', '', 'KOTA BARU', '', ''),
(1150, 'S 1669', 'SRI HARTINI', 'P', '2017-10-03', '1972-09-03', '81274776027', '', '', 'PAL MERAH', '', ''),
(1151, 'R 1656', 'RAUDAH', 'P', '2017-10-03', '1986-06-19', '85270964441', '', '', 'MAYANG', '', ''),
(1152, 'F 0632', 'FITRI', 'P', '2017-10-03', '1981-08-05', '85267498377', '', '', 'MAYANG', '', ''),
(1153, 'R 1215', 'RAISA AINI', 'P', '2017-10-03', '2018-01-01', '8982357276', '', '', 'EKA JAYA', '', ''),
(1154, 'W 0255', 'WIWIK', 'P', '2017-10-03', '1983-12-28', '85380437392', '', '', 'EKA JAYA', '', ''),
(1155, 'F 0001', 'FITRI', 'P', '2017-10-03', '2018-01-01', '85266786980', 'PERAWAT', '', 'KASANG', '', ''),
(1156, 'D 1273', 'DANIL', 'L', '2017-10-03', '1996-10-17', '82372315570', '', '', 'MENDALO', '', ''),
(1157, 'A 1564', 'ALYA', 'P', '2017-10-04', '1973-07-12', '81366665948', '', '', 'TANJUNG PINANG', '', ''),
(1158, 'A 1563', 'AINI', 'P', '2017-10-04', '1976-08-22', '82372268722', '', '', 'TANJUNG PINANG', '', ''),
(1159, 'D 1274', 'DEWI', 'P', '2017-10-04', '2063-10-03', '8117440063', '', '', 'KEBUN KOPI', '', ''),
(1160, 'F 0633', 'FITRI', 'P', '2017-10-04', '1990-04-14', '85295149508', '', '', 'TALANG BAKUNG', '', ''),
(1161, 'T 0564', 'TRI SEPTIA LESTARI', 'P', '2017-10-04', '1998-09-29', '89697154343', '', '', 'MENDALO', '', ''),
(1162, 'N 0980', 'NURHAYATI', 'P', '2017-10-05', '1972-05-02', '81367502465', '', '', 'SWASTA', '', ''),
(1163, 'R 1411', 'ROSMAINI', 'P', '2017-10-06', '2018-01-01', '85266353124', '', '', 'TALANG BAKUNG', '', ''),
(1164, 'R 1615', 'RETNO', 'P', '2017-10-06', '2018-01-01', '82176241181', '', '', 'KEBUN HANDIL', '', ''),
(1165, 'M 1359', 'MELI', 'P', '2017-10-06', '1972-06-17', '85378939054', '', '', 'TANJUNG SARI', '', ''),
(1166, 'F 0569', 'FADIL', 'L', '2017-10-10', '2018-01-01', '85383847992', '', '', 'SELINCAH', '', ''),
(1167, 'G 0107', 'GUSTINA', 'P', '2017-10-10', '1994-08-02', '85357755492', '', '', 'TANJAB TIMUR', '', ''),
(1168, 'Y 0992', 'YAUMIL', 'P', '2017-10-10', '1987-08-09', '85263310211', '', '', 'KOPRAL RAMLI', '', ''),
(1169, 'M 1361', 'MARLINA WATI', 'P', '2017-10-10', '1984-12-17', '85384174284', 'PNS', '', 'KASANG', '', ''),
(1171, 'H 0', '', '', '2017-10-10', '2017-10-10', '', '', '', '', '', ''),
(1172, 'H 0427', 'HARDANELI', 'P', '2017-10-10', '2018-01-01', '85368916155', '', '', 'SABAK', '', ''),
(1173, 'C 0181', 'CIA', 'P', '2017-10-10', '1988-03-28', '85266512828', '', '', 'TEBO', '', ''),
(1174, 'N 1311', 'NURHASIBAH', 'P', '2017-10-10', '1983-02-08', '87793495915', '', '', 'BATANG HARI', '', ''),
(1175, 'N 0982', 'NOVRITA', 'P', '2017-10-10', '2018-01-01', '85266653718', '', '', 'AUDURI', '', ''),
(1176, 'J 0246', 'JAMILAH', 'P', '2017-10-10', '1990-03-20', '85378902229', '', '', 'KASANG PUDAK', '', ''),
(1177, 'I 0546', 'ISTI', 'P', '2017-10-10', '2018-01-01', '81366607130', '', '', 'JERAMBAH BOLONG', '', ''),
(1178, 'U 0239', 'ULI', 'P', '2017-10-10', '2018-01-01', '85378515177', '', '', 'KASANG', '', ''),
(1179, 'M 1360', 'MERY AYU AGUSTIN', 'P', '2017-10-10', '2063-11-26', '82177130808', '', '', 'SIMPANG PULAI', '', ''),
(1180, 'S 1657', 'SISWANITA', 'P', '2017-10-10', '2062-06-18', '81366791761', '', '', 'KASANG', '', ''),
(1181, 'S 1560', 'SERLY', 'P', '2017-10-10', '2018-01-01', '', '82372347988', '', 'KOTA BARU', '', ''),
(1182, 'R 0160', 'RANI', 'P', '2017-10-10', '1980-06-01', '85269870270', '', '', 'KASANG', '', ''),
(1183, 'D 1275', 'DIMAS ALVIANDI', 'P', '2017-10-10', '2001-01-06', '81294343735', '', '', 'MARENE', '', ''),
(1184, 'O 0111', 'OKKA', 'P', '2017-10-10', '1981-10-12', '85266470705', '', '', 'KASANG', '', ''),
(1185, 'N 1312', 'NURI HANDAYANI', 'P', '2017-10-10', '1987-11-05', '8217525395', '', '', 'TANGKIT', '', ''),
(1186, 'S 1671', 'SISKA', 'P', '2017-10-10', '1982-04-15', '85378888910', '', '', 'PASIR PUTIH', '', ''),
(1187, 'E 1222', 'ELSA', 'P', '2017-10-10', '1975-01-06', '85239674333', '', '', 'SIMPANG RIMBO', '', ''),
(1188, 'Y 0935', 'YULI', 'P', '2017-10-10', '2018-01-01', '85343966828', '', '', 'MUARA BULIAN', '', ''),
(1189, 'F 0634', 'FAHRIDA', 'P', '2017-10-10', '1998-08-06', '8,9535E+11', '', '', 'THEHOK', '', ''),
(1190, 'A1457', 'ASPURYANI', 'P', '2017-10-10', '2018-01-01', '85267288904', '', '', 'PATIMURA', '', ''),
(1191, 'R 1663', 'RIHAB', 'P', '2017-10-10', '2018-01-01', '85383350444', '', '', 'SEBRANG', '', ''),
(1192, 'S 1670', 'SUCI CINTIA DEWI', 'P', '2017-10-10', '2018-01-01', '89685299291', '', '', 'PASIR PUTIH', '', ''),
(1193, 'M 1615', 'RETNO OKTARIN', 'P', '2017-10-10', '2018-01-01', '82176241181', '', '', 'KEBUN HANDIL', '', ''),
(1194, 'L 0983', 'LELI', 'P', '2017-10-11', '1992-02-18', '85279438676', '', '', 'KEBUN HANDIL', '', ''),
(1195, 'A 1430', 'ADAWIYAH', 'P', '2017-10-14', '2018-01-01', '82180017584', '', '', 'KUMPEH', '', ''),
(1196, 'Y 0993', 'YESI ARISKA', 'P', '2017-10-14', '1994-08-28', '89607078495', '', '', 'KASANG LUAR', '', ''),
(1197, 'A 1375', 'ASNAWATI', 'P', '2017-10-14', '2018-01-01', '85381063098', '', '', 'SUNGAI KAMBANG', '', ''),
(1198, 'H 0554', 'HENI MELA', 'P', '2017-10-14', '2018-01-01', '85369698064', '', '', 'MENDALO', '', ''),
(1199, 'I 0848', 'INA', 'P', '2017-10-14', '1972-03-05', '81274050570', '', '', 'KOTA BARU', '', ''),
(1200, 'C 0182', 'CICI', 'P', '2017-10-14', '1995-09-29', '89527566610', '', '', 'KOTA BARU', '', ''),
(1201, 'I 0849', 'ITA', 'P', '2017-10-14', '1989-10-14', '85271020565', '', '', 'TANJUNG PINANG ', '', ''),
(1202, 'M 0201', 'MURNI', 'P', '2017-10-14', '2018-01-01', '', '', '', 'NIPAH PANJANG', '', ''),
(1203, 'N 1244', 'NABILA', 'P', '2017-10-16', '2018-01-01', '85269733763', '', '', 'JERAMBAH BOLONG', '', ''),
(1204, 'S 0751', 'SITI ROPIAH', 'P', '2017-10-16', '1981-07-15', '85384229770', '', '', 'GERAGAI', '', ''),
(1205, 'A 1566', 'AMINAH', 'P', '2017-10-16', '2018-01-01', '8127879434', '', '', 'BULIAN', '', ''),
(1206, 'R 1538', 'ROMA', 'P', '2017-10-16', '2018-01-01', '81317766163', '', '', 'TEBING TINGI', '', ''),
(1207, 'S 1674', 'SITI FATIMAH', 'P', '2017-10-16', '1984-11-09', '82306150303', '', '', 'BAYUNG LINCIR', '', ''),
(1208, 'T 0421', 'TIURLAN SIRAIT', 'P', '2017-10-16', '2018-01-01', '82175253825', '', '', 'KUMPEH ULU', '', ''),
(1209, 'D 0264', 'DINA', 'P', '2017-10-16', '2018-01-01', '85382112082', '', '', 'BELAKANG DKT', '', ''),
(1210, 'N 1313', 'NYOMAN SUJANI', 'P', '2017-10-16', '2068-12-13', '85764565233', '', '', 'EKA JAYA', '', ''),
(1211, 'F 0635', 'FITRI', 'P', '2017-10-16', '1998-10-15', '82278191549', '', '', 'BAYUNG LINCIR', '', ''),
(1212, 'A 1565', 'ALDI', 'P', '2017-10-16', '1995-04-12', '82278191549', '', '', 'BAYUNG LINCIR', '', ''),
(1213, 'I 0850', 'IFA', 'P', '2017-10-16', '1997-04-08', '82372557197', '', '', 'SIMPANG RIMBO', '', ''),
(1214, 'S 1122', 'SUCI', 'P', '2017-10-16', '2018-01-01', '85266890747', '', '', 'BAYUNG LINCIR', '', ''),
(1215, 'S 1673', 'SARINAH SEMBIRING', 'P', '2017-10-16', '2018-01-01', '85269783320', '', '', 'SIMPANG RIMBO', '', ''),
(1216, 'M 1313', 'MANJA', 'P', '2017-10-16', '2018-01-01', '85268642624', '', '', 'TALANG BAKUNG', '', ''),
(1217, 'E 1223', 'EKA LARASWATI', 'P', '2017-10-19', '2018-01-01', '85268570007', '', '', 'CEMPAKA PUTIH', '', ''),
(1219, 'S 1677', 'SANTI', 'P', '2017-10-19', '2018-01-01', '81919100968', '', '', 'KUNINGAN TANJUNG PINANG', '', ''),
(1220, 'R 1665', 'RINI', 'P', '2017-10-19', '2018-01-01', '82376212121', '', '', 'THEHOK', '', ''),
(1221, 'O 0112', 'OLIVIA NINDY KARTIKA', 'P', '2017-10-19', '1999-05-28', '81294255449', '', '', 'PERUMNAS AUDURI', '', ''),
(1222, 'M 1363', 'MELIA', 'P', '2017-10-19', '1976-11-28', '82376167629', '', '', 'SIMPANG PULAI', '', ''),
(1223, 'Y 0631', 'YUNI', 'P', '2017-10-19', '2018-01-01', '82176474662', '', '', 'BAHAR', '', ''),
(1224, 'S 1676', 'SARI', 'P', '2017-10-19', '1984-06-17', '82373762070', '', '', 'TANGKIT', '', ''),
(1225, 'T 0565', 'TETTY LINA', 'P', '2017-10-19', '1970-11-01', '82175978283', '', '', 'KASANG PUDAK\'', '', ''),
(1226, 'N 1314', 'NAYYA', 'P', '2017-10-19', '1988-11-17', '85273950894', '', '', 'KASANG PUDAK', '', ''),
(1227, 'S 1675', 'SRI', 'P', '2017-10-19', '1981-05-02', '82174280886', '', '', 'KASANG PUDAK', '', ''),
(1228, 'S 1636', 'SALMA SRI WAHYUNI', 'P', '2017-10-19', '2018-01-01', '85267518323', '', '', 'SELINCAH', '', ''),
(1229, 'M 1362', 'MEGAWATI', 'P', '2017-10-19', '2064-06-24', '81366588590', '', '', 'PATTIMURA', '', ''),
(1230, 'D 1276', 'DESY', 'P', '2017-10-19', '1984-12-01', '85273928569', '', '', 'TELANAI', '', ''),
(1231, 'L 0971', 'LIUS', 'L', '2017-10-19', '1990-09-03', '85378619559', '', '', 'CEMPAKA PUTIH', '', ''),
(1232, 'S 1678', 'SALAMAH', 'P', '2017-10-19', '2018-01-01', '85366671431', '', '', 'CEMPAKA PUTIH', '', ''),
(1233, 'N 0964', 'NIA', 'P', '2017-10-23', '2018-01-01', '82280457880', '', '', 'CEMPAKA PUTIH', '', ''),
(1234, 'A 1210', 'ANNISA', 'P', '2017-10-23', '1999-04-25', '89623626777', '', '', 'JERAMBAH BOLONG', '', ''),
(1235, 'Y 0234', 'YENI', 'P', '2017-10-23', '2018-01-01', '', '', '', 'MENDALO', '', ''),
(1236, 'N 1318', 'NETI HERAWATI', 'P', '2017-10-23', '2066-10-22', '85380594954', '', '', 'JERAMBAH BOLONG', '', ''),
(1237, 'E 1174', 'EKA', 'P', '2017-10-23', '2018-01-01', '811740679', '', '', 'BAMGKO', '', ''),
(1238, 'N 0981', 'NETIN', 'P', '2017-10-23', '2018-01-01', '81367578232', '', '', 'PASIR PUTIH', '', ''),
(1239, 'I 0851', 'IMA RAHMI', 'P', '2017-10-23', '1982-06-20', '85368157600', '', '', 'SAROLANGUN', '', ''),
(1240, 'J 0005', 'JULIATI', 'P', '2017-10-23', '2018-01-01', '8127479801', '', '', 'PAL MERAH LAMA', '', ''),
(1241, 'E 1224', 'EBAY', 'P', '2017-10-23', '1992-02-01', '82372710290', '', '', 'KENALI', '', ''),
(1242, 'R 1522', 'RIKA', 'P', '2017-10-23', '2018-01-01', '85377731334', '', '', 'KOBAR', '', ''),
(1243, 'N 1317', 'NOVRI NAURA', 'P', '2017-10-23', '1986-11-18', '89624703205', '', '', 'SIMPANG RIMBO', '', ''),
(1244, 'A 1569', 'AMALIA', 'P', '2017-10-23', '2018-01-01', '82292635585', '', '', 'SUNGAI BAHAR', '', ''),
(1245, 'U 0250', 'ULFA', 'P', '2017-10-23', '1996-08-07', '85219529733', '', '', 'PERSIJAM', '', ''),
(1246, 'N 1316', 'NUR', 'P', '2017-10-23', '2055-08-27', '81366616262', '', '', 'KERINCI', '', ''),
(1247, 'F 0636', 'FITRI', 'P', '2017-10-23', '1988-10-10', '82176003818', '', '', 'SELINCAH', '', ''),
(1248, 'A 1568', 'ARDILA SAPUTRI', 'P', '2017-10-23', '1999-05-07', '81366705011', '', '', 'TELANAI', '', ''),
(1249, 'C 0142', 'COY', 'L', '2017-10-23', '1979-03-28', '85266157080', '', '', 'SELINCAH', '', ''),
(1250, 'R 1666', 'RIKA', 'P', '2017-10-23', '1981-11-22', '85265619094', '', '', 'BRONI', '', ''),
(1251, 'D 1277', 'DWI JUNIATI', 'P', '2017-10-23', '1995-06-24', '82279647233', '', '', 'PAL 10', '', ''),
(1252, 'L 0926', 'LAILA ', 'P', '2017-10-23', '2018-01-01', '81274336100', '', '', 'SEBERANG', '', ''),
(1254, 'R 1667', 'RISKI WULANDARI', 'P', '2017-10-25', '1996-10-03', '82211506791', '', '', 'SENGETI', '', ''),
(1255, 'N 1320', 'NOVI', 'P', '2017-10-25', '1991-04-06', '85266666582', '', '', '16', '', ''),
(1256, 'D 1193', 'DEWI', 'P', '2017-10-25', '2018-01-01', '82181357718', '', '', 'KASANG', '', ''),
(1257, 'N 1060', 'NENG MUTIAH', 'P', '2017-10-26', '2018-01-01', '82176011033', '', '', 'BELAKANG DKT', '', ''),
(1258, 'S 0994', 'SUMIATI', 'P', '2017-10-26', '2018-01-01', '85266604224', '', '', 'TANJUNG PINANG', '', ''),
(1259, 'R 1162', 'RIZKY PUSPA', 'P', '2017-10-26', '2018-01-01', '81994440179', '', '', 'PAYO SELINCAH', '', ''),
(1260, 'A 1572', 'ANGGUN', 'P', '2017-10-26', '2018-01-01', '8117422237', '', '', 'JL FALISONG', '', ''),
(1261, 'I 0852', 'ITRA HAWANI', 'P', '2017-10-26', '1983-07-13', '85213289747', '', '', 'MAYANG', '', ''),
(1262, 'P 0263', 'PUTRI', 'P', '2017-10-26', '1994-12-24', '87745357998', '', '', 'KARYA MAJU', '', ''),
(1263, 'S 0432', 'SERLY BIDARIA', 'P', '2017-10-26', '2018-01-01', '85764253994', '', '', 'STM ATAS', '', ''),
(1264, 'A 1571', 'ANGGUN SARTIKA', 'P', '2017-10-26', '2009-04-29', '81287783257', '', '', 'MENDALO', '', ''),
(1265, 'N 1319', 'NUR', 'P', '2017-10-26', '1975-04-11', '85381740000', '', '', 'KEBUN HANDIL', '', ''),
(1266, 'A 1570', 'ADLY', 'L', '2017-10-26', '2001-02-25', '85385853377', '', '', 'BERINGIN', '', ''),
(1267, 'H 0557', 'HELEN', 'P', '2017-10-26', '2018-01-01', '81366138899', '', '', 'JELUTUNG', '', ''),
(1268, 'R 1429', 'RONIATI', 'P', '2017-10-30', '2018-01-01', '81274887755', '', '', 'RAJAWALI', '', ''),
(1269, 'V 0073', 'VERA', 'P', '2017-10-30', '2018-01-01', '81366064809', '', '', 'THEHOK', '', ''),
(1270, 'E 1226', 'ENDANG', 'P', '2017-10-30', '2018-01-01', '85266936117', '', '', 'TANJUNG PERMATA', '', ''),
(1271, 'E 1227', 'EGA OKTAVIA', 'P', '2017-10-30', '2018-01-01', '8,95351E+11', '', '', 'TANJUNG PERMATA', '', ''),
(1272, 'R 1523', 'RIA', 'P', '2017-10-30', '2018-01-01', '82269183388', '', '', 'KENALI ATAS', '', ''),
(1273, 'R 1480', 'RITA SUSANTI', 'P', '2017-10-30', '2018-01-01', '81272646404', '', '', 'KUMPE', '', ''),
(1274, 'N 1321', 'NUR FITRI', 'P', '2017-10-30', '1988-04-05', '81272930303', '', '', 'TALANG BANJAR', '', ''),
(1275, 'R 1669', 'RAHMAWATI', 'P', '2017-10-30', '1980-06-12', '81273479997', '', '', 'TALANG BANJAR', '', ''),
(1276, 'L 0911', 'LASMINI', 'P', '2017-10-30', '2018-01-01', '82377276336', '', '', 'RAJAWALI', '', ''),
(1277, 'D 1278', 'DEVI', 'P', '2017-10-30', '1975-02-11', '811749248', '', '', 'KASANG', '', ''),
(1278, 'R 1668', 'RISMAWATI', 'P', '2017-10-30', '7960-12-20', '82379972288', '', '', 'TALANG BANJAR', '', ''),
(1279, 'M 0705', 'MEGA WATI', 'P', '2017-10-30', '2018-01-01', '82175245555', '', '', 'MAYANG', '', ''),
(1280, 'M 1171', 'MIAINA', 'P', '2017-10-30', '2018-01-01', '81366507899', '', '', 'BUDIMAN', '', ''),
(1281, 'R 1447', 'RIKA', 'P', '2017-10-30', '2018-01-01', '', '', '', 'SELINCAH', '', ''),
(1282, 'Y 0674', 'YULI', 'P', '2017-10-30', '2018-01-01', '89677707066', '', '', 'KASANG', '', ''),
(1283, 'E 1225', 'ETIN', 'P', '2017-10-30', '2018-01-01', '85215861893', '', '', 'KASANG LUAR', '', ''),
(1284, 'D 1094', 'DARA', 'P', '2017-11-01', '2018-01-01', '81368259370', '', '', 'MENDALO', '', ''),
(1285, 'S 1479', 'SURIA', 'P', '2017-11-01', '2018-01-01', '8,13668E+12', '', '', 'MARENE', '', ''),
(1286, 'D 0019', 'DENI KARTIKA', 'P', '2017-11-01', '2018-01-01', '81994422218', '', '', 'NUSA INDAH', '', ''),
(1287, 'G 0108', 'GITA', 'P', '2017-11-01', '1995-08-18', '81298018986', '', '', 'PAL MERAH', '', ''),
(1288, 'R 1473', 'RITA', 'P', '2017-11-01', '2018-01-01', '81919088740', '', '', 'TELANAI', '', ''),
(1289, 'Y 0994', 'YULIA', 'P', '2017-11-01', '1982-07-04', '85378503199', '', '', 'KENALI ATAS', '', ''),
(1290, 'T 0554', 'TETY', 'P', '2017-11-03', '2062-08-01', '8508099797', '', '', 'PAL 16', '', ''),
(1292, 'A 1420', 'ASMARANI', 'P', '2017-11-03', '2018-01-01', '85373333336', '', '', 'TALANG BANJAR', '', ''),
(1293, 'R 1670', 'RINA', 'P', '2017-11-03', '1994-01-02', '85271719440', '', '', 'MAYANG', '', ''),
(1294, 'W 0475', 'WULAN', 'P', '2017-11-03', '1996-07-31', '8,95382E+11', '', '', 'TALANG BANJAR', '', ''),
(1295, 'T 0392', 'TISNAWATI', 'P', '2017-11-03', '2069-09-04', '85384958432', '', '', 'KUMPEH', '', ''),
(1296, 'L 0183', 'LINA', 'P', '2017-11-03', '1993-03-06', '85769765113', '', '', 'MUARO JAMBI', '', ''),
(1297, 'M 1365', 'MELISA', 'P', '2017-11-03', '2067-12-25', '82166633333', '', '', 'TANJUNG PINANG', '', ''),
(1298, 'I 0799', 'IJAH', 'P', '2017-11-03', '2018-01-01', '85266955645', '', '', 'ISKANDAR DINATA', '', ''),
(1299, 'N 1163', 'NURHAYATI', 'P', '2017-11-03', '2018-01-01', '85225575656', '', '', 'PEMANCAR', '', ''),
(1301, 'G 0109', 'GIAN', 'P', '2017-11-03', '1995-03-23', '87700082376', 'SWASTA', '', 'TANGKIT', '', ''),
(1302, 'D 1279', 'DEVIE', 'P', '2017-11-03', '1995-12-19', '8153961341', 'SWASTA', '', 'TANGKIT', '', ''),
(1303, 'D 0110', 'diana novita', 'P', '2018-01-12', '2001-01-12', '81274708091', 'mahasiswa', 'diana@gmail.com', 'SELINCAH', '', ''),
(1304, 'R 0124', 'Riki Fernando', 'L', '2018-05-06', '1996-07-30', '08979159691', 'Programmer', 'richkyrf@gmail.com', 'Jl. Pangeran Diponegoro. Lorong Koni 5', '-', '0004552295');

-- --------------------------------------------------------

--
-- Table structure for table `tbmpemasok`
--

CREATE TABLE `tbmpemasok` (
  `IdPemasok` int(11) NOT NULL,
  `NamaPemasok` varchar(50) NOT NULL,
  `NoTelpon` varchar(20) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Keterangan` text NOT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbmpemasok`
--

INSERT INTO `tbmpemasok` (`IdPemasok`, `NamaPemasok`, `NoTelpon`, `Alamat`, `Keterangan`, `Status`) VALUES
(1, 'martono', '0', '', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbmtindakan`
--

CREATE TABLE `tbmtindakan` (
  `IdTindakan` int(11) NOT NULL,
  `NamaTindakan` varchar(50) NOT NULL,
  `IdTipeTindakan` int(11) DEFAULT NULL,
  `Harga` int(11) NOT NULL,
  `Keterangan` varchar(255) NOT NULL,
  `Status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbmtindakan`
--

INSERT INTO `tbmtindakan` (`IdTindakan`, `NamaTindakan`, `IdTipeTindakan`, `Harga`, `Keterangan`, `Status`) VALUES
(4, 'Facial Biasa', 1, 80000, ' ', 1),
(5, 'Facial Serum', 1, 120000, ' ', 1),
(6, 'Facial Oksi', 1, 185000, ' ', 1),
(8, 'Facial Mikro', 1, 185000, ' ', 1),
(9, 'Laser CO2 400', 2, 400000, ' ', 1),
(10, 'Laser CO2 600', 2, 600000, ' ', 1),
(11, 'Laser Quanta', 2, 600000, ' ', 1),
(12, 'Cukur Alis', 1, 20000, ' ', 1),
(14, 'Facial Detox', 1, 200000, ' ', 1),
(15, 'Inj. Kill', 2, 100000, ' ', 1),
(16, 'Inj. ACN', 2, 100000, ' ', 1),
(17, 'RF', 2, 300000, ' ', 1),
(18, 'Peeling AHL', 2, 200000, ' ', 1),
(19, 'Inj. Nano', 2, 450000, ' ', 1),
(20, 'Inj. Pro E', 2, 100000, ' ', 1),
(21, 'Inj. Vit C 250', 2, 250000, ' ', 1),
(22, 'Inj. Vit C 400', 2, 400000, ' ', 1),
(23, 'Inj. Lc 200', 2, 200000, ' ', 1),
(24, 'Inj. Lc 300', 2, 300000, ' ', 1),
(25, 'Facial Laser Jerawat', 1, 180000, ' ', 1),
(26, 'Laser Jerawat', 2, 100000, ' ', 1),
(27, 'Laser Rambut', 2, 100000, ' ', 1),
(28, 'Milk peel', 2, 200000, ' ', 1),
(29, 'Mela Peel', 2, 250000, ' ', 1),
(30, 'Facial Mikroksi', 1, 250000, ' ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbobatdetail`
--

CREATE TABLE `tbobatdetail` (
  `IdObatDetail` int(11) NOT NULL,
  `NoInvoice` varchar(15) NOT NULL,
  `IdObat` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbobatdetail`
--

INSERT INTO `tbobatdetail` (`IdObatDetail`, `NoInvoice`, `IdObat`, `Jumlah`) VALUES
(1, 'KB-000001-INV', 1, 1),
(2, 'KB-000002-INV', 1, 1),
(3, 'KB-000003-INV', 5, 1),
(4, 'KB-000004-INV', 18, 1),
(5, 'KB-000005-INV', 2, 1),
(6, 'KB-000006-INV', 1, 2),
(7, 'KB-000007-INV', 2, 1),
(8, 'KB-000008-INV', 1, 1),
(9, 'KB-000009-INV', 2, 1),
(10, 'KB-000010-INV', 4, 2),
(11, 'KB-000011-INV', 1, 1),
(12, 'KB-000012-INV', 2, 1),
(13, 'KB-000013-INV', 1, 1),
(14, 'KB-000014-INV', 3, 1),
(15, 'KB-000015-INV', 2, 2),
(16, 'KB-000016-INV', 1, 1),
(17, 'KB-000017-INV', 1, 1),
(18, 'KB-000018-INV', 1, 1),
(19, 'KB-000019-INV', 4, 1),
(20, 'KB-000020-INV', 1, 1),
(21, 'KB-000021-INV', 1, 1),
(22, 'KB-000022-INV', 3, 1),
(23, 'KB-000023-INV', 1, 1),
(24, 'KB-000024-INV', 1, 1),
(25, 'KB-000024-INV', 1, 1),
(26, 'KB-000024-INV', 1, 1),
(27, 'KB-000025-INV', 1, 1),
(28, 'KB-000025-INV', 2, 1),
(29, 'KB-000025-INV', 1, 1),
(30, 'KB-000025-INV', 2, 1),
(31, 'KB-000026-INV', 1, 1),
(32, 'KB-000026-INV', 2, 1),
(33, 'KB-000026-INV', 1, 1),
(34, 'KB-000026-INV', 2, 1),
(35, 'KB-000027-INV', 1, 1),
(36, 'KB-000027-INV', 2, 2),
(37, 'KB-000027-INV', 3, 3),
(38, 'KB-000027-INV', 4, 4),
(39, 'KB-000028-INV', 1, 1),
(40, 'KB-000028-INV', 2, 1),
(41, 'KB-000029-INV', 2, 1),
(42, 'KB-000029-INV', 15, 1),
(43, 'KB-000030-INV', 1, 1),
(44, 'KB-000030-INV', 2, 1),
(45, 'KB-000031-INV', 2, 1),
(46, 'KB-000031-INV', 22, 1),
(47, 'KB-000031-INV', 16, 2),
(48, 'KB-000031-INV', 18, 4),
(49, 'KB-000031-INV', 14, 5),
(50, 'KB-000031-INV', 45, 6),
(55, 'KB-000032-INV', 1, 1),
(56, 'KB-000032-INV', 2, 1),
(57, 'KB-000032-INV', 3, 1),
(58, 'KB-000032-INV', 4, 1),
(59, 'KB-000032-INV', 85, 1),
(60, 'KB-000033-INV', 1, 1),
(61, 'KB-000033-INV', 2, 2),
(62, 'KB-000033-INV', 3, 1),
(63, 'KB-000033-INV', 4, 1),
(64, 'KB-000033-INV', 5, 1),
(65, 'KB-000033-INV', 6, 1),
(66, 'KB-000033-INV', 7, 11),
(67, 'KB-000033-INV', 8, 11),
(68, 'KB-000033-INV', 11, 11),
(69, 'KB-000033-INV', 12, 11),
(70, 'KB-000033-INV', 10, 11),
(71, 'KB-000033-INV', 18, 11),
(72, 'KB-000033-INV', 19, 11),
(73, 'KB-000034-INV', 3, 1),
(74, 'KB-000035-INV', 5, 1),
(75, 'KB-000036-INV', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbpenjualan`
--

CREATE TABLE `tbpenjualan` (
  `IdPenjualan` int(10) NOT NULL,
  `NoTransaksi` varchar(12) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdPasien` int(10) DEFAULT NULL,
  `Keterangan` text NOT NULL,
  `Bayar` int(11) NOT NULL,
  `Diskon` int(11) NOT NULL,
  `Kasir` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbpenjualan`
--

INSERT INTO `tbpenjualan` (`IdPenjualan`, `NoTransaksi`, `Tanggal`, `IdPasien`, `Keterangan`, `Bayar`, `Diskon`, `Kasir`) VALUES
(1, 'KB-000001-PJ', '2018-05-16', 1195, '', 1650000, 0, 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `tbpenjualandetail`
--

CREATE TABLE `tbpenjualandetail` (
  `IdPenjualanDetail` int(10) NOT NULL,
  `NoTransaksi` varchar(12) NOT NULL,
  `NoKolom` int(5) NOT NULL,
  `IdBarang` int(10) NOT NULL,
  `Jumlah` int(5) NOT NULL,
  `Harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbpenjualandetail`
--

INSERT INTO `tbpenjualandetail` (`IdPenjualanDetail`, `NoTransaksi`, `NoKolom`, `IdBarang`, `Jumlah`, `Harga`) VALUES
(2, 'KB-000001-PJ', 1, 1, 10, 110000),
(3, 'KB-000001-PJ', 2, 2, 5, 110000);

-- --------------------------------------------------------

--
-- Table structure for table `tbpenyesuaianstok`
--

CREATE TABLE `tbpenyesuaianstok` (
  `IdPenyesuaian` int(11) NOT NULL,
  `NoPenyesuaian` varchar(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdBarang` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbpenyesuaianstok`
--

INSERT INTO `tbpenyesuaianstok` (`IdPenyesuaian`, `NoPenyesuaian`, `Tanggal`, `IdBarang`, `Jumlah`, `Keterangan`) VALUES
(1, 'KB-000001-PS', '2018-05-16', 1, 20, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbpenyesuaianstokgudangbesar`
--

CREATE TABLE `tbpenyesuaianstokgudangbesar` (
  `IdPenyesuaian` int(11) NOT NULL,
  `NoPenyesuaian` varchar(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdBarang` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbpenyesuaianstokgudangbesar`
--

INSERT INTO `tbpenyesuaianstokgudangbesar` (`IdPenyesuaian`, `NoPenyesuaian`, `Tanggal`, `IdBarang`, `Jumlah`, `Keterangan`) VALUES
(1, 'KB-000001-PG', '2018-05-16', 4, 10, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbperawatan`
--

CREATE TABLE `tbperawatan` (
  `IdPerawatan` int(11) NOT NULL,
  `Tanggal` date NOT NULL,
  `NoAntrian` int(11) NOT NULL,
  `NoInvoice` varchar(15) NOT NULL,
  `IdDokter` int(11) NOT NULL,
  `IdBeautician` int(11) DEFAULT NULL,
  `Keluhan` varchar(255) NOT NULL,
  `Diagnosa` varchar(255) NOT NULL,
  `Catatan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbperawatan`
--

INSERT INTO `tbperawatan` (`IdPerawatan`, `Tanggal`, `NoAntrian`, `NoInvoice`, `IdDokter`, `IdBeautician`, `Keluhan`, `Diagnosa`, `Catatan`) VALUES
(1, '2018-04-27', 1, 'KB-000001-INV', 1, 1, '1', '2', '3'),
(2, '2018-04-28', 2, 'KB-000002-INV', 1, 1, 'asdasd', 'asdasdasd', 'asdasdasdasd'),
(3, '2018-04-28', 1, 'KB-000003-INV', 1, 5, '3', '2', '1'),
(4, '2018-04-29', 1, 'KB-000004-INV', 1, 2, '1', '2', '3'),
(5, '2018-04-30', 1, 'KB-000005-INV', 1, 2, '1', '2', '3'),
(6, '2018-04-30', 2, 'KB-000006-INV', 1, 2, '23', '13', '321'),
(7, '2018-05-01', 1, 'KB-000007-INV', 1, 4, 'asdasda', 'dsadsda', 'sdasdas'),
(8, '2018-05-01', 2, 'KB-000008-INV', 1, 3, '123', '321', '123'),
(9, '2018-05-01', 3, 'KB-000009-INV', 1, 3, '2', '3', '1'),
(10, '2018-05-01', 5, 'KB-000010-INV', 1, 1, 'Jerawat', 'Kasih Martono', 'Martono Is The Best'),
(11, '2018-05-02', 1, 'KB-000011-INV', 1, 1, '1', '2', '3'),
(12, '2018-05-02', 2, 'KB-000012-INV', 2, 2, '1', '2', '1'),
(13, '2018-05-03', 1, 'KB-000013-INV', 1, 2, 'asdasd', 'asdfasdf', 'asdfgasdfg'),
(14, '2018-05-03', 2, 'KB-000014-INV', 1, 2, '123', '123', '123'),
(15, '2018-05-05', 1, 'KB-000015-INV', 1, 1, '123', '123', '123'),
(16, '2018-05-05', 2, 'KB-000016-INV', 1, 1, '123', '123', '123'),
(17, '2018-05-05', 3, 'KB-000017-INV', 1, 1, '123', '123', '123'),
(18, '2018-05-05', 4, 'KB-000018-INV', 1, 1, '1', '2', '3'),
(19, '2018-05-06', 1, 'KB-000019-INV', 1, 1, 'asdasd', 'asdasd', 'asdasd'),
(20, '2018-05-06', 2, 'KB-000020-INV', 1, 1, '123', '123', '123'),
(21, '2018-05-06', 3, 'KB-000021-INV', 1, 1, '123', '123', '123'),
(22, '2018-05-06', 4, 'KB-000022-INV', 1, 1, '123', '123123', '123'),
(23, '2018-05-06', 5, 'KB-000023-INV', 1, 2, '1', '2', '3'),
(24, '2018-05-06', 6, 'KB-000024-INV', 1, 1, '123123', '2131', '123'),
(25, '2018-05-06', 7, 'KB-000025-INV', 1, 2, '123', '123', '123'),
(26, '2018-05-06', 8, 'KB-000026-INV', 1, 2, '123', '123', '123'),
(27, '2018-05-07', 1, 'KB-000027-INV', 1, 2, '123', '123', '123'),
(28, '2018-05-08', 1, 'KB-000028-INV', 1, 2, '12312321', '213123123', '12321321321'),
(29, '2018-05-08', 2, 'KB-000029-INV', 1, 1, '3212133', '12321321', '321321'),
(30, '2018-05-09', 1, 'KB-000030-INV', 1, 1, '1', '2', '3'),
(31, '2018-05-10', 1, 'KB-000031-INV', 1, 1, '123', '123', '123'),
(32, '2018-05-11', 1, 'KB-000032-INV', 1, 1, '123', '123', '123'),
(33, '2018-05-11', 2, 'KB-000033-INV', 2, 1, '123', '123', '123'),
(34, '2018-05-16', 1, 'KB-000034-INV', 1, 1, '123', '123123', '123'),
(35, '2018-05-16', 2, 'KB-000035-INV', 1, 1, '123', '123', '123'),
(36, '2018-05-16', 3, 'KB-000036-INV', 1, 1, '123', '123', '123');

-- --------------------------------------------------------

--
-- Table structure for table `tbperawatandetail`
--

CREATE TABLE `tbperawatandetail` (
  `IdPerawatanDetail` int(11) NOT NULL,
  `NoInvoice` varchar(15) NOT NULL,
  `IdTindakan` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbperawatandetail`
--

INSERT INTO `tbperawatandetail` (`IdPerawatanDetail`, `NoInvoice`, `IdTindakan`, `Jumlah`) VALUES
(1, 'KB-000001-INV', 4, 1),
(2, 'KB-000002-INV', 4, 1),
(3, 'KB-000003-INV', 5, 1),
(4, 'KB-000004-INV', 4, 1),
(5, 'KB-000005-INV', 4, 1),
(6, 'KB-000006-INV', 4, 1),
(7, 'KB-000007-INV', 4, 1),
(8, 'KB-000008-INV', 4, 1),
(9, 'KB-000009-INV', 5, 2),
(10, 'KB-000010-INV', 4, 1),
(11, 'KB-000011-INV', 4, 1),
(12, 'KB-000012-INV', 4, 1),
(13, 'KB-000013-INV', 4, 1),
(14, 'KB-000014-INV', 10, 1),
(15, 'KB-000015-INV', 4, 1),
(16, 'KB-000016-INV', 4, 1),
(17, 'KB-000017-INV', 4, 1),
(18, 'KB-000018-INV', 4, 1),
(19, 'KB-000019-INV', 4, 1),
(20, 'KB-000020-INV', 5, 1),
(21, 'KB-000021-INV', 4, 1),
(22, 'KB-000022-INV', 4, 1),
(23, 'KB-000023-INV', 4, 1),
(24, 'KB-000024-INV', 4, 1),
(25, 'KB-000025-INV', 4, 1),
(26, 'KB-000025-INV', 5, 1),
(27, 'KB-000026-INV', 4, 1),
(28, 'KB-000026-INV', 6, 1),
(29, 'KB-000027-INV', 4, 1),
(30, 'KB-000028-INV', 4, 1),
(31, 'KB-000028-INV', 6, 1),
(32, 'KB-000029-INV', 5, 1),
(33, 'KB-000029-INV', 6, 1),
(34, 'KB-000030-INV', 4, 1),
(35, 'KB-000030-INV', 5, 1),
(36, 'KB-000031-INV', 5, 1),
(37, 'KB-000031-INV', 9, 1),
(38, 'KB-000031-INV', 11, 1),
(41, 'KB-000032-INV', 4, 1),
(42, 'KB-000032-INV', 5, 1),
(43, 'KB-000033-INV', 4, 1),
(44, 'KB-000034-INV', 4, 1),
(45, 'KB-000035-INV', 4, 1),
(46, 'KB-000036-INV', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbpermintaanstok`
--

CREATE TABLE `tbpermintaanstok` (
  `IdPermintaan` int(11) NOT NULL,
  `NoPermintaan` varchar(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdBarang` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbpermintaanstok`
--

INSERT INTO `tbpermintaanstok` (`IdPermintaan`, `NoPermintaan`, `Tanggal`, `IdBarang`, `Jumlah`, `Keterangan`) VALUES
(1, 'KB-000001-PN', '2018-05-01', 1, 10, ''),
(2, 'KB-000002-PN', '2018-05-16', 1, 100, ''),
(3, 'KB-000003-PN', '2018-05-16', 2, 110, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbsmjenisbarang`
--

CREATE TABLE `tbsmjenisbarang` (
  `IdJenisBarang` int(11) NOT NULL,
  `JenisBarang` varchar(50) NOT NULL,
  `Keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbsmjenisbarang`
--

INSERT INTO `tbsmjenisbarang` (`IdJenisBarang`, `JenisBarang`, `Keterangan`) VALUES
(1, 'CREAM', ''),
(2, 'OBAT', ''),
(3, 'ALKES', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbsmjenisperawatan`
--

CREATE TABLE `tbsmjenisperawatan` (
  `IdJenisPerawatan` int(11) NOT NULL,
  `JenisPerawatan` varchar(50) NOT NULL,
  `IdTypePerawatan` int(11) NOT NULL,
  `Keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbsmjenisperawatan`
--

INSERT INTO `tbsmjenisperawatan` (`IdJenisPerawatan`, `JenisPerawatan`, `IdTypePerawatan`, `Keterangan`) VALUES
(1, 'Facial Laser Jerawat', 1, ''),
(2, 'Facial Detox', 1, ''),
(3, 'Cukur Alis', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbsmtipeperawatan`
--

CREATE TABLE `tbsmtipeperawatan` (
  `IdTipePerawatan` int(11) NOT NULL,
  `TipePerawatan` varchar(50) NOT NULL,
  `Keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbsmtipeperawatan`
--

INSERT INTO `tbsmtipeperawatan` (`IdTipePerawatan`, `TipePerawatan`, `Keterangan`) VALUES
(1, 'FACIAL', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbsmtipetindakan`
--

CREATE TABLE `tbsmtipetindakan` (
  `IdTipeTindakan` int(11) NOT NULL,
  `TipeTindakan` varchar(50) NOT NULL,
  `Keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbsmtipetindakan`
--

INSERT INTO `tbsmtipetindakan` (`IdTipeTindakan`, `TipeTindakan`, `Keterangan`) VALUES
(1, 'FACIAL', ''),
(2, 'Tindakan', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbuser`
--

CREATE TABLE `tbuser` (
  `IdUser` int(10) NOT NULL,
  `Username` varchar(25) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Level` enum('Administrator','Operator','Kasir','Gudang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbuser`
--

INSERT INTO `tbuser` (`IdUser`, `Username`, `Password`, `Level`) VALUES
(-1, '', '57f842286171094855e51fc3a541c1e2', 'Administrator'),
(1, 'ADMIN', 'd41d8cd98f00b204e9800998ecf8427e', 'Administrator'),
(2, 'RIKI', 'dc131ed8751b5ac500a79b1d7207929c', 'Operator'),
(4, 'MARTONO', 'af7936442257f4a1b39e5f7f1c4e2c1c', 'Operator'),
(5, 'HENDRI', 'ee7e5d37b0b10c30a11df69c4e3d1d57', 'Operator'),
(6, 'christian', '7ff135854376850e9711bd75ce942e07', 'Administrator'),
(7, 'kasir', 'd41d8cd98f00b204e9800998ecf8427e', 'Kasir'),
(8, 'gudang', 'd41d8cd98f00b204e9800998ecf8427e', 'Gudang');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbantrian`
--
ALTER TABLE `tbantrian`
  ADD PRIMARY KEY (`IdAntrian`);

--
-- Indexes for table `tbbarangmasuk`
--
ALTER TABLE `tbbarangmasuk`
  ADD PRIMARY KEY (`IdBarangMasuk`);

--
-- Indexes for table `tbbarangmasukdetail`
--
ALTER TABLE `tbbarangmasukdetail`
  ADD PRIMARY KEY (`IdBarangMasukDetail`);

--
-- Indexes for table `tbbilling`
--
ALTER TABLE `tbbilling`
  ADD PRIMARY KEY (`IdBilling`);

--
-- Indexes for table `tbbillingobat`
--
ALTER TABLE `tbbillingobat`
  ADD PRIMARY KEY (`IdBillingObat`);

--
-- Indexes for table `tbbillingtindakan`
--
ALTER TABLE `tbbillingtindakan`
  ADD PRIMARY KEY (`IdBillingTindakan`);

--
-- Indexes for table `tbmbarang`
--
ALTER TABLE `tbmbarang`
  ADD PRIMARY KEY (`IdBarang`);

--
-- Indexes for table `tbmbeautician`
--
ALTER TABLE `tbmbeautician`
  ADD PRIMARY KEY (`IdBeautician`);

--
-- Indexes for table `tbmdokter`
--
ALTER TABLE `tbmdokter`
  ADD PRIMARY KEY (`IdDokter`);

--
-- Indexes for table `tbmpasien`
--
ALTER TABLE `tbmpasien`
  ADD PRIMARY KEY (`IdPasien`),
  ADD UNIQUE KEY `KodePasien` (`KodePasien`);

--
-- Indexes for table `tbmpemasok`
--
ALTER TABLE `tbmpemasok`
  ADD PRIMARY KEY (`IdPemasok`);

--
-- Indexes for table `tbmtindakan`
--
ALTER TABLE `tbmtindakan`
  ADD PRIMARY KEY (`IdTindakan`);

--
-- Indexes for table `tbobatdetail`
--
ALTER TABLE `tbobatdetail`
  ADD PRIMARY KEY (`IdObatDetail`);

--
-- Indexes for table `tbpenjualan`
--
ALTER TABLE `tbpenjualan`
  ADD PRIMARY KEY (`IdPenjualan`),
  ADD UNIQUE KEY `NomorBarangMasuk` (`NoTransaksi`),
  ADD KEY `IdVendor` (`IdPasien`);

--
-- Indexes for table `tbpenjualandetail`
--
ALTER TABLE `tbpenjualandetail`
  ADD PRIMARY KEY (`IdPenjualanDetail`),
  ADD KEY `NomorBarangMasuk` (`NoTransaksi`),
  ADD KEY `IdBarang` (`IdBarang`),
  ADD KEY `JumlahBarang` (`Jumlah`),
  ADD KEY `HargaBarang` (`Harga`);

--
-- Indexes for table `tbpenyesuaianstok`
--
ALTER TABLE `tbpenyesuaianstok`
  ADD PRIMARY KEY (`IdPenyesuaian`);

--
-- Indexes for table `tbpenyesuaianstokgudangbesar`
--
ALTER TABLE `tbpenyesuaianstokgudangbesar`
  ADD PRIMARY KEY (`IdPenyesuaian`);

--
-- Indexes for table `tbperawatan`
--
ALTER TABLE `tbperawatan`
  ADD PRIMARY KEY (`IdPerawatan`);

--
-- Indexes for table `tbperawatandetail`
--
ALTER TABLE `tbperawatandetail`
  ADD PRIMARY KEY (`IdPerawatanDetail`);

--
-- Indexes for table `tbpermintaanstok`
--
ALTER TABLE `tbpermintaanstok`
  ADD PRIMARY KEY (`IdPermintaan`);

--
-- Indexes for table `tbsmjenisbarang`
--
ALTER TABLE `tbsmjenisbarang`
  ADD PRIMARY KEY (`IdJenisBarang`);

--
-- Indexes for table `tbsmjenisperawatan`
--
ALTER TABLE `tbsmjenisperawatan`
  ADD PRIMARY KEY (`IdJenisPerawatan`);

--
-- Indexes for table `tbsmtipeperawatan`
--
ALTER TABLE `tbsmtipeperawatan`
  ADD PRIMARY KEY (`IdTipePerawatan`);

--
-- Indexes for table `tbsmtipetindakan`
--
ALTER TABLE `tbsmtipetindakan`
  ADD PRIMARY KEY (`IdTipeTindakan`);

--
-- Indexes for table `tbuser`
--
ALTER TABLE `tbuser`
  ADD PRIMARY KEY (`IdUser`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbantrian`
--
ALTER TABLE `tbantrian`
  MODIFY `IdAntrian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT for table `tbbarangmasuk`
--
ALTER TABLE `tbbarangmasuk`
  MODIFY `IdBarangMasuk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbbarangmasukdetail`
--
ALTER TABLE `tbbarangmasukdetail`
  MODIFY `IdBarangMasukDetail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbbilling`
--
ALTER TABLE `tbbilling`
  MODIFY `IdBilling` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `tbbillingobat`
--
ALTER TABLE `tbbillingobat`
  MODIFY `IdBillingObat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `tbbillingtindakan`
--
ALTER TABLE `tbbillingtindakan`
  MODIFY `IdBillingTindakan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `tbmbarang`
--
ALTER TABLE `tbmbarang`
  MODIFY `IdBarang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;
--
-- AUTO_INCREMENT for table `tbmbeautician`
--
ALTER TABLE `tbmbeautician`
  MODIFY `IdBeautician` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `tbmdokter`
--
ALTER TABLE `tbmdokter`
  MODIFY `IdDokter` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tbmpasien`
--
ALTER TABLE `tbmpasien`
  MODIFY `IdPasien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1305;
--
-- AUTO_INCREMENT for table `tbmpemasok`
--
ALTER TABLE `tbmpemasok`
  MODIFY `IdPemasok` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbmtindakan`
--
ALTER TABLE `tbmtindakan`
  MODIFY `IdTindakan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `tbobatdetail`
--
ALTER TABLE `tbobatdetail`
  MODIFY `IdObatDetail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
--
-- AUTO_INCREMENT for table `tbpenjualan`
--
ALTER TABLE `tbpenjualan`
  MODIFY `IdPenjualan` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbpenjualandetail`
--
ALTER TABLE `tbpenjualandetail`
  MODIFY `IdPenjualanDetail` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbpenyesuaianstok`
--
ALTER TABLE `tbpenyesuaianstok`
  MODIFY `IdPenyesuaian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbpenyesuaianstokgudangbesar`
--
ALTER TABLE `tbpenyesuaianstokgudangbesar`
  MODIFY `IdPenyesuaian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbperawatan`
--
ALTER TABLE `tbperawatan`
  MODIFY `IdPerawatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `tbperawatandetail`
--
ALTER TABLE `tbperawatandetail`
  MODIFY `IdPerawatanDetail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT for table `tbpermintaanstok`
--
ALTER TABLE `tbpermintaanstok`
  MODIFY `IdPermintaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbsmjenisbarang`
--
ALTER TABLE `tbsmjenisbarang`
  MODIFY `IdJenisBarang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbsmjenisperawatan`
--
ALTER TABLE `tbsmjenisperawatan`
  MODIFY `IdJenisPerawatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbsmtipeperawatan`
--
ALTER TABLE `tbsmtipeperawatan`
  MODIFY `IdTipePerawatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tbsmtipetindakan`
--
ALTER TABLE `tbsmtipetindakan`
  MODIFY `IdTipeTindakan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tbuser`
--
ALTER TABLE `tbuser`
  MODIFY `IdUser` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
