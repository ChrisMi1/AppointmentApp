
DROP SCHEMA IF EXISTS `appointment_db`;
CREATE SCHEMA `appointment_db`;
use `appointment_db`; 

CREATE TABLE `user`(
	`id` CHAR(36),
    `first_name` VARCHAR(50) NOT NULL,
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;