create table user_role (
    user_id int8 not null,
    role varchar(255)
);

create table usr (
    id int8 not null,
    login varchar(255),
    password varchar(255),
    primary key (id)
);

CREATE SEQUENCE user_sequence START 1 INCREMENT 1 OWNED BY usr.id;

alter table if exists user_role
    add constraint fk_user_role
    foreign key (user_id) references usr;