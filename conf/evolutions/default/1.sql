# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table security_role (
  id                            bigserial not null,
  name                          varchar(255),
  constraint pk_security_role primary key (id)
);

create table users (
  id                            bigserial not null,
  login                         varchar(255),
  password_hash                 integer,
  is_deleted                    boolean default false not null,
  constraint pk_users primary key (id)
);

create table users_security_role (
  users_id                      bigint not null,
  security_role_id              bigint not null,
  constraint pk_users_security_role primary key (users_id,security_role_id)
);

create table users_user_permission (
  users_id                      bigint not null,
  user_permission_id            bigint not null,
  constraint pk_users_user_permission primary key (users_id,user_permission_id)
);

create table user_permission (
  id                            bigserial not null,
  permission_value              varchar(255),
  constraint pk_user_permission primary key (id)
);

create index ix_users_security_role_users on users_security_role (users_id);
alter table users_security_role add constraint fk_users_security_role_users foreign key (users_id) references users (id) on delete restrict on update restrict;

create index ix_users_security_role_security_role on users_security_role (security_role_id);
alter table users_security_role add constraint fk_users_security_role_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;

create index ix_users_user_permission_users on users_user_permission (users_id);
alter table users_user_permission add constraint fk_users_user_permission_users foreign key (users_id) references users (id) on delete restrict on update restrict;

create index ix_users_user_permission_user_permission on users_user_permission (user_permission_id);
alter table users_user_permission add constraint fk_users_user_permission_user_permission foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;


# --- !Downs

alter table if exists users_security_role drop constraint if exists fk_users_security_role_users;
drop index if exists ix_users_security_role_users;

alter table if exists users_security_role drop constraint if exists fk_users_security_role_security_role;
drop index if exists ix_users_security_role_security_role;

alter table if exists users_user_permission drop constraint if exists fk_users_user_permission_users;
drop index if exists ix_users_user_permission_users;

alter table if exists users_user_permission drop constraint if exists fk_users_user_permission_user_permission;
drop index if exists ix_users_user_permission_user_permission;

drop table if exists security_role cascade;

drop table if exists users cascade;

drop table if exists users_security_role cascade;

drop table if exists users_user_permission cascade;

drop table if exists user_permission cascade;

