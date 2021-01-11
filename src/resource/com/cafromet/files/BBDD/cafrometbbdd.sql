-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-01-2021 a las 22:47:34
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cafrometbbdd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centros_meteorologicos`
--

CREATE TABLE `centros_meteorologicos` (
  `ID_CENTRO_MET` int(3) NOT NULL,
  `ID_MUNICIPIO` int(3) DEFAULT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `DIRECCION` varchar(50) DEFAULT NULL,
  `LATITUD` float(20,20) DEFAULT NULL,
  `LONGITUD` float(20,20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_CLIENTE` int(3) NOT NULL,
  `USUARIO` varchar(30) DEFAULT NULL,
  `PASSWD` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacios_naturales`
--

CREATE TABLE `espacios_naturales` (
  `ID_ESPACIO` int(3) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `DESCRIPCION` varchar(2000) DEFAULT NULL,
  `TIPO` varchar(50) DEFAULT NULL,
  `DIRECCION` varchar(50) DEFAULT NULL,
  `CATEGORIA` varchar(50) DEFAULT NULL,
  `LATITUD` float(20,20) DEFAULT NULL,
  `LONGITUD` float(20,20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fuentes`
--

CREATE TABLE `fuentes` (
  `ID` int(3) NOT NULL,
  `FORMATO` varchar(5) DEFAULT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `URL` varchar(150) DEFAULT NULL,
  `HASH` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mediciones_centro_met`
--

CREATE TABLE `mediciones_centro_met` (
  `ID_CENTRO_MET` int(3) NOT NULL,
  `FECHA` date NOT NULL,
  `HORA` time(2) NOT NULL,
  `COMGM3` float(3,2) DEFAULT NULL,
  `CO8HMGM3` float(3,2) DEFAULT NULL,
  `NOGM3` float(3,2) DEFAULT NULL,
  `NO2` float(3,2) DEFAULT NULL,
  `NO2ICA` varchar(50) DEFAULT NULL,
  `NOXGM3` float(3,2) DEFAULT NULL,
  `PM10` float(3,2) DEFAULT NULL,
  `PM10ICA` varchar(50) DEFAULT NULL,
  `PM25` float(3,2) DEFAULT NULL,
  `PM25ICA` varchar(50) DEFAULT NULL,
  `SO2` float(3,2) DEFAULT NULL,
  `SO2ICA` varchar(50) DEFAULT NULL,
  `ICA_ESTACION` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipios`
--

CREATE TABLE `municipios` (
  `ID_MUNICIPIO` int(3) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `ID_PROVINCIA` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipios_espacios_nat`
--

CREATE TABLE `municipios_espacios_nat` (
  `ID_ESPACIO` int(3) NOT NULL,
  `ID_MUNICIPIO` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincias`
--

CREATE TABLE `provincias` (
  `ID_PROVINCIA` int(3) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `centros_meteorologicos`
--
ALTER TABLE `centros_meteorologicos`
  ADD PRIMARY KEY (`ID_CENTRO_MET`),
  ADD KEY `ID_MUNICIPIO` (`ID_MUNICIPIO`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_CLIENTE`);

--
-- Indices de la tabla `espacios_naturales`
--
ALTER TABLE `espacios_naturales`
  ADD PRIMARY KEY (`ID_ESPACIO`);

--
-- Indices de la tabla `fuentes`
--
ALTER TABLE `fuentes`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `mediciones_centro_met`
--
ALTER TABLE `mediciones_centro_met`
  ADD PRIMARY KEY (`ID_CENTRO_MET`,`FECHA`,`HORA`);

--
-- Indices de la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD PRIMARY KEY (`ID_MUNICIPIO`),
  ADD KEY `ID_PROVINCIA` (`ID_PROVINCIA`);

--
-- Indices de la tabla `municipios_espacios_nat`
--
ALTER TABLE `municipios_espacios_nat`
  ADD KEY `ID_ESPACIO` (`ID_ESPACIO`),
  ADD KEY `ID_MUNICIPIO` (`ID_MUNICIPIO`);

--
-- Indices de la tabla `provincias`
--
ALTER TABLE `provincias`
  ADD PRIMARY KEY (`ID_PROVINCIA`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `centros_meteorologicos`
--
ALTER TABLE `centros_meteorologicos`
  MODIFY `ID_CENTRO_MET` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID_CLIENTE` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `espacios_naturales`
--
ALTER TABLE `espacios_naturales`
  MODIFY `ID_ESPACIO` int(3) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `centros_meteorologicos`
--
ALTER TABLE `centros_meteorologicos`
  ADD CONSTRAINT `centros_meteorologicos_ibfk_1` FOREIGN KEY (`ID_MUNICIPIO`) REFERENCES `municipios` (`ID_MUNICIPIO`) ON DELETE SET NULL;

--
-- Filtros para la tabla `mediciones_centro_met`
--
ALTER TABLE `mediciones_centro_met`
  ADD CONSTRAINT `mediciones_centro_met_ibfk_1` FOREIGN KEY (`ID_CENTRO_MET`) REFERENCES `centros_meteorologicos` (`ID_CENTRO_MET`) ON DELETE CASCADE;

--
-- Filtros para la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD CONSTRAINT `municipios_ibfk_1` FOREIGN KEY (`ID_PROVINCIA`) REFERENCES `provincias` (`ID_PROVINCIA`) ON DELETE SET NULL;

--
-- Filtros para la tabla `municipios_espacios_nat`
--
ALTER TABLE `municipios_espacios_nat`
  ADD CONSTRAINT `municipios_espacios_nat_ibfk_1` FOREIGN KEY (`ID_ESPACIO`) REFERENCES `espacios_naturales` (`ID_ESPACIO`) ON DELETE CASCADE,
  ADD CONSTRAINT `municipios_espacios_nat_ibfk_2` FOREIGN KEY (`ID_MUNICIPIO`) REFERENCES `municipios` (`ID_MUNICIPIO`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;