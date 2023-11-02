show tables;

create table login (
  idx  int not null auto_increment primary key,	/* 고유번호 */
  mid  varchar(20) not null,			/* 아이디 */
  pwd  varchar(20) not null,			/* 비밀번호 */
  name varchar(20) not null,			/* 성명 */
  point int default 100,					/* 포인트(기본포인트 100) */
  lastDate datetime default now(), /* 최종 방문일자 */
  todayCount int default 0				/* 오늘 방문일수 누적 */
);

desc login;

insert into login (mid,pwd,name) values ('hkd1234','1234','홍길동');
insert into login values (default, 'admin', '1234', '관리자', default, default, default);

select * from login;

