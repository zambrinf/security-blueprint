create table my_users(
    username varchar(500) not null primary key,
    password varchar(500) not null,
    enabled boolean not null
);

create table my_authorities(
    username varchar(500) not null,
    authority varchar(500) not null,
    constraint fk_authorities_users foreign key(username) references my_users(username)
);

create unique index ix_auth_username on my_authorities(username,authority);