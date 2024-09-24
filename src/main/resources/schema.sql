create table if not exists cycling (
id int auto_increment not null,
title varchar(250) not null,
started_on timestamp not null,
completed_on timestamp not null,
distance double not null,
location varchar(20) not null,
primary key (id)
);