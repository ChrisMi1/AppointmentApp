
DROP SCHEMA IF EXISTS `appointment_db`;
CREATE SCHEMA `appointment_db`;
use `appointment_db`; 

CREATE TABLE `user`(
	`id` VARCHAR(36) NOT NULL ,
    `first_name` VARCHAR(50) NOT NULL,	
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `phone_number` VARCHAR(10) NOT NULL,
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `employee`(
	`id` VARCHAR(36) NOT NULL ,
    `first_name` VARCHAR(50) NOT NULL,	
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
	`phone_number` VARCHAR(10) NOT NULL,
    `password` VARCHAR(70) NOT NULL,
    `photo` blob NOT NULL,
    `role` VARCHAR(50) NOT NULL,
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `schedule`(
	`id` VARCHAR(36) NOT NULL ,
    `from` DATETIME NOT NULL,
    `to` DATETIME NOT NULL,
	`employee_id` VARCHAR(36) NOT NULL,
    primary key(`id`),
	FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `appointment`(
	`id` VARCHAR(36) NOT NULL,
    `appointment_number` VARCHAR(8) NOT NULL , 
    `from` DATETIME NOT NULL, 
    `to` DATETIME NOT NULL, 
    `total_price` DECIMAL(10,2) NOT NULL ,
    `canceled` BOOL NOT NULL, 
    `user_id` CHAR(36),    
    `employee_id`  VARCHAR(36) NOT NULL,
    primary key(`id`),
    FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`),
	FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DELIMITER //

CREATE TRIGGER `set_prefix_before_insert`
BEFORE INSERT ON `appointment`
FOR EACH ROW
BEGIN
    SET NEW.appointment_number = LEFT(NEW.id, 8); 
END;
//
DELIMITER ;

CREATE TABLE `service`(
	`id` VARCHAR(36) NOT NULL ,
    `service_name` VARCHAR(200) NOT NULL,
    `duration` INT(4) NOT NULL,
    `price` DECIMAL(10,2),
     primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `service_booked`(
	`id` VARCHAR(36) NOT NULL ,
    `appointment_id` VARCHAR(36) NOT NULL ,
    `service_id` VARCHAR(36) NOT NULL ,
	primary key(`id`),
    FOREIGN KEY(`appointment_id`) REFERENCES `appointment`(`id`),
	FOREIGN KEY(`service_id`) REFERENCES `service`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;












