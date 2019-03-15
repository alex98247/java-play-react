# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table users (
  id                            bigserial not null,
  login                         varchar(255),
  password_hash                 integer,
  is_deleted                    boolean default false not null,
  constraint pk_users primary key (id)
);


# --- !Downs

drop table if exists users cascade;

