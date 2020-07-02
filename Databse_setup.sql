CREATE DATABASE  IF NOT EXISTS `expense_tracker` ;

use  `expense_tracker`;


CREATE USER 'vijai'@'localhost' IDENTIFIED BY 'vijai';

GRANT ALL PRIVILEGES ON * . * TO 'vijai'@'localhost';
ALTER USER 'vijai'@'localhost' IDENTIFIED WITH mysql_native_password BY 'vijai';

CREATE TABLE `User_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
 
) ;

commit;

create TABLE `Category`(
 `category_id` int(11) NOT NULL AUTO_INCREMENT,
 `categort_name` varchar(45),
 primary key(`category_id`)
 );
 
 commit;
CREATE table `Expense`(
	`expense_id` int(11) NOT NULL ,
    `user_expense_id` int(11),
    `expense_category_id` int(11),
    `expense_amount` bigint(100) ,
    `expense_date` timestamp,
    `expense_added_date` timestamp,
    `expense_notes` varchar(2000),
     CONSTRAINT `fkey_user` FOREIGN KEY (`user_expense_id`) REFERENCES `User_info` (`id`),
     CONSTRAINT `fkey_category` FOREIGN KEY (`expense_category_id`) REFERENCES `Category` (`category_id`)
     );
    
 insert into `User_info` values
			(1,"vijai"),
            (2,"nirmal"),
            (3,"kalai");
            
insert into `Category` values
			(1,"Rent"),
            (2,"food"),
            (3,"EMi");
          
		insert into `Expense` values
			(1,1,1,5000,STR_TO_DATE('01/06/2020 11:15:45','%d/%m/%Y %H:%i:%s'),now(),"Rent for the moth june");
         
         commit;  
          