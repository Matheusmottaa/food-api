set foreign_key_checks=0;

delete from kitchen; 
delete from restaurant; 
delete from group_permission; 
delete from state; 
delete from city; 
delete from product; 
delete from permission; 
delete from restaurant_type_payments; 
delete from tb_group; 
delete from type_payment; 
delete from user; 
delete from user_group; 

set foreign_key_checks=0;

alter table kitchen auto_increment = 1; 
alter table restaurant auto_increment = 1;
alter table permission auto_increment = 1; 
alter table product auto_increment = 1;
alter table restaurant_type_payments auto_increment = 1; 
alter table group_permission auto_increment = 1;  
alter table tb_group auto_increment = 1; 
alter table type_payment auto_increment = 1; 
alter table user auto_increment = 1; 
alter table state auto_increment = 1; 
alter table city auto_increment = 1; 
alter table user_group auto_increment = 1; 



INSERT INTO KITCHEN (`NAME`) VALUES ('Brasileira');
INSERT INTO KITCHEN (`NAME`) VALUES ('Chinesa');
INSERT INTO KITCHEN (`NAME`) VALUES ('Japonesa');

INSERT IGNORE INTO `food_db`.`state` (`name`) VALUES ('NEW YORK');
INSERT INTO `food_db`.`state` (`name`) VALUES ('CALIFORNIA');
INSERT INTO `food_db`.`state` (`name`) VALUES ('MINAS GERAIS');
INSERT INTO `food_db`.`state` (`name`) VALUES ('SAO PAULO');

INSERT INTO  `food_db`.`city` (`name`, `state_id`) VALUES ('NEW YORK', 1);
INSERT INTO `food_db`.`city` (`name`, `state_id`) VALUES ('SAN FRANCISCO', 2);
INSERT INTO `food_db`.`city` (`name`, `state_id`) VALUES ('LOS ANGELES', 2);
INSERT INTO `food_db`.`city` (`name`, `state_id`) VALUES ('BELO HORIZONTE', 3);
INSERT INTO `food_db`.`city` (`name`, `state_id`) VALUES ('GOVERNADOR VALADARES', 3);
INSERT INTO `food_db`.`city` (`name`, `state_id`) VALUES ('SAO PAULO', 4);


INSERT INTO type_payment (`description`) VALUES ('CREDIT');
INSERT INTO type_payment (`description`) VALUES ('DEBIT');
INSERT INTO type_payment (`description`) VALUES ('PIX');

INSERT INTO RESTAURANT (NAME, DELIVERY_TAX, KITCHEN_ID, register_date, updated_date) VALUES ('Fogao Mineiro', 8.60, 1, now(), now()); 
INSERT INTO RESTAURANT (NAME, DELIVERY_TAX, KITCHEN_ID, register_date, updated_date) VALUES ('Chinabox', 10.00, 2, now(), now()); 
INSERT INTO RESTAURANT (NAME, DELIVERY_TAX, KITCHEN_ID, register_date, updated_date) VALUES ('OHKMI Food', 12.00, 3, now(), now());
INSERT INTO RESTAURANT (NAME, DELIVERY_TAX, KITCHEN_ID, ADDRESS_NUMBER, ADDRESS_STREET, ADDRESS_ZIP_CODE, ADDRESS_NEIGHBORHOOD, REGISTER_DATE, updated_date) VALUES ('OKA food', 5.00, 3, '105', 'Rua niquelina', '34567', 'Savassi', now(), now());

insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 1);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 1);
insert into PRODUCT (NAME, DESCRIPTION, PRICE, ACTIVE, RESTAURANT_ID) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 1);

INSERT INTO permission (`name`, `description`) VALUES ('NIVEL 1', 'DIRECTOR PERMISSION'); 
INSERT INTO permission (`name`, `description`) VALUES ('NIVEL 2', 'MANAGER PERMISSION'); 
INSERT INTO permission (`name`, `description`) VALUES ('NIVEL 3', 'TECNICAL PERMISSION');

INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (1,1);
INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (1,2);
INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (1,3);
INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (2,1);
INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (2,3);
INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (3,1);
INSERT INTO food_db.restaurant_type_payments(restaurant_id, type_payment_id) VALUES (3,2);
