show tables;

/* 기본키(Primary key)
  테이블을 대표하는 키로서, 중복을 허용하지 않는다.  기본키는 여러개를 사용할 수 있다.
*/
create table test1 (
  idx  int not null auto_increment primary key,
  name varchar(20) not null,
  age  int default 20,
  address varchar(50)
);

desc test1;

/* 테이블의 구조 변경(추가/변경/삭제/테이블명령) : alter table ~~~명령어~~~ */
alter table test1 add column job varchar(10);
alter table test1 modify column job varchar(15);
alter table test1 change column job jikup varchar(20);
alter table test1 drop column jikup;
alter table test1 rename tt1;
show tables;
desc tt1;

create table test2 (
  idx  int not null auto_increment primary key,
  name varchar(20) not null,
  age  int default 20,
  test2Code varchar(10),
  /* primary key(idx, test2Code) */
  unique key (test2Code)
);
desc test2;
drop table test2;

insert into test2 values (default, '홍길동', 22, 'aaa');
insert into test2 values (default, '김말숙', 33, 'bbb');
select * from test2;

-- test2테이블에 기본키를 추가로 정의
alter table test2 add primary key (test2Code);

/*  UNIQUE KEY : 중복불허를 위해 설정하는 키
 *  Primary key를 대신해서 중복을 불허시키고자할때 사용.
 */

create table test3 (
  idx  int not null auto_increment primary key,
  name varchar(20) not null,
  age  int default 20,
  test3Code varchar(10),
  unique key (test3Code)
);
desc test3;
drop table test3;

insert into test3 values (default,'가가가',22,'bbb');
insert into test3 values (default,'나나나',33,'ccc');
insert into test3 values (default,'다다다',44,'ddd');
delete from test3;
select * from test3;

/* 외래키(Foreign key)
 * 하나의 테이블에서 다른테이블의 정보를 찾기위해 연결해주는 역할을 할때 지정하는 키이다.
 * 조건, 현재 테이블의 필드를 외래키로 설정하려면? 상대쪽 테이블의 해당필드는 Primary key이거나 Unique key로 설정되어 있어야하고,
 *      해당 필드는 상대편 테이블의 필드의 속성과 같아야 한다.
 */
/*
create table test4 (
  idx  int not null auto_increment primary key,
  name varchar(20) not null,
  age  int default 20,
  test2Code varchar(10),
  test3Code varchar(10),
  foreign key (idx,test2Code) references test2 (idx,test2Code)
);
*/
create table test4 (
  idx  int not null auto_increment primary key,
  name varchar(20) not null,
  age  int default 20,
  test2Code varchar(10),
  test3Code varchar(10),
  test4Code varchar(10),
  unique key (test4Code),
  foreign key (test2Code) references test2 (test2Code),
  foreign key (test3Code) references test3 (test3Code)
);
desc test4;
drop table test4;
insert into test4 value (default,'하하하',24,'mbc','aaa','서울');	/* 외래키인 test3Code필드가 test3테이블의 필드(test3Code)내용에 포함되어있지 않기에 등록불가 */
insert into test4 value (default,'하하하',24,'aaa','bbb','서울');
insert into test4 value (default,'파파파',24,'bbb','ccc','부산');


create table test5 (
  idx  int not null auto_increment primary key,
  name varchar(20),
  age  int default 20,
  test2Code varchar(10),
  test3Code varchar(10),
  test4Code varchar(10),
  unique key(test2Code, test3Code),
  unique key(test4Code),
  foreign key (test2Code) references test2 (test2Code),
  foreign key (test3Code) references test3 (test3Code),
  foreign key (test4Code) references test4 (test4Code)
);
desc test4;
drop table test4;

insert into test5 (idx,test2Code,test3Code,test4Code) values (default,'aaa','bbb','ccc');