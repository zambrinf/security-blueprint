CREATE SCHEMA IF NOT exists myschema;

create table IF NOT exists myschema.my_users(
    username varchar(500) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table IF NOT exists myschema.my_authorities(
    username varchar(500) not null,
    authority varchar(500) not null,
    constraint fk_authorities_users foreign key(username) references myschema.my_users(username)
);

create unique index IF NOT exists ix_auth_username on myschema.my_authorities(username,authority);