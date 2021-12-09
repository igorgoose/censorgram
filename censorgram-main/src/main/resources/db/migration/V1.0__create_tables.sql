create table user_statuses
(
    id          serial primary key,
    code        varchar(50) not null,
    description varchar(200)
);

create table users
(
    id            bigserial primary key,
    username      varchar(100) not null,
    password      text         not null,
    email         varchar(150) not null,
    dob           date,
    register_date date not null,
    status_id     int          not null references user_statuses (id)
);

create table followers (
    followed_user_id bigint primary key references users (id),
    follower_id bigint primary key references users (id)
);

create table authorities
(
    id          serial primary key,
    code        varchar(50) not null,
    description varchar(200)
);

create table roles
(
    id          serial primary key,
    code        varchar(50) not null,
    description varchar(200)
);

create table roles_to_authorities
(
    role_id      int not null primary key references roles (id),
    authority_id int not null primary key references authorities (id)
);

create table post_statuses
(
    id          serial primary key,
    code        varchar(50) not null,
    description varchar(200)
);

create table posts
(
    id           bigserial primary key,
    user_id      bigint    not null references users (id),
    text         text,
    created_date timestamp not null,
    updated_date timestamp,
    status_id    int       not null references post_statuses (id)
);

create table content_analytics
(
    post_id     bigint primary key references posts (id),
    toxic       boolean not null,
    probability real    not null
);