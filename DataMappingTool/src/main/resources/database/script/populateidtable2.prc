CREATE OR REPLACE PROCEDURE populateidtable2 IS
  max_var_id NUMBER;
BEGIN

  DECLARE
    seqid   idtable2.seq_id%TYPE;
    nextid  idtable2.nextid%TYPE;
    seqname idtable2.seq_name%TYPE;
  
    min_val    idtable2.min_val%TYPE;
    max_val    idtable2.max_val%TYPE;
    incrbucket idtable2.dflt_incr%TYPE;
  
    CURSOR c_idtable IS
      SELECT var_id, lastusedid, TRIM(NAME) FROM idtable ORDER BY var_id;
  
  BEGIN
    SELECT MAX(var_id) + 1 INTO max_var_id FROM idtable;
  
    OPEN c_idtable;
    LOOP
      FETCH c_idtable
        INTO seqid, nextid, seqname;
      EXIT WHEN c_idtable%NOTFOUND;
      /* the following segments should be adjusted for each States */
      IF seqid = 0 THEN
        min_val    := 1;
        max_val    := 2000000000000000000;
        incrbucket := 20;
      
      ELSIF seqid = 12 THEN
        min_val    := 90000000;
        max_val    := 91000000;
        incrbucket := 50;
      
      ELSIF seqid = 13 THEN
        min_val    := 80000000;
        max_val    := 89999999;
        incrbucket := 50;
      
      ELSIF seqid = 14 OR seqid = 15 THEN
        min_val    := 0;
        max_val    := 0;
        incrbucket := 20;
      
      ELSIF seqid = 16 THEN
        min_val    := 0;
        max_val    := 999499;
        incrbucket := 20;
      
      ELSIF seqid IN (17, 18, 19, 23, 33) THEN
        min_val    := 100;
        max_val    := 2100000000;
        incrbucket := 20;
      
      ELSIF seqid = 20 THEN
        min_val    := 10000;
        max_val    := 2100000000;
        incrbucket := 20;
        
        ELSIF seqid = 29 THEN
        min_val    := 20000;
        max_val    := 2100000000;
        incrbucket := 10;
      
      ELSE
        min_val    := 1;
        max_val    := 2100000000;
        incrbucket := 20;
      END IF;
      /*end the segment */
      IF seqid != max_var_id THEN
        INSERT INTO idtable2
          (seq_id, min_val, max_val, nextid, dflt_incr, seq_name, modtime)
        VALUES
          (seqid, min_val, max_val, nextid, incrbucket, seqname, SYSDATE);
        COMMIT;
      END IF;
    END LOOP;
  
    CLOSE c_idtable;
  
    --This is a special id that tags the instance of eppic running and is needed for startup
    INSERT INTO idtable2
      (seq_id,
       min_val,
       max_val,
       nextid,
       dflt_incr,
       seq_name,
       modtime,
       can_wrap)
    VALUES
      (max_var_id, 1, 999, 1, 1, 'ACCOUNT_BUCKET_GROUP', systimestamp, 'Y');
    COMMIT;
  END;
END populateidtable2;
/
