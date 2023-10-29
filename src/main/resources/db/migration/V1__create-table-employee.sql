create table employee(
  employee_id bigint not null auto_increment,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  salary float,
  dni varchar(8) not null unique,
  address varchar(100) not null,
  birthdate date not null,
  primary key(employee_id)

);