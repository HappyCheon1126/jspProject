show tables;

create table schedule (
  idx   int not null auto_increment primary key,	/* 스케줄관리 고유번호 */
  mid		varchar(30) not null,		/* 회원 아이디(일정검색시 필요) */
  sDate datetime not null,			/* 일정 등록 날짜 */
  part	varchar(10) not null,		/* 일정분류(1.모임, 2.업무, 3.학습, 4.여행, 5:기타) */
  content text not null,				/* 일정 상세내역 */
  foreign key(mid) references member(mid)
);
drop table schedule;

desc schedule;

insert into schedule values (default,'hkd1234','2023-11-01','학습','JSP 프로젝트 기획');
insert into schedule values (default,'hkd1234','2023-11-01','모임','학원동기모임 19시 사창사거리');
insert into schedule values (default,'hkd1234','2023-11-05','업무','기획회의 12시30분');
insert into schedule values (default,'hkd1234','2023-11-11','모임','빼빼로데이 모임');
insert into schedule values (default,'hkd1234','2023-11-15','학습','JSP 프로젝트 시작');
insert into schedule values (default,'hkd1234','2023-11-22','학습','프로젝트 정기점검');
insert into schedule values (default,'kms1234','2023-11-11','모임','빼빼로데이 모임-사창사거리');
insert into schedule values (default,'kms1234','2023-11-15','여행','주말 여행계획');
insert into schedule values (default,'kms1234','2023-11-22','학습','프로젝트 점검');

select * from schedule;
select * from schedule order by sDate, part;

select * from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m-%d')='2023-11-01' order by sDate, part;
select * from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' order by sDate, part;

select distinct sDate as cnt from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' order by sDate, part;
select distinct sDate, count(*) as cnt from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' order by sDate, part;

select * from schedule group by date_format(sDate, '%Y-%m')='2023-11' order by sDate, part;
select *,count(*) from schedule group by date_format(sDate, '%Y-%m')='2023-11' order by sDate, part;

select * from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' group by sDate order by sDate, part;
select *,count(*) from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' group by sDate order by sDate, part;
select *,count(*) as partCnt from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' group by sDate,part order by sDate, part;

select *,count(*) from schedule where mid='hkd1234' and date_format(sDate, '%Y-%m')='2023-11' group by part order by sDate, part;

