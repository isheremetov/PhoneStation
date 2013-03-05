drop table if exists dialogues;
drop table if exists dialoguesHistory;
drop table if exists subscribes;
drop table if exists subscribesHistory;
drop table if exists users;
drop table if exists services;
drop trigger if exists copyDeletedSubscribeToHistory;
drop trigger if exists copyDeletedDialogueToHistory;

create table users (
	id int primary key auto_increment,
	phone varchar(9) not null unique,
	password varchar(32) not null,
	username varchar(16) not null unique,
	email varchar(32) not null unique,
	purse float not null,
	role varchar(5) not null,
	active bool not null default 1
) Engine = InnoDB;

create table services (
	id int primary key auto_increment,
	name varchar(32) not null,
	description text,
	price int not null,
	active bool not null default 1
) Engine = InnoDB;

create table subscribes(
	id int primary key auto_increment,
	userid int not null,
	serviceid int not null,
	activationDate datetime not null,
	FOREIGN KEY(userid) REFERENCES users(id),
	FOREIGN KEY(serviceid) REFERENCES services(id)
) Engine = InnoDB;

create table subscribesHistory(
	id int primary key auto_increment,
	oldId int not null,
	userid int not null,
	serviceid int not null,
	activationDate datetime not null,
	deactivationDate datetime not null,
	FOREIGN KEY(userid) REFERENCES users(id),
	FOREIGN KEY(serviceid) REFERENCES services(id)
) Engine = InnoDB;

create table dialogues (
	id int primary key auto_increment,
	dialogueDate datetime not null,
	duration int not null,
	destination varchar(9) not null,
	userId int not null,
	serviceId int not null,
	FOREIGN KEY(userid) REFERENCES users(id),
	FOREIGN KEY(serviceid) REFERENCES services(id)
) Engine = InnoDB;

create table dialoguesHistory (
	id int primary key auto_increment,
	oldId int not null,
	dialogueDate datetime not null,
	duration int not null,
	destination varchar(9) not null,
	userId int not null,
	serviceId int not null,
	FOREIGN KEY(userid) REFERENCES users(id),
	FOREIGN KEY(serviceid) REFERENCES services(id)
) Engine = InnoDB;

DELIMITER |

CREATE TRIGGER copyDeletedSubscribeToHistory before delete ON subscribes
FOR EACH ROW BEGIN
insert into subscribesHistory (oldId, userid, serviceid, activationDate, deactivationDate) 
	values(old.id, old.userid, old.serviceid, old.activationDate, now());
END|

CREATE TRIGGER copyDeletedDialogueToHistory before delete ON dialogues
FOR EACH ROW BEGIN
insert into dialoguesHistory (oldId, dialogueDate, duration, destination, userid, serviceid) 
	values(old.id, old.dialogueDate, old.duration, old.destination, old.userid, old.serviceid);
END|



DELIMITER ;

insert into users (phone, password, username, email, purse, role, active) 
	values('111111111', md5('admin'), 'admin', 'admin@adm.com', 0, 'Admin', 1);
insert into users (phone, password, username, email, purse, role, active) 
	values('111111112', md5('user'), 'user', 'user@usr.com', 10000, 'User', 1);

insert into services(name, description, price, active) 
	values('Городской звонок', 'Предоставаляет возможность звонить на городские номера', 3, 1);
insert into services(name, description, price, active) 
	values('Междугородный звонок', 'Предоставаляет возможность звонить на номера в других городах РБ', 4, 1);
insert into services(name, description, price, active) 
	values('Международный звонок', 'Предоставаляет возможность звонить во все страны мира', 10, 1);
insert into services(name, description, price, active) 
	values('Звонок на мобильный', 'Предоставаляет возможность звонить на мобильные телефоны в пределах РБ', 7, 1);
insert into services(name, description, price, active) 
	values('Звонки в роуминг', 'Предоставаляет возможность звонить на мобильные телефоны за пределами РБ',  12, 0);

insert into subscribes (userid, serviceid, activationDate) 
	values(2, 1, now());
insert into subscribes (userid, serviceid, activationDate) 
	values(2, 2, now());