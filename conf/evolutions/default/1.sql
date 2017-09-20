# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                            serial not null,
  title                         varchar(255),
  price                         integer,
  author                        varchar(255),
  constraint pk_book primary key (id)
);

create table entry (
  id                            bigserial not null,
  name                          varchar(255),
  constraint pk_entry primary key (id)
);

create table grid (
  id                            serial not null,
  date                          timestamp,
  client                        varchar(255),
  amount                        integer,
  tax                           integer,
  total                         integer,
  notes                         varchar(255),
  comment                       varchar(255),
  constraint pk_grid primary key (id)
);


# --- !Downs

drop table if exists book cascade;

drop table if exists entry cascade;

drop table if exists grid cascade;

