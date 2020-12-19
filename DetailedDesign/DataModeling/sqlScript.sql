create table transaction (
	transaction_id INT NOT NULL AUTO_INCREMENT,
    card_id VARCHAR(64) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount INT NOT NULL,
    order_id int not null,
    command VARCHAR(16) NOT NULL,
    content VARCHAR(255),
    PRIMARY KEY (transaction_id)
);

create table rental_orders (
	order_id INT NOT NULL AUTO_INCREMENT,
    card_id VARCHAR(64) NOT NULL,
    bike_id VARCHAR(32) NOT NULL,
    start_time DATETIME,
    return_time DATETIME,
    is_return BOOL default FALSE,
    PRIMARY KEY (order_id)
);
create table bike (
    bike_id VARCHAR(32) NOT NULL,
    station_id VARCHAR(32) NOT NULL,
    bike_name VARCHAR(64),
    bike_type INT NOT NULL,
    available BOOLEAN NOT NULL,
    PRIMARY KEY (bike_id)
);
create table station (
    station_id VARCHAR(32) NOT NULL,
    station_name VARCHAR(64),
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (station_id)
);
ALTER TABLE bike
ADD FOREIGN KEY (station_id) REFERENCES station(station_id);
ALTER TABLE rental_orders
ADD FOREIGN KEY (bike_id) REFERENCES bike(bike_id);
ALTER TABLE transaction
ADD FOREIGN KEY (order_id) REFERENCES rental_orders(order_id);

insert into station (station_id, station_name, address) values ('S001', 'STATION_001', '1600 Pennsylvania Avenue NW Washington, D.C');
insert into station (station_id, station_name, address) values ('S002', 'STATION_002', '1 Infinite Loop Cupertino, CA 95014');
insert into station (station_id, station_name, address) values ('S003', 'STATION_003', 'Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France');

insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('RB001', 'S001', 'Road Bike', 1, TRUE);
insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('RB002', 'S002', 'Road Bike', 1, TRUE);
insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('RB003', 'S003', 'Road Bike', 1, TRUE);

insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('TB001', 'S001', 'Tandem Bike', 2, TRUE);
insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('TB002', 'S002', 'Tandem Bike', 2, TRUE);
insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('TB003', 'S003', 'Tandem Bike', 2, TRUE);

insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('ETB001', 'S001', 'Electric Tandem Bike', 3, TRUE);
insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('ETB002', 'S002', 'Electric Tandem Bike', 3, TRUE);
insert into bike (bike_id, station_id, bike_name, bike_type, available) values ('ETB003', 'S003', 'Electric Tandem Bike', 3, TRUE);


