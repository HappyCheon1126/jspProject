show tables;

create table webMessage (
  idx     int not null auto_increment primary key, /* 메세지 고유번호 */
  title   varchar(100) not null,			/* 메세지 제목 */
  content text  not null,							/* 메세지 내용 */
  sendId  varchar(30) not null,				/* 보내는 사람 아이디 */
  sendSw  char(1)     not null,				/* 보낸메세지(s), 휴지통(g), 휴지통삭제(x) 표시 */
  sendDate datetime   default now(),	/* 메세지 보낸날짜 */
  receiveId  varchar(30) not null,		/* 받는 사람 아이디 */
  receiveSw  char(1)     not null,		/* 받은메세지(n), 읽은메세지(r), 휴지통(g), 휴지통삭제(x) 표시 */
  receiveDate datetime   default now()	/* 메세지 받은날짜 */  
);

desc webMessage;

insert into webMessage values (default,'안녕! 말숙아~~','이번주 주말에 뭐하니?','hkd1234','s',default,'kms1234','n',default);
insert into webMessage values (default,'답장! 반갑다 길동아~~','이번주 주말에는 프로젝트 작업으로 바빠. 다음에 보자.','kms1234','s',default,'hkd1234','n',default);
insert into webMessage values (default,'말숙아~~','물어볼게 있는데?.. 다음에 물어볼께..','hkd1234','s',default,'kms1234','n',default);
insert into webMessage values (default,'뭔데?','궁금하다. 빨리 얘기해~','kms1234','s',default,'hkd1234','n',default);
insert into webMessage values (default,'그럼 다음에..','만나서 얘기해 줄께~','hkd1234','s',default,'kms1234','n',default);

select * from webMessage;
