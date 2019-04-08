# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table claim (
  id                            bigint not null,
  user_id                       bigint not null,
  created_at                    timestamptz,
  solved                        boolean default false not null,
  solved_at                     timestamptz,
  comment                       varchar(255)
);

create table games (
  id                            bigint not null,
  name                          varchar(255),
  company                       varchar(255),
  year                          date,
  created_at                    timestamptz,
  is_deleted                    boolean default false not null
);

create table promo (
  id                            bigint not null,
  game_id                       bigint not null,
  start                         timestamptz,
  end                           timestamptz,
  discount                      integer
);

create table users (
  id                            bigserial not null,
  login                         varchar(255),
  password_hash                 integer,
  is_deleted                    boolean default false not null,
  constraint pk_users primary key (id)
);

create table wishlist (
  id                            bigint not null,
  user_id                       bigint not null,
  game_id                       bigint not null,
  created_at                    timestamptz,
  is_deleted                    boolean default false not null
);


# --- !Downs

drop table if exists claim cascade;

drop table if exists games cascade;

drop table if exists promo cascade;

drop table if exists users cascade;

drop table if exists wishlist cascade;

