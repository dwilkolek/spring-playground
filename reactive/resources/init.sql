drop table if exists car;
create table car (
    id uuid DEFAULT RANDOM_UUID() primary key,
    name text not null,
    age int not null
);
