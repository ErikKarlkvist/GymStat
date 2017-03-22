CREATE DATABASE gymstat.db;

CREATE TABLE setnames(
  username varchar(255) NOT NULL PRIMARY KEY,
  set_id int NOT NULL UNIQUE AUTO_INCREMENT,
  setname varchar(20) NOT NULL,
  description varchar(255),
  img_url varchar(255)
);

CREATE TABLE weightlist(
  set_id int NOT NULL FOREIGN KEY REFERENCES setnames(set_id),
  weight FLOAT,
  reps varchar(10),
  set_date varchar(10)
)
