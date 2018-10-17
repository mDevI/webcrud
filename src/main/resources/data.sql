delete from user_role;
delete from roles;
delete from users;
delete from persons;

-- PERSONS -------------------------------------------------------------------------------------------------------------
insert into persons (id, firstname, lastname, email) values (1,"John", "Doe", "jdoe@gmail.com" );
insert into persons (id, firstname, lastname, email) values (2,"Jane", "Doe", "janedoe@gmail.com" );
insert into persons (id, firstname, lastname, email) values (3,"Nick", "Finn", "nick@gmail.com" );
insert into persons (id, firstname, lastname, email) values (4,"Meggy", "Rolls", "meg@gmail.com" );
insert into persons (id, firstname, lastname, email) values (5,"Walter", "Frisky", "waltf@gmail.com" );


INSERT INTO roles (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_READER');

-- Пароли и явки
-- Admin admin
-- Sergei Sergei
-- Yuri Yuri

INSERT INTO users (id, login, password, name) VALUES
(1, 'Admin', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS',
'Admin'),
(2, 'Sergei', '$2a$10$0FvNxBOy4IiibLXBeQ5e9.ub.vwVnBLOW8kTxIc1eNNP5Lk1/.1/e',
'Reader'),
(3, 'Yuri', '$2a$10$2NEyto6GUS1yFhfSx/EpD.VqvJ/krpnpWDV2frRA.NDeyYeOl7jS6',
'DemoUser');

insert into user_role(user_id, role_id) values
(1,1),
(2,2),
(3,1);