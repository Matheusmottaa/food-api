create table orders ( 
	id bigint not null auto_increment, 
	restaurant_id bigint not null, 
	customer_id bigint not null, 
	address_city_id bigint not null, 
	type_payment_id bigint not null, 
	
	delivery_tax decimal(19, 2) not null, 
	total_value decimal(19, 2) not null, 
	subtotal decimal(19, 2) not null, 
	
	
	address_complement varchar(100) not null, 
	address_neighborhood varchar(100) not null, 
	address_number varchar(100) not null, 
	address_street varchar(100) not null, 
	address_zip_code varchar(40) not null, 
	
	
	created_at datetime not null, 
	confirmed_at datetime null, 
	cancelled_at datetime null, 
	delivery_at datetime null,
	status varchar(10) not null, 
	
	constraint pk_order primary key(id), 
	
	constraint fk_order_restaurant foreign key (restaurant_id) 
	references restaurant (id), 
	
	constraint fk_order_customer foreign key(customer_id)
	references user (id), 
	
	constraint fk_order_address_city foreign key (address_city_id) 
	references city (id), 
	
	constraint fk_order_type_payment foreign key (type_payment_id) 
	references type_payment (id)
	
	
	)engine=InnoDB default charset=utf8; 

create table order_item ( 
	id bigint not null auto_increment, 
	order_id bigint not null, 
	product_id bigint not null, 
	quantity smallint(6) not null, 
	unit_price decimal(19, 2) not null, 
	total_amount decimal(19, 2) not null, 
	order_note varchar(255),
	
	constraint pk_order_item primary key(id), 
	
	constraint pk_order_item_order foreign key (order_id) 
	references orders (id), 
	
	constraint pk_order_item_product foreign key (product_id)
	references product (id)
)engine=InnoDB default charset=utf8; 