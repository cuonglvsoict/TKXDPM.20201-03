use EcobikeRental;
create table Account(
	username varchar(64) not null primary key,
    password text not null,
    customer_id varchar(64)
);

create table Customer(
	id varchar(64) not null primary key,
    fullname varchar(64) not null,
    gender varchar(5) not null,
    dateofbirth date not null,
    email varchar(64) not null,
    phone varchar(11)
); 
alter table Account add foreign key(customer_id) references Customer(id);
create table Bike(
	barcode varchar(64) not null primary key,
    soghe int,
    price int not null,
    category enum('xedon','xedoi','xedien'),
    isrent boolean not null
);
create table RentBike(
	id int not null auto_increment primary key,
    barcode varchar(64) not null,
    customer_id varchar(64) not null,
    foreign key(barcode) references Bike(barcode),
    foreign key(customer_id) references Customer(id),
    timerent int 
);