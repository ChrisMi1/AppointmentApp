
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
    `photo` blob ,
    `role` VARCHAR(50) NOT NULL,
    `email_verified` BOOLEAN DEFAULT FALSE, 
    primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `schedule`(
	`id` VARCHAR(36) NOT NULL ,
    `date` DATE NOT NULL, 
    `start` time NOT NULL,
    `end` time NOT NULL,
	`employee_id` VARCHAR(36) NOT NULL,
    primary key(`id`),
	FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `agency`(
	`id` VARCHAR(36) NOT NULL ,
    `agency_name` VARCHAR(200) NOT NULL,
    `duration` INT NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
     primary key(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `appointment`(
	`id` VARCHAR(36) NOT NULL,
    `appointment_number` VARCHAR(8), 
    `start` DATETIME NOT NULL, 
    `end` DATETIME NOT NULL, 
    `total_price` DECIMAL(10,2) NOT NULL ,
    `canceled` BOOL NOT NULL, 
    `user_id` VARCHAR(36) NOT NULL,
    `employee_id`  VARCHAR(36) NOT NULL,
    `agency_id` VARCHAR(36) NOT NULL,
    primary key(`id`),
    FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`),
	FOREIGN KEY(`user_id`) REFERENCES `user`(`id`),
    FOREIGN KEY(`agency_id`) REFERENCES `agency`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `verification`(
	`id` VARCHAR(36) NOT NULL,
    `token` VARCHAR(300) NOT NULL, 
    `created` TIMESTAMP NOT NULL, 
    `employee_id` VARCHAR(36) NOT NULL,
     primary key(`id`),
	 FOREIGN KEY(`employee_id`) REFERENCES `employee`(`id`)
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



INSERT INTO `agency` (id,agency_name,duration,price) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c1','haircut',30,10); 
INSERT INTO `agency` (id,agency_name,duration,price) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c2','Shave',10,5); 
INSERT INTO `agency` (id,agency_name,duration,price) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c3','Beard Trim',5,2); 
INSERT INTO `agency` (id,agency_name,duration,price) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c4','Hair Coloring',30,30);

INSERT INTO `employee` (`id`,`first_name`,`last_name`,`email`,`phone_number`,`password`,`role`,`email_verified`) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c5','Christos','Maltezos','xristosmalt@hotmail.com','6980259394','$2a$12$N6NK63LE9ry6.TSmfvU1VuWaRKUDPp2gKuZvJfJ1iWndsGFg3pyO2','ROLE_USER',1);
INSERT INTO `employee` (`id`,`first_name`,`last_name`,`email`,`phone_number`,`password`,`role`,`email_verified`) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c6','Kwstas','Papas','passmalt@hotmail.com','6980259394','$2a$12$N6NK63LE9ry6.TSmfvU1VuWaRKUDPp2gKuZvJfJ1iWndsGFg3pyO2','ROLE_USER',1);
INSERT INTO `employee` (`id`,`first_name`,`last_name`,`email`,`phone_number`,`password`,`role`,`email_verified`) VALUES('9e949b1b-14e3-4973-a959-5e1b34fc99c7','Nikos','Stroug','stroug@hotmail.com','6980259394','$2a$12$N6NK63LE9ry6.TSmfvU1VuWaRKUDPp2gKuZvJfJ1iWndsGFg3pyO2','ROLE_USER',1);



select * 
from schedule; 










