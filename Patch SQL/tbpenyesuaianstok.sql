-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 24 Apr 2018 pada 16.13
-- Versi server: 10.1.31-MariaDB
-- Versi PHP: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbkusuma`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbpenyesuaianstok`
--

CREATE TABLE `tbpenyesuaianstok` (
  `IdPenyesuaian` int(11) NOT NULL,
  `NoPenyesuaian` varchar(20) NOT NULL,
  `Tanggal` date NOT NULL,
  `IdBarangLain` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbpenyesuaianstok`
--

INSERT INTO `tbpenyesuaianstok` (`IdPenyesuaian`, `NoPenyesuaian`, `Tanggal`, `IdBarangLain`, `Jumlah`, `Keterangan`) VALUES
(1, 'GG-000001-PS', '2018-04-24', 1, 10, ''),
(2, 'GG-000002-PS', '2018-04-24', 1, 10, ''),
(3, 'KB-000003-PS', '2018-04-24', 1, 10, 'asasz');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tbpenyesuaianstok`
--
ALTER TABLE `tbpenyesuaianstok`
  ADD PRIMARY KEY (`IdPenyesuaian`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tbpenyesuaianstok`
--
ALTER TABLE `tbpenyesuaianstok`
  MODIFY `IdPenyesuaian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
