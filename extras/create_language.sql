CREATE DATABASE workout_db;

CREATE TABLE `users` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(45) NOT NULL COLLATE 'latin1_swedish_ci',
	`first_name` VARCHAR(45) NOT NULL COLLATE 'latin1_swedish_ci',
	`last_name` VARCHAR(45) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
	`password` VARCHAR(64) NOT NULL COLLATE 'latin1_swedish_ci',
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4;


CREATE TABLE `exercises` (
	`e_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL COLLATE 'latin1_swedish_ci',
	`reps` BIGINT(20) NOT NULL,
	`sets` BIGINT(20) NOT NULL,
	`type` VARCHAR(45) NOT NULL COLLATE 'latin1_swedish_ci',
	`user_id` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`e_id`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4;