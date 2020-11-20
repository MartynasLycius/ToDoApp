CREATE TABLE IF NOT EXISTS Task(
   id IDENTITY PRIMARY KEY,
   timestamp DATE,
   name VARCHAR,
   description VARCHAR
);
DELETE FROM Task;
INSERT INTO Task (id,date,name,description) VALUES(1,'2020-01-17','Tea-break','Do it today');
INSERT INTO Task (id,date,name,description) VALUES(2,'2020-01-17','Less-important','Do it tomorrow');
INSERT INTO Task (id,date,name,description) VALUES(3,'2020-01-17','Sports','Do it on Friday');
