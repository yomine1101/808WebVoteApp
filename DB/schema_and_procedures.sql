-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        11.4.3-MariaDB - mariadb.org binary distribution
-- 伺服器作業系統:                      Win64
-- HeidiSQL 版本:                  12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 傾印 webvoteapp 的資料庫結構
CREATE DATABASE IF NOT EXISTS `webvoteapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `webvoteapp`;

-- 傾印  程序 webvoteapp.DeleteVoteItem 結構
DELIMITER //
CREATE PROCEDURE `DeleteVoteItem`(
	IN `p_id` INT
)
BEGIN

    DELETE FROM votes
    WHERE vote_item_id = p_id;
    
    DELETE FROM vote_items
    WHERE id = p_id;
END//
DELIMITER ;

-- 傾印  程序 webvoteapp.GetAllVoteItems 結構
DELIMITER //
CREATE PROCEDURE `GetAllVoteItems`()
BEGIN
    SELECT * FROM vote_items;
END//
DELIMITER ;

-- 傾印  程序 webvoteapp.InsertVoteItem 結構
DELIMITER //
CREATE PROCEDURE `InsertVoteItem`(
	IN `p_name` VARCHAR(255)
)
BEGIN
    INSERT INTO vote_items (name)
    VALUES (p_name);
END//
DELIMITER ;

-- 傾印  程序 webvoteapp.UpdateVoteItem 結構
DELIMITER //
CREATE PROCEDURE `UpdateVoteItem`(
	IN `p_id` INT,
	IN `p_name` VARCHAR(255)
)
BEGIN
    UPDATE vote_items
    SET name = p_name
    WHERE id = p_id;
END//
DELIMITER ;

-- 傾印  資料表 webvoteapp.users 結構
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  webvoteapp.users 的資料：~3 rows (近似值)
INSERT INTO `users` (`id`, `username`, `account`, `password`, `role`) VALUES
	(1, 'admin', 'admin', 'admin', 'ADMIN'),
	(2, 'Allen', '0000', '0000', 'USER'),
	(3, 'Vincent', '1111', '1111', 'USER');

-- 傾印  資料表 webvoteapp.votes 結構
CREATE TABLE IF NOT EXISTS `votes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voter` varchar(255) NOT NULL,
  `vote_item_id` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `vote_item_id` (`vote_item_id`),
  CONSTRAINT `votes_ibfk_1` FOREIGN KEY (`vote_item_id`) REFERENCES `vote_items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  webvoteapp.votes 的資料：~9 rows (近似值)
INSERT INTO `votes` (`id`, `voter`, `vote_item_id`) VALUES
	(1, 'Leo', 1),
	(2, 'Sandy', 1),
	(3, 'Sandy', 2),
	(4, 'Randy', 2),
	(5, 'RSY', 2),
	(6, 'John Doe', 1),
	(36, 'Allen', 6),
	(37, 'Allen', 7),
	(43, 'Allen', 2),
	(44, 'Allen', 6),
	(45, 'Allen', 17);

-- 傾印  資料表 webvoteapp.vote_items 結構
CREATE TABLE IF NOT EXISTS `vote_items` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 正在傾印表格  webvoteapp.vote_items 的資料：~5 rows (近似值)
INSERT INTO `vote_items` (`id`, `name`) VALUES
	(1, '電腦'),
	(2, '滑鼠'),
	(6, '鳳梨芒果冰'),
	(7, '珍珠奶茶椰果'),
	(17, '香蕉牛奶');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
