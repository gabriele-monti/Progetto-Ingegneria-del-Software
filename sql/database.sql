CREATE DATABASE IF NOT EXISTS `foodmood`
  CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `foodmood`;

CREATE TABLE `users` (
  `user_id`    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name`  VARCHAR(100) NOT NULL,
  `is_active`  BOOLEAN NOT NULL DEFAULT TRUE,
  `created_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB;

CREATE TABLE `auth` (
  `user_id`  INT UNSIGNED NOT NULL,
  `email`    VARCHAR(254) NOT NULL UNIQUE,
  `pwd_hash` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_auth_user` FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB;

DELIMITER $$

CREATE PROCEDURE `sp_register`(
  IN  p_first_name VARCHAR(100),
  IN  p_last_name  VARCHAR(100),
  IN  p_email      VARCHAR(254),
  IN  p_pwd_hash   VARCHAR(255),
  OUT p_user_id    INT UNSIGNED
)
BEGIN
  DECLARE EXIT HANDLER FOR 1062
  BEGIN
    ROLLBACK;
    SET p_user_id = NULL;
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'EMAIL_EXISTS';
  END;
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    SET p_user_id = NULL;
  END;
  SET p_email = LOWER(p_email);
  START TRANSACTION;
    INSERT INTO `users`(`first_name`,`last_name`,`is_active`)
    VALUES (p_first_name, p_last_name, TRUE);
    SET p_user_id = LAST_INSERT_ID();
    INSERT INTO `auth`(`user_id`,`email`,`pwd_hash`)
    VALUES (p_user_id, p_email, p_pwd_hash);
  COMMIT;
END $$

CREATE PROCEDURE `sp_login`(
  IN  p_email      VARCHAR(254),
  OUT p_user_id    INT UNSIGNED,
  OUT p_first_name VARCHAR(100),
  OUT p_last_name  VARCHAR(100),
  OUT p_is_active  BOOLEAN,
  OUT p_pwd_hash   VARCHAR(255)
)
BEGIN
  DECLARE CONTINUE HANDLER FOR NOT FOUND BEGIN END;
  SET p_user_id = NULL;
  SET p_first_name = NULL;
  SET p_last_name = NULL;
  SET p_is_active = NULL;
  SET p_pwd_hash = NULL;
  SET p_email = LOWER(p_email);
  SELECT u.user_id, u.first_name, u.last_name, u.is_active, a.pwd_hash
    INTO p_user_id, p_first_name, p_last_name, p_is_active, p_pwd_hash
  FROM `auth` AS a
  JOIN `users` AS u ON u.user_id = a.user_id
  WHERE a.`email` = p_email
  LIMIT 1;
END $$
DELIMITER ;
