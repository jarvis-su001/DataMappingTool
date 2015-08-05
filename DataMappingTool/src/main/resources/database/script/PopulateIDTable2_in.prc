CREATE OR REPLACE
PROCEDURE PopulateIDTable2_in AS
  MAX_VAR_ID number;
BEGIN

DECLARE
  
  
  TYPE minRangeArray IS varray(32) OF NUMBER;
  rangeMin minRangeArray := minRangeArray(
     0,0,0,0,0,0,0,0,0,0,0,0,90000000,80000000,0,0,0,100,100,100,10000,0,0,100,0,0,0,0,0,100,0,0,100);

  TYPE maxRangeArray IS varray(32) OF NUMBER;
  rangeMax maxRangeArray := maxRangeArray(
    2000000000000000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,91000000,89999999,0,0,999499,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000,2100000000);

  TYPE incrArray IS varray(32) OF NUMBER; 
  incrBucket incrArray := incrArray(
    50,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20);

  seqID  IDTABLE2.SEQ_ID%TYPE;
  nextID IDTABLE2.NEXTID%TYPE;
  seqName IDTABLE2.SEQ_NAME%TYPE;
  
CURSOR c_idtable
IS
  SELECT VAR_ID, LASTUSEDID, TRIM(NAME) FROM IDTABLE ORDER BY VAR_ID;
  
  BEGIN
  select max(var_id)+1 into MAX_VAR_ID from idtable; 
  
       OPEN c_idtable;
         LOOP
           FETCH c_idtable INTO seqID, nextID, seqName;
           EXIT WHEN c_idtable%NOTFOUND;
       IF seqID != MAX_VAR_ID THEN
             INSERT INTO IDTABLE2 (SEQ_ID,MIN_VAL,MAX_VAL,NEXTID,DFLT_INCR,SEQ_NAME,MODTIME) VALUES (seqID, rangeMin(seqID + 1), rangeMax(seqID + 1), nextID, incrBucket(seqID + 1), seqName, SYSDATE);
             commit;
           END IF;         
     END lOOP;
         
         CLOSE c_idtable;
         
         --This is a special id that tags the instance of eppic running and is needed for startup
         INSERT INTO IDTABLE2 (SEQ_ID,MIN_VAL,MAX_VAL,NEXTID,DFLT_INCR,SEQ_NAME,MODTIME,CAN_WRAP) VALUES (MAX_VAR_ID,1,999,1,1,'ACCOUNT_BUCKET_GROUP',SYSTIMESTAMP, 'Y');
         commit;
  END;
  
END PopulateIDTable2_in;
/
