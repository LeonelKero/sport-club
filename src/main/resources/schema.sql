create table if not exists cycling (
id int not null,
title varchar(250) not null,
start_on timestamp not null,
completed_on timestamp not null,
distance double not null,
location varchar(20) not null,
primary key (id)
);