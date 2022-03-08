DROP database MovieDetails;
create database MovieDetails;
use MovieDetails;

 create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

 create table movie (
       id bigint not null,
        certification integer,
        country integer,
        description varchar(255),
        genre integer,
        language integer,
        name varchar(255),
        release_date date,
        primary key (id)
    ) engine=InnoDB;

create table movie_person (
       movie_id bigint not null,
        person_id bigint not null
    ) engine=InnoDB;

create table person (
       id bigint not null,
        name varchar(255),
        role integer,
        primary key (id)
    ) engine=InnoDB;

create table rating (
       id bigint not null,
        dislikes bigint,
        likes bigint,
        total_rating double precision,
        movie_id bigint,
        primary key (id)
    ) engine=InnoDB;

create table review (
       id bigint not null,
        comment varchar(255),
        created_date datetime(6),
        movie_id bigint,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB;

create table user (
       id bigint not null,
        age integer,
        email varchar(255),
        is_admin bit,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

alter table movie_person 
       add constraint FKbfx16j95895oxaamdotd6j519 
       foreign key (person_id) 
       references person (id);

alter table movie_person 
       add constraint FKhhevyo1j4b1gre2vcccvxk83u 
       foreign key (movie_id) 
       references movie (id);

alter table rating 
       add constraint FKlqsvmdlh3ep1boo7in23xe86y 
       foreign key (movie_id) 
       references movie (id);

