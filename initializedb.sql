drop table if exists accounts;
drop table if exists users;


drop type Permission;
drop type AccountStatuses;
-----------------------------------------------------------------------------------------------------------------------------------------
create type Permission as enum('customer', 'employee', 'admin');
create type Status as enum('pending', 'open', 'closed');
create table users(id serial primary key, userName text, password text, firstName text, lastName text, permission text);
create table accounts(id serial primary key, primaryUserId int references users(id), jointUserId int references users(id), status text, balance float);
