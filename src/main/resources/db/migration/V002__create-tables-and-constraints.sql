create table group_permission (
	group_id bigint not null, 
	permission_id bigint not null
) engine=InnoDB default charset=utf8;

create table permission (
	id bigint not null auto_increment, 
	description varchar(150) not null, 
	name varchar(80) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment, 
	active bit not null, 
	description varchar(150) not null, 
	name varchar(60) not null, 
	price decimal(19,2) not null, 
	restaurant_id bigint not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment, 
	active bit, 
	address_complement varchar(100), 
	address_neighborhood varchar(100), 
	address_number varchar(100), 
	address_street varchar(100), 
	address_zip_code varchar(40), 
	delivery_tax decimal(19,2) not null, 
	name varchar(80) not null, 
	open bit, 
	register_date datetime not null,
	updated_date datetime not null, 
	address_city_id bigint, 
	kitchen_id bigint, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_type_payments (
	restaurant_id bigint not null, 
	type_payment_id bigint not null
) engine=InnoDB default charset=utf8;

create table tb_group (
	id bigint not null auto_increment, 
	name varchar(80) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;


create table type_payment (
	id bigint not null auto_increment, 
	description varchar(150) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment, 
	created_at datetime, 
	email varchar(80) not null, 
	name varchar(80) not null, 
	password varchar(80) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_group (
	user_id bigint not null, 
	group_id bigint not null
) engine=InnoDB default charset=utf8;

alter table group_permission add constraint FKss14p30qbokhpkpdov4ha3wro foreign key (permission_id) references permission (id);
alter table group_permission add constraint FKd7btwx2xgubeiiygwwbmax16l foreign key (group_id) references tb_group (id);
alter table product add constraint FKp4b7e36gck7975p51raud8juk foreign key (restaurant_id) references restaurant (id);
alter table restaurant add constraint FK8pcwgn41lfg43d8u82ewn3cn foreign key (address_city_id) references city (id);
alter table restaurant add constraint FKrur1dqx79jim8s8dvdp16qc3d foreign key (kitchen_id) references kitchen (id);
alter table restaurant_type_payments add constraint FKihkes6g1f5nusghs9srv6rn2u foreign key (type_payment_id) references type_payment (id);
alter table restaurant_type_payments add constraint FKsfafok0wklj2qtnu5pkuhvgsu foreign key (restaurant_id) references restaurant (id);
alter table user_group add constraint FKbui3v81j0fckm2wna9hbacmcb foreign key (group_id) references tb_group (id);
alter table user_group add constraint FK1c1dsw3q36679vaiqwvtv36a6 foreign key (user_id) references user (id);