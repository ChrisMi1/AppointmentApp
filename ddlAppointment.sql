
DROP SCHEMA IF EXISTS `appointment_db`;
CREATE SCHEMA `appointment_db`;
use `appointment_db`; 

CREATE TABLE `user`(
	`id` BINARY(16) NOT NULL,
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

10-10:30 
10:30-11:00

barbers{
names,fk id }




CREATE TABLE `appointment`(
	`id` BINARY(16) NOT NULL,
    `date_of_appontment` DATETIME, 
    `user_id` CHAR(36),    
    `barber_id`  BINARY(16) NOT NULL
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


