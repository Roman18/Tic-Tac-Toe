create database scorebase;


create table if not exists `score`(
`id` int not null primary key auto_increment,
`name` varchar(30) not null,
`rating` int not null

);