create table kitchen ( 
	id bigint not null auto_increment, 
	name varchar(60) not null, 
	constraint pk_kitchen primary key (id)
)engine=InnoDB default charset=utf8;

create table state (
	id bigint not null auto_increment, 
	name varchar(80) not null, 
	constraint pk_state primary key (id)
)engine=InnoDB default charset=utf8; 

create table city (
	id bigint not null auto_increment, 
	state_id bigint not null, 
	name varchar(80) not null,
	constraint pk_city primary key(id),
	constraint fk_city_state foreign key (id)
	references state (id)
)engine=InnoDB default charset=utf8; 