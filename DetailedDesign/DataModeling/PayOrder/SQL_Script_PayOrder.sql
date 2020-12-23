-- create database EcoBikeRental;
use ecobikerental;
create table BankAccount (
	account_id varchar(64),
    balance long,
    PRIMARY KEY (account_id)
);

create table Card (
	card_id varchar(64),
	account_id varchar(64),
    security_code varchar(64),
    PRIMARY KEY (card_id),
    FOREIGN KEY (account_id) REFERENCES BankAccount(account_id)
);

create table PaymentTransactionHistory (
	transaction_id varchar(64),
    transaction_status bool,
    card_id varchar(64),
    transaction_code varchar(64),
    transaction_value long,
    transaction_time timestamp,
    transaction_content varchar(255),
    PRIMARY KEY (transaction_id)
);