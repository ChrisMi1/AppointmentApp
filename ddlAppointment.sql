
DROP SCHEMA IF EXISTS `appointment_db`;
CREATE SCHEMA `appointment_db`;
use `appointment_db`; 

CREATE TABLE `user`(
	`id` VARCHAR(70) ,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `password`VARCHAR(70) NULL,
    `phone_number` VARCHAR(10) NOT NULL, 
    `role` VARCHAR(10) NOT NULL,
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * 
from user; 
CREATE TABLE `appointment`(
	`id` CHAR(36),
    ``
    
    `user_id` CHAR(36),    
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


