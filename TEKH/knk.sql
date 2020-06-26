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

SET FOREIGN_KEY_CHECKS=ON;

create table users(
username varchar (40) primary key,
password varchar (30),
roli varchar (30)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into users values ('enis.1','Berisha1','menaxher');
select * from users;

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

delete from users where id=4;


create table faturat(
id int auto_increment primary key,
personi varchar(50),
koha datetime,
shuma double)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table mbrendafatures(
id int,
produkti varchar(30),
cmimi double,
foreign key(id) references faturat(id))ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table lastLogged(
username varchar(50),
kohakyqjes datetime)ENGINE=InnoDB DEFAULT CHARSET=utf8;
