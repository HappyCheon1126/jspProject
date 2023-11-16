/* 학과(hakkwa) 테이블 */
create table hakkwa (
  code  int not null primary key,	/* 학과 코드 */
  name  varchar(20)								/* 학과 명 */
);
desc hakkwa;

drop table hakkwa;

-- unique key로 name을 추가하시오.
alter table hakkwa add unique(name);

insert into hakkwa values (101, '컴퓨터공학과');
insert into hakkwa values (102, '멀티미디어과');
insert into hakkwa values (103, '경영학과');
insert into hakkwa values (104, '생물학과');
insert into hakkwa values (105, '기계공학과');
insert into hakkwa values (106, '영문학과');
insert into hakkwa values (107, '일문학과');

delete from hakkwa where code = 106;
delete from hakkwa where code = 101;
update hakkwa set code = 107 where code = 103;
update hakkwa set code = 108 where code = 101;

select * from hakkwa;

/* 학생 정보 테이블 */
create table student (
  st_code  int not null auto_increment primary key, /* 학생 고유번호 */
  name     varchar(20) not null,		/* 학생 성명 */
  hakkwa_code int,
  age			 int default 20,
  foreign key(hakkwa_code) references hakkwa(code)
  on update cascade
  on delete set null
);
desc student;

drop table student;

insert into student values (default,'홍길동',101,default);
insert into student values (default,'김말숙',101,default);
insert into student values (default,'이기자',102,default);
insert into student values (default,'오하늘',102,default);
insert into student values (default,'고인돌',103,default);
insert into student values (default,'강하늘',103,default);
insert into student values (default,'배서현',104,default);
insert into student values (default,'신성우',104,default);
insert into student values (default,'박정환',105,default);
insert into student values (default,'유승선',105,default);
insert into student values (default,'김은이',106,default);
insert into student values (default,'허성진',106,default);
insert into student values (default,'연습이',107,default);	/* 107번 필드값이 없음.. 오류 */

select * from student;

-- 현재 시스템(존재하는 모든 Database)의 모든 제약조건을 확인?
select * from information_schema.table_constraints;
select * from information_schema.table_constraints where constraint_schema='javaProject';
select * from information_schema.table_constraints where constraint_schema='javaProject' and table_name='member';

/* ------- 조인(관련된 테이블간의 정보를 검색 처리할때 사용..) --------------- */
-- 일반조인(Inner Join) : 각 테이블에는 서로 매칭이 되는 필드가 포함되어 있어야 한다.
select * from hakkwa;
select * from student;

select * from hakkwa Inner Join student;

select * from hakkwa Inner Join student on hakkwa.code = student.hakkwa_code;
select * from hakkwa Join student on hakkwa.code = student.hakkwa_code;
select * from hakkwa as h Inner Join student as s on h.code = s.hakkwa_code;
select * from hakkwa as h, student as s where h.code = s.hakkwa_code;

/* 내부조인 : LEFT JOIN, RIGHT JOIN */
-- LEFT JOIN(왼쪽 기준) : 왼쪽자료를 모두가져오고 오른쪽은 만족하는것만 가져온다. 없으면 NULL로 채운다.
select * from hakkwa h left join student s on h.code = s.hakkwa_code;

-- RIGHT JOIN(오른쪽 기준) : 오른쪽자료를 모두가져오고 왼쪽은 만족하는것만 가져온다. 없으면 NULL로 채운다.
select * from hakkwa h right join student s on h.code = s.hakkwa_code;

/* Cross Join(곱집합) : 모든행의 자료들을 다 가져온다. */
select * from hakkwa, student;

/* 합집합 : Full outer Join(Mysql에는 없음), MySql에서는 UNION명령을 사용한다. */
select * from hakkwa as h union select hakkwa_code,name from student as s;



/* 연습자료 */
/* 학생 정보 테이블 */
create table student2 (
  st_code  int not null auto_increment primary key, /* 학생 고유번호 */
  name     varchar(20) not null,		/* 학생 성명 */
  hakkwa_code int
);

insert into student2 values (default,'가가가',100);
insert into student2 values (default,'나나나',200);
insert into student2 values (default,'다다다',300);
insert into student2 values (default,'라라라',400);
insert into student2 values (default,'홍길동',101);
select * from student2;

select name,hakkwa_code from student union select name,hakkwa_code from student2;

/* ----실제 사용예에 따른 응용연습----------- */
select * from student2 s2, student s1;
select * from student2 s2, student s1 where s2.name = s1.name;
select s1.hakkwa_code,s2.name from student2 s2, student s1 where s2.name = s1.name;
select s1.hakkwa_code,h.name,s2.name from student2 s2, student s1, hakkwa h where s2.name = s1.name and s1.hakkwa_code=h.code;

select * from 