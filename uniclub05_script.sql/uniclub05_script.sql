CREATE DATABASE uniclub;
USE uniclub;

CREATE TABLE tag(
	id int auto_increment,
	name varchar(100),
	primary key(id)
);

CREATE TABLE category(
	id int auto_increment,
	name varchar(50),
	image varchar(50),
	
	primary key(id)
);

CREATE TABLE tag_product(
	id_product int ,
	id_tag int,
	
	primary key(id_product,id_tag)
);

CREATE TABLE category_product(
	id_product int,
	id_category int,
	primary key(id_product,id_category)
);

CREATE TABLE product_image(
	id int auto_increment,
	id_product int,
	name varchar(100),
	
	primary key(id)
);

CREATE TABLE order_detail(
	id_order int,
	id_product int,
	id_size int,
	id_color int,
	quatity int,
	price double,
	status varchar(200),
	
	primary key(id_order,id_product, id_size, id_color)
	
);

CREATE TABLE product(
	id int auto_increment,
	name varchar(100),
	price double,
	rate int,
	description text,
	sku varchar(20),
	
	primary key(id)
);

CREATE TABLE orders(
	id int auto_increment,
	id_user int,
	full_name varchar(100),
	address varchar(255),
	phone varchar(12),
	total double,
	create_date timestamp default now(),
	status varchar(20),

	primary key(id)
);

CREATE TABLE cart(
	id int auto_increment,
	id_user int,
	id_product int,
	id_size int,
	id_color int,
	quantity int,
	
	primary key(id)
);

CREATE TABLE size(
	id int auto_increment,
	name varchar(100),
	
	primary key(id)
);

CREATE TABLE product_detail(
	id_product int,
	id_size int,
	id_color int,
	quantity int,
	price double,
	
	primary key(id_product,id_size,id_color)
);

CREATE TABLE users(
	id int auto_increment,
	email varchar(100) unique,
	password varchar(255),
	id_role int,
	
	primary key(id)
);

CREATE TABLE roles(
	id int auto_increment,
	name varchar(50),
	
	primary key(id)
);

CREATE TABLE color(
	id int auto_increment,
	name varchar(50),
	
	primary key(id)
);

ALTER TABLE users ADD CONSTRAINT FK_id_role_users FOREIGN KEY(id_role) REFERENCES roles(id);
ALTER TABLE cart ADD CONSTRAINT FK_id_user_cart FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE cart ADD CONSTRAINT FK_id_product_cart FOREIGN KEY(id_product) REFERENCES product(id);
ALTER TABLE cart ADD CONSTRAINT FK_id_size_cart FOREIGN KEY(id_size) REFERENCES size(id);
ALTER TABLE cart ADD CONSTRAINT FK_id_color_cart FOREIGN KEY(id_color) REFERENCES color(id);
ALTER TABLE product_detail ADD CONSTRAINT FK_id_product_product_detail FOREIGN KEY(id_product) REFERENCES product(id);
ALTER TABLE product_detail ADD CONSTRAINT FK_id_color_product_detail FOREIGN KEY(id_color) REFERENCES color(id);
ALTER TABLE product_detail ADD CONSTRAINT FK_id_size_product_detail FOREIGN KEY(id_size) REFERENCES size(id);
ALTER TABLE orders ADD CONSTRAINT FK_id_user_orders FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE order_detail ADD CONSTRAINT FK_id_product_order_detail FOREIGN KEY(id_product) REFERENCES product(id);
ALTER TABLE order_detail ADD CONSTRAINT FK_id_size_order_detail FOREIGN KEY(id_size) REFERENCES size(id);
ALTER TABLE order_detail ADD CONSTRAINT FK_id_color_order_detail FOREIGN KEY(id_color) REFERENCES color(id);
ALTER TABLE order_detail ADD CONSTRAINT FK_id_order_order_detail FOREIGN KEY(id_order) REFERENCES orders(id);
ALTER TABLE tag_product ADD CONSTRAINT FK_id_product_tag_product FOREIGN KEY(id_product) REFERENCES product(id);
ALTER TABLE tag_product ADD CONSTRAINT FK_id_tag_tag_product FOREIGN KEY(id_tag) REFERENCES tag(id);
ALTER TABLE category_product ADD CONSTRAINT FK_id_product_category_product FOREIGN KEY(id_product) REFERENCES product(id);
ALTER TABLE category_product ADD CONSTRAINT FK_id_category_category_product FOREIGN KEY(id_category) REFERENCES category(id);
ALTER TABLE product_image ADD CONSTRAINT Fk_id_product_product_image FOREIGN KEY(id_product) REFERENCES product(id);

ALTER TABLE product_image ADD COLUMN id_color INT, ADD CONSTRAINT fk_id_color_product_image FOREIGN KEY (id_color) REFERENCES color(id);

ALTER TABLE category DROP COLUMN image;

DROP TABLE category_product;

ALTER TABLE orders ADD COLUMN message varchar(100);

ALTER TABLE product ADD COLUMN id_category INT, ADD CONSTRAINT Fk_id_category_product FOREIGN KEY (id_category) REFERENCES category(id);






