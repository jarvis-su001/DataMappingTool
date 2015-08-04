 CREATE TABLE IDTABLE2 (
   SEQ_ID NUMBER(6,0) NOT NULL, 
   NEXTID NUMBER(19,0) NOT NULL, 
   SEQ_NAME VARCHAR2(30) NOT NULL, 
   MODTIME TIMESTAMP NOT NULL,
   MIN_VAL NUMBER DEFAULT -1 NOT NULL,
   MAX_VAL NUMBER(19,0) DEFAULT 9223372036854775807 NOT NULL,
   DFLT_INCR NUMBER DEFAULT -1 NOT NULL,
   CAN_WRAP CHAR(1) DEFAULT'N' CHECK("CAN_WRAP" IN ('Y','N')) NOT NULL,
   EXPIRE_SPEC VARCHAR2(20) DEFAULT'NONE' NOT NULL,
   MIN_UPDT_MS NUMBER DEFAULT 99999999999 NOT NULL
   );
   
   CREATE UNIQUE INDEX PK_IDTABLE2 ON IDTABLE2 ("SEQ_ID") ;

  ALTER TABLE IDTABLE2 ADD CONSTRAINT PK_IDTABLE2 PRIMARY KEY ("SEQ_ID")
  USING INDEX NOLOGGING COMPUTE STATISTICS ENABLE;