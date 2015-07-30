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
    
      IF seqid = 0 THEN
        min_val    := 0;
        max_val    := 2000000000000000000;
        incrbucket := 200;
      ELSE
        min_val    := 0;
        max_val    := 2100000000;
        incrbucket := 20;
      END IF;
    
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
