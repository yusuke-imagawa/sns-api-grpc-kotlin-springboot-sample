
CREATE DATABASE IF NOT EXISTS `talking` DEFAULT CHARACTER SET utf8mb4;

USE `talking`;

-- ユーザー作成
CREATE USER 'talking_user'@'%' IDENTIFIED BY 'aaaaaaaaaaa';
GRANT ALL PRIVILEGES ON `talking`.* TO 'talking_user'@'%';

CREATE USER 'talking_viewer'@'%' IDENTIFIED BY 'bbbbbbbbb';
GRANT SELECT ON `talking`.* TO 'talking_viewer'@'%';
FLUSH PRIVILEGES;

CREATE USER 'talking_staff'@'%' IDENTIFIED BY 'ccccccccccc';
GRANT SELECT, UPDATE ON `talking`.* TO 'talking_staff'@'%';
FLUSH PRIVILEGES;

-- ・ユーザー
CREATE TABLE IF NOT EXISTS `talking`.`users`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  -- profile
  `name` VARCHAR(20) NOT NULL COMMENT '名前',
  `gender_type` ENUM('male', 'female') NOT NULL DEFAULT 'male' COMMENT '性別',
  `age` INT UNSIGNED NULL COMMENT '年齢',
  `pr` TEXT DEFAULT NULL COMMENT '自己PR',
  -- status, auth
  `status` ENUM('enable', 'leave', 'force_leave') NOT NULL DEFAULT 'enable' COMMENT 'ステータス',
  `user_type` ENUM('u', 'c') NOT NULL DEFAULT 'u',
  `api_token` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(30) NULL,
  `last_online_datetime` DATETIME NOT NULL,
  `is_enabled_notify` BOOLEAN DEFAULT FALSE NOT NULL COMMENT 'プッシュ通知',
  -- common
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`name`),
  INDEX (`id`, `api_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- プロフィール画像
CREATE TABLE IF NOT EXISTS `talking`.`profile_images`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `file_name` VARCHAR(100) NULL,
  `exists_original` BOOLEAN DEFAULT FALSE NOT NULL,
  `exists_100` BOOLEAN DEFAULT FALSE NOT NULL,
  `exists_300` BOOLEAN DEFAULT FALSE NOT NULL,
  `exists_600` BOOLEAN DEFAULT FALSE NOT NULL,
  `status` ENUM('enable', 'deleted_by_user', 'deleted_by_admin') NOT NULL DEFAULT 'enable' COMMENT 'ステータス',
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `profile_images_fk_user_id` FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ・チャット
CREATE TABLE IF NOT EXISTS `talking`.`chat_messages`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_user_id` BIGINT UNSIGNED NOT NULL,
  `to_user_id` BIGINT UNSIGNED NOT NULL,
  `message` TEXT NOT NULL,
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `chat_messages_fk_from_user_id` FOREIGN KEY(`from_user_id`) REFERENCES `users`(`id`),
  CONSTRAINT `chat_messages_fk_to_user_id` FOREIGN KEY(`to_user_id`) REFERENCES `users`(`id`),
  INDEX (`from_user_id`, `to_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ・通話履歴
CREATE TABLE IF NOT EXISTS `talking`.`calling_logs`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_user_id` BIGINT UNSIGNED NOT NULL,
  `to_user_id` BIGINT UNSIGNED NOT NULL,
  `status` ENUM('starting', 'talking', 'end') NOT NULL DEFAULT 'starting' COMMENT 'ステータス',
  `start_datetime` DATETIME NOT NULL,
  `end_datetime` DATETIME NULL,
  `last_from_user_continue_datetime` DATETIME NULL COMMENT 'fromUserから最後にcontinue APIが呼び出された日時',
  `last_to_user_continue_datetime` DATETIME NULL COMMENT 'toUserから最後にcontinue APIが呼び出された日時',
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `calling_logs_fk_from_user_id` FOREIGN KEY(`from_user_id`) REFERENCES `users`(`id`),
  CONSTRAINT `calling_logs_fk_to_user_id` FOREIGN KEY(`to_user_id`) REFERENCES `users`(`id`),
  INDEX (`from_user_id`, `to_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 通報
CREATE TABLE IF NOT EXISTS `talking`.`reports`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `target_user_id` BIGINT UNSIGNED NOT NULL,
  `reporter_user_id` BIGINT UNSIGNED NOT NULL,
  `message` TEXT DEFAULT NULL,
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `reports_fk_target_user_id` FOREIGN KEY(`target_user_id`) REFERENCES `users`(`id`),
  CONSTRAINT `reports_fk_reporter_user_id` FOREIGN KEY(`reporter_user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ブロック
CREATE TABLE IF NOT EXISTS `talking`.`blocks`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_user_id` BIGINT UNSIGNED NOT NULL,
  `block_user_id` BIGINT UNSIGNED NOT NULL,
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `blocks_fk_from_user_id` FOREIGN KEY(`from_user_id`) REFERENCES `users`(`id`),
  CONSTRAINT `blocks_fk_block_user_id` FOREIGN KEY(`block_user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- プッシュ通知デバイストークン_iOS
CREATE TABLE IF NOT EXISTS `talking`.`push_token_ios`
(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `device_token` VARCHAR(100) NOT NULL,
  `created` DATETIME NOT NULL,
  `modified` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `push_token_ios_fk_user_id` FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;