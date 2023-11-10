show tables;

create table user (
  idx  int not null auto_increment primary key,  /* user 고유번호 */
  mid  varchar(20) not null,
  name varchar(20) not null,
  age  int default 20,
  address varchar(10)
);

desc user;

insert into user value (default, 'admin', '관리자', 40, '서울');
insert into user value (default, 'hkd1234', '홍길동', 22, '청주');
insert into user value (default, 'kms1234', '김말수', 31, '제주');

select * from user;
