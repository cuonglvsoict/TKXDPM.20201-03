create table transaction (
	transaction_id INT NOT NULL AUTO_INCREMENT,
    card_id VARCHAR(64) NOT NULL,
    bike_id VARCHAR(64) NOT NULL,
    createAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    command VARCHAR(16),
    content VARCHAR(255),
    PRIMARY KEY (transaction_id)
);

create table current_orders (
    card_id VARCHAR(64) NOT NULL unique,
    bike_id VARCHAR(64) NOT NULL,
    createAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (card_id, bike_id)
);
create table bike (
    bike_id INT NOT NULL AUTO_INCREMENT,
    station_id VARCHAR(64) NOT NULL,
    bike_type INT NOT NULL,
    available BOOLEAN NOT NULL,
    PRIMARY KEY (bike_id)
);
create table station (
    station_id INT NOT NULL AUTO_INCREMENT,
    station_name VARCHAR(64),
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (station_id)
);
ALTER TABLE station
ADD FOREIGN KEY (station_id) REFERENCES bike(bike_id);
ALTER TABLE current_orders
ADD FOREIGN KEY (bike_id) REFERENCES bike(bike_id);
ALTER TABLE transaction
ADD FOREIGN KEY (bike_id) REFERENCES bike(bike_id);
