-- New Migration
create table todo_items
(
    id bigserial not null
        constraint todo_items_pkey
        primary key,
    created_at timestamp not null,
    created_by varchar(255),
    deleted boolean not null,
    updated_at timestamp,
    updated_by varchar(255),
    uuid uuid not null
        constraint uk_6e3cfbbdtql6rergu6unddhlx
        unique,
    date timestamp not null,
    description text,
    name varchar(255) not null
);


