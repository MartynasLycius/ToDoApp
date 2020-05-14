create table todo.todo_item (
  id            bigint   not null auto_increment,
  title       varchar(255),
  description       varchar(255),
  taskdate      datetime,
  version       bigint not null,
  created       datetime not null,
  lastUpdated   datetime not null,
  primary key (id)
)
  engine = InnoDB;