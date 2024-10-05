INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (1, 'Jan 1', 'New Year''s Day', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (2, 'Oct 31', 'Halloween', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (3, 'Nov 24', 'Thanksgiving Day', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (4, 'Dec 25', 'Christmas', 'FESTIVAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (5, 'Jan 17', 'Martin Luther King Jr. Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (6, 'July 4', 'Independence Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (7, 'Sep 5', 'Labor Day', 'FEDERAL', CURDATE(), 'DBA');

INSERT INTO `holidays` (`id`, `day`, `reason`, `type`, `created_at`, `created_by`)
VALUES (8, 'Nov 11', 'Veterans Day', 'FEDERAL', CURDATE(), 'DBA');


INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
VALUES ('STUDENT',CURDATE(),'DBA');


INSERT INTO `person` (`name`,`email`,`mobile_number`,`password`,`role_id`,`created_at`, `created_by`)
VALUES ('Admin','admin@gmail.com','3443434343','$2a$12$FKsNqMMnmNO9oTJZcpx3x.zC0vBuKP2cyrXgu27TxUDieLTQFBHWW', 1 ,CURDATE(),'DBA');


SHOW INDEX FROM person WHERE Key_name = 'UKblkytayhlgyjt5xbhj0do1i4k';



ALTER TABLE `contact_msg` ADD COLUMN `contact_id` INT AUTO_INCREMENT PRIMARY KEY;

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (2, 'Adam', '2176436587', 'zadam@gmail.com', 'Regarding a job', 'Wanted to join as teacher', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (3, 'Zara', '3412654387', 'zarabaig@hotmail.com', 'Course Admission', 'Wanted to join a course', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (4, 'Marques', '8547643673', 'kmarques@yahoo.com', 'Course Review', 'Review of Development course', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (5, 'Shyam', '4365328776', 'gshyam@gmail.com', 'Admission Query', 'Need to talk about admission', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (6, 'John', '5465765453', 'doejohn@gmail.com', 'Holiday Query', 'Query on upcoming holidays', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (7, 'Taniya Bell', '3987463827', 'belltaniya@gmail.com', 'Child Scholarship', 'Can my child get scholarship?', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (8, 'Willie Lara', '4568764801', '476lara@gmail.com', 'Need Admission', 'My son need an admission', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (9, 'Jonathan Parsons', '4321768902', 'jonathan.parsons@gmail.com', 'Course feedback', 'Music course is good', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (10, 'Cloe Rubio', '9854438719', 'rubio987@gmail.com', 'Correct Date of Birth', 'My Child DOB needs to be corrected', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (11, 'Camilla Stein', '6545433254', 'camillas@gmail.com', 'Transport Query', 'Is Transport provided?', 'Open', CURDATE(), 'DBA');

INSERT INTO `contact_msg` (`contact_id`, `name`, `mobile_num`, `email`, `subject`, `message`, `status`, `created_at`, `created_by`)
VALUES (12, 'Lizeth Gross', '4678783434', 'grossliz@yahoo.com', 'Progress report', 'Please send progress report', 'Open', CURDATE(), 'DBA');
