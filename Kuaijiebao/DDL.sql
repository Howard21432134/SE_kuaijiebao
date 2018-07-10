-- Create syntax for TABLE 'book'
-- Create syntax for TABLE 'book'

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(15) DEFAULT NULL,
  `name` varchar(7) DEFAULT NULL,
  `identity` varchar(18) DEFAULT NULL,
  `job`  varchar(15) DEFAULT NULL,
  `income`  int DEFAULT NULL,
  `address`  text DEFAULT NULL,
  `introduction`  text DEFAULT NULL,
  `phone`  varchar(11) NOT NULL,
  `email`  varchar(25) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `user_id` bigint(10) NOT NULL,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `user` ( `user_id` ）
);
