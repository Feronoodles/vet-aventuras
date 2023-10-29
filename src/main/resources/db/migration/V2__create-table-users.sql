create table users (
  user_id bigint not null auto_increment,
  employee_id bigint,
  email varchar(100) not null unique,
  password varchar(200) not null,
  role varchar(50) not null,
  created_at date not null,
  primary key(user_id),
  foreign key(employee_id) references employee(employee_id)
);