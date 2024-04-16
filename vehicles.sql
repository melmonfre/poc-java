create table vehicles (
id bigint not null auto_increment,
brand varchar(100),
fabrication datetime(6),
model varchar(100) not null,
plate varchar(12) not null,
primary key (id)
)