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
    role_id      int not null references roles (id),
    authority_id int not null references authorities (id),
    primary key (role_id, authority_id)
);

create table user_statuses
(
    id          serial primary key,
    code        varchar(50) not null,
    description varchar(200)
);

create table users
(
    id            bigserial primary key,
    username      varchar(100) not null unique,
    password      text         not null,
    email         varchar(150) not null,
    dob           date,
    register_date date         not null,
    status_id     int          not null references user_statuses (id),
    role_id       int          not null references roles (id)
);

create table followers
(
    followed_user_id bigint references users (id),
    follower_id      bigint references users (id),
    primary key (followed_user_id, follower_id)
);

create table post_statuses
(
    id          serial primary key,
    code        varchar(50) not null,
    color       varchar(6)  not null,
    description varchar(200)
);

create table posts
(
    id           uuid primary key,
    user_id      bigint    not null references users (id),
    text         text      not null,
    created_date timestamp not null,
    updated_date timestamp,
    status_id    int       not null references post_statuses (id)
);

create table comment_statuses
(
    id          serial primary key,
    code        varchar(50) not null,
    color       varchar(6)  not null,
    description varchar(200)
);

create table comments
(
    id           uuid primary key,
    user_id      bigint    not null references users (id),
    post_id      uuid      not null references posts (id),
    text         text,
    created_date timestamp not null,
    updated_date timestamp,
    status_id    int       not null references comment_statuses (id)
);

create table post_analytics
(
    id           bigserial primary key,
    post_id      uuid      not null references posts (id),
    created_date timestamp not null,
    toxic        boolean   not null,
    probability  real      not null
);

create table comment_analytics
(
    id           bigserial primary key,
    comment_id   uuid      not null references comments (id),
    created_date timestamp not null,
    toxic        boolean   not null,
    probability  real      not null
);

-- one of post_analytics_id, comment_analytics_id is not null. checked by trigger
create table inspection_request
(
    id                   bigserial primary key,
    post_analytics_id    bigint references post_analytics (id),
    comment_analytics_id bigint references comment_analytics (id)
);

-- one of post_id, comment_id and new_post_status_id, new_comment_status_id is not null. checked by trigger
create table status_action
(
    id                    bigserial primary key,
    post_id               uuid references posts (id),
    comment_id            uuid references comments (id),
    actor_id              bigint references users (id),
    new_post_status_id    int references post_statuses (id),
    new_comment_status_id int references comment_statuses (id),
    created_date          timestamp not null
);