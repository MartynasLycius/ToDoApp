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
    uuid_str varchar(255) not null
        constraint uk_8ajuiqv4yht19w37hw861gf8o
        unique,
    date timestamp not null,
    description text,
    name varchar(255) not null
);

alter table todo.todo_items owner to sayem;

