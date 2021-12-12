insert into authorities (code, description)
values ('POSTS_VIEW', 'Просмотр постов'),
       ('POSTS_EDIT', 'Просмотр постов'),
       ('ANALYTICS_VIEW', 'Просмотр результатов инспекции'),
       ('USERS_VIEW', 'Просмотр пользователей');

insert into roles (code, description)
values ('ADMIN', 'Администратор'),
       ('USER', 'Пользователь');

insert into roles_to_authorities (role_id, authority_id)
select r.id, a.id
from roles r
         cross join authorities a
where r.code = 'ADMIN'
   or (r.code = 'USER' and a.code in ('POSTS_VIEW', 'POSTS_EDIT'));

insert into user_statuses (code, description)
values ('ACTIVE', 'Активен'),
       ('BLOCKED', 'Заблокирован');

insert into users (username, password, email, dob, register_date, status_id, role_id)
values ('admin', '$2a$10$b8eIRtgOJHC2Ttxysb1NyuVDt7/VybomGsjI3D9wj11VhtQK.EoPG', 'igorgood01@gmail.com', '2001-07-24',
        current_date, (select id from user_statuses where code = 'ACTIVE'), (select id from roles where code = 'ADMIN'));

insert into post_statuses (code, color, description) values
('ACTIVE', 'f7f7f7', 'Активен'),
('SUSPICIOUS', 'fdd867', 'Подозреваем на неприемлемость'),
('BLOCKED', 'fd8b90', 'Заблокирован');