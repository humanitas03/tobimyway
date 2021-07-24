CREATE DATABASE IF NOT EXISTS myspring;

CREATE TABLE users (
    id varchar(100) primary key,
    name varchar(20) not null,
    password varchar(10) not null,
    level tinyint not null,
    login int not null,
    recommend int not null,
    email varchar(255) not null
)