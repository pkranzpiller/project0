drop table if exists customer;
drop table if exists account;
drop table if exists admin;
drop table if exists employee;


create table account(id serial primary key, username text, password text, fname text, lname text);
create table customer(id integer references account(id), balance numeric(100,2));
/*
create table admin(id serial primary key, username text);
create table employee(id serial primary key, username text, employeeID serial);
*/