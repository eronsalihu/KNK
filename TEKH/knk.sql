create database tech;
use tech;

create table employees(
id int auto_increment primary key,
name varchar (30),
surname varchar (40),
roli varchar (30),
data_fillimit datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from employees;
select * from users;

SET FOREIGN_KEY_CHECKS=ON;

create table users(
username varchar (40) primary key,
password varchar (30),
roli varchar (30)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table employees modify data_fillimit date;


create table exEmployees(
id int primary key,
emri varchar (30),
mbiemri varchar (40),
data_fillimit datetime,
data_mbarimit datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table exEmployees modify data_fillimit date;
alter table exEmployees modify data_mbarimit date;

select * from exEmployees;


create table stock(
id int auto_increment primary key,
lloji varchar (30),
modeli varchar (30),
sasia int,
cmimi double
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table lastLogged(
username varchar(50),
kohakyqjes datetime)ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from lastLogged;
select * from exEmployees;

create table faturat(
id int auto_increment primary key,
personi varchar(50),
koha datetime,
shuma double)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table signedOut(
username varchar(50),
kohadaljes datetime)Engine=InnoDB default Charset=utf8;
select * from signedOut;

create table mbrendafatures(
id int,
produkti varchar(30),
cmimi double,
foreign key(id) references faturat(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table orders(
name varchar(30),
id integer ,
koha datetime,
price double,
VAT double,
total double,
aprovuar varchar(25)
);
insert into orders values('fatura',3,'2020-03-20 23:19:18',82.1,18,82.1,'a');
select * from orders;
CREATE TABLE `products` (
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `inventory` varchar(30) DEFAULT NULL,
  `category` varchar(30) DEFAULT NULL,
  `dateAndTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;