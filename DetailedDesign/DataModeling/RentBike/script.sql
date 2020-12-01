use EcobikeRental;
create table Customer(
    id INT NOT NULL AUTO_INCREMENT,
    fullname varchar(64) not null,
    gender varchar(5) not null,
    dateofbirth date not null,
    email varchar(64) not null,
    phone varchar(11),
    username varchar(64) not null,
    password text not null
    primary key(id)
); 
create table Bike(
    barcode varchar(64) not null primary key,
    soghe int,
    price int not null,
    category enum('xedon','xedoi','xedien'),
    isrent boolean not null,
    batery int,
    station int
);
create table RentBike(
    id int not null auto_increment primary key,
    barcode varchar(64) not null,
    customer_id int not null,
    foreign key(barcode) references Bike(barcode),
    foreign key(customer_id) references Customer(id),
    timerent int 
);
CREATE TABLE `EcobikeRental`.`Station` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `numberofbike` INT NOT NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `EcobikeRental`.`Bike` 
ADD INDEX `station_idx` (`station` ASC);
;
ALTER TABLE `EcobikeRental`.`Bike` 
ADD CONSTRAINT `station`
  FOREIGN KEY (`station`)
  REFERENCES `EcobikeRental`.`Station` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
