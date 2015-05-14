DROP TABLE BOARD_COMMENT

CREATE TABLE BOARD_COMMENT (
  NO NUMBER(8) NOT NULL,
  USER_NO NUMBER(8), 
  CONTENT VARCHAR2(500),
  REG_DATE DATE,
  BOARD_NO NUMBER(8), 
  ORDER_NO NUMBER(8), 
  PRIMARY KEY (NO) 
);

alter table BOARD_COMMENT add constraint BOARD_COMMENT_DELETE_CONTRAINT foreign key (BOARD_NO) references BOARD(NO) on delete cascade; 

CREATE SEQUENCE BOARD_COMMENT_SEQ
INCREMENT BY 1
START WITH 1
MAXVALUE 9999999
NOCACHE
NOCYCLE;
   
select COMMENT_SEQ.nextval from dual;

select * from BOARD;
