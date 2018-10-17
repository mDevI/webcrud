delete from user_role;
delete from roles;
delete from users;
delete from persons;

-- PERSONS -------------------------------------------------------------------------------------------------------------
insert into persons (id, firstname, lastname, email) values (1, 'John', 'Doe', 'jdoe@gmail.com' );
insert into persons (id, firstname, lastname, email) values (2, 'Jane', 'Doe', 'janedoe@gmail.com' );
insert into persons (id, firstname, lastname, email) values (3, 'Nick', 'Finn', 'nick@gmail.com' );
insert into persons (id, firstname, lastname, email) values (4, 'Meggy', 'Rolls', 'meg@gmail.com' );
insert into persons (id, firstname, lastname, email) values (5, 'Walter', 'Frisky', 'waltf@gmail.com' );




-- ROLES ---------------------------------------------------------------------------------------------------------------

INSERT INTO roles (id, name) VALUES
                                    (1, 'ROLE_ADMIN'),
                                    (2, 'ROLE_READER'),
                                    (3, 'ROLE_EDITOR');

-- Logins and passwords ------------------------------------------------------------------------------------------------
-- Admin admin
-- Sergei Sergei
-- Yuri Yuri

-- USERS ---------------------------------------------------------------------------------------------------------------
INSERT INTO users (id, login, password, name) VALUES
(1, 'Admin', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS','Admin'),
(2, 'Sergei', '$2a$10$0FvNxBOy4IiibLXBeQ5e9.ub.vwVnBLOW8kTxIc1eNNP5Lk1/.1/e','Reader'),
(3, 'Yuri', '$2a$10$2NEyto6GUS1yFhfSx/EpD.VqvJ/krpnpWDV2frRA.NDeyYeOl7jS6','PowerUser');

-- Admin has two roles (ROLE_ADMIN & ROLE_EDITOR) ----------------------------------------------------------------------
insert into user_role(user_id, role_id) values(1, 1);
insert into user_role(user_id, role_id) values(1, 3);

-- Sergei has read-only role -------------------------------------------------------------------------------------------
insert into user_role(user_id, role_id) values (2, 2);

-- Yuri has editor role ------------------------------------------------------------------------------------------------
insert into user_role(user_id, role_id) values (3, 3);


-- ACL schema specific tables ------------------------------------------------------------------------------------------

-- SIDs table definition -----------------------------------------------------------------------------------------------
create table acl_sid (
  id        bigint generated by default as identity (start with 100) not null primary key,
  principal boolean                                                  not null,
  sid       varchar_ignorecase(100)                                  not null,
  constraint unique_uk_1 unique (sid, principal)
);

-- Our SID's -----------------------------------------------------------------------------------------------------------
insert into acl_sid (id, principal, sid) values (1, true, 'Admin');
insert into acl_sid (id, principal, sid) values (2, false, 'ROLE_READER');
insert into acl_sid (id, principal, sid) values (3, false, 'ROLE_EDITOR');
insert into acl_sid (id, principal, sid) values (4, false, 'ROLE_ADMIN');


-- ACL class table definition ------------------------------------------------------------------------------------------
create table acl_class (
  id    bigint generated by default as identity (start with 100) not null primary key,
  class varchar_ignorecase(100)                                  not null,
  constraint unique_uk_2 unique (class)
);

-- We have only one ACL class for Person class object ------------------------------------------------------------------
insert into acl_class (id, class) values (1, 'com.mdevi.webcrud.model.Person');

-- Object identity table definition ------------------------------------------------------------------------------------
create table acl_object_identity (
  id                 bigint generated by default as identity (start with 100) not null primary key,
  object_id_class    bigint                                                   not null,
  object_id_identity bigint                                                   not null,
  parent_object      bigint,
  owner_sid          bigint,
  entries_inheriting boolean                                                  not null,
  constraint unique_uk_3 unique (object_id_class, object_id_identity),
  constraint foreign_fk_1 foreign key (parent_object) references acl_object_identity (id),
  constraint foreign_fk_2 foreign key (object_id_class) references acl_class (id),
  constraint foreign_fk_3 foreign key (owner_sid) references acl_sid (id)
);

-- Object identity -----------------------------------------------------------------------------------------------------
insert into acl_object_identity (id, object_id_identity, object_id_class, parent_object, owner_sid, entries_inheriting) values (1, 1, 1, null, 4, false);
insert into acl_object_identity (id, object_id_identity, object_id_class, parent_object, owner_sid, entries_inheriting) values (2, 2, 1, null, 4, false);
insert into acl_object_identity (id, object_id_identity, object_id_class, parent_object, owner_sid, entries_inheriting) values (3, 3, 1, null, 4, false);
insert into acl_object_identity (id, object_id_identity, object_id_class, parent_object, owner_sid, entries_inheriting) values (4, 4, 1, null, 4, false);
insert into acl_object_identity (id, object_id_identity, object_id_class, parent_object, owner_sid, entries_inheriting) values (5, 5, 1, null, 4, false);



-- ACE table definition ------------------------------------------------------------------------------------------------
create table acl_entry (
  id                  bigint generated by default as identity (start with 100) not null primary key,
  acl_object_identity bigint                                                   not null,
  ace_order           int                                                      not null,
  sid                 bigint                                                   not null,
  mask                integer                                                  not null,
  granting            boolean                                                  not null,
  audit_success       boolean                                                  not null,
  audit_failure       boolean                                                  not null,
  constraint unique_uk_4 unique (acl_object_identity, ace_order),
  constraint foreign_fk_4 foreign key (acl_object_identity) references acl_object_identity (id),
  constraint foreign_fk_5 foreign key (sid) references acl_sid (id)
);

-- ACE list for Admin --------------------------------------------------------------------------------------------------

insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values (1, 1, 4, 3, true, true, true);
insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values (2, 1, 4, 3, true, true, true);
insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values (3, 1, 4, 3, true, true, true);
insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values (4, 1, 4, 3, true, true, true);
insert into acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values (5, 1, 4, 3, true, true, true);

