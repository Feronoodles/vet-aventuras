create table token(
token_id bigint not null auto_increment,
token varchar(200) not null,
token_type varchar(50) not null,
revoked boolean not null,
expired boolean not null,
user_id bigint not null,
primary key(token_id),
foreign key(user_id) references users(user_id)

);