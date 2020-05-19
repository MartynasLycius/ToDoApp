create table todo_items
(
    id          int auto_increment
        primary key,
    item_date   date         not null,
    item_name   varchar(150) not null,
    description varchar(250) not null,
    item_status char         null,
    created_at  datetime     not null
);