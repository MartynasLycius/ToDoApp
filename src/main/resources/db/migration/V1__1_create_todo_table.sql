-- drop schema if exists todo_app cascade ;
-- CREATE SCHEMA todo_app;
-- DROP table if exists todo_app.todo;

create table todo_app.todo
(
    id bigint
        constraint todo_app_pk
            primary key,
    title varchar(100),
    description varchar(500),
    date date,
    complete bool default false,
    create_time timestamp,
    edit_time timestamp,
    version bigint
);



CREATE SEQUENCE todo_app.TODO_SEQ INCREMENT BY 1 START WITH 1 NO CYCLE OWNED BY todo_app.todo.id;