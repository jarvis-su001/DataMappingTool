SELECT * FROM ATTENDANCE_ROLLUP;
SELECT * FROM ACH_ATTENDANCE;
SELECT * FROM ATTENDANCE_SUMMARY;

SELECT DISTINCT a.transaction_type_id, t.descr
  FROM ach_detail a, transaction_type t
 WHERE a.transaction_type_id = t.transaction_type_id;

SELECT DISTINCT a.ach_source_type_id, ast.descr
  FROM ach_detail a, ach_source_type ast
 WHERE a.ach_source_type_id = ast.ach_source_type_id;

SELECT DISTINCT a.transaction_type_id,
                t.descr,
                a.ach_source_type_id,
                ast.descr
  FROM ach_detail a, ach_source_type ast, transaction_type t
 WHERE a.ach_source_type_id = ast.ach_source_type_id
   AND a.transaction_type_id = t.transaction_type_id;

SELECT * FROM ach_source_type;

SELECT * FROM PAYMENT_SUMMARY ;
SELECT * FROM payment_process p WHERE p.process_id='56952836';
SELECT * FROM TRANSACTION_ATTENDANCE;

SELECT * FROM ACH_ABSENT_DAYS a WHERE a.attendance_summary_id <> -1;
SELECT * FROM ACH_ABSENT_DAYS a WHERE a.copay_applied <> 0;
SELECT * FROM ach_detail a WHERE a.ach_detail_id='208373380';

SELECT * FROM attendance_summary a WHERE a.attendance_summary_id=-1;
SELECT * FROM ATTENDANCE_ADJUSTMENT a WHERE a.adjustment_id=-1;


SELECT DISTINCT ad.transaction_type_id, ad.ach_source_type_id
  FROM attendance_adjustment aa,
       attendance_summary    ats,
       ach_attendance        act,
       ach_detail            ad
 WHERE aa.attendance_summary_id = ats.attendance_summary_id
   AND ats.attendance_summary_id = act.attendance_summary_id
   AND act.ach_detail_id = ad.ach_detail_id;

SELECT COUNT(1) FROM ATTENDANCE_ADJUSTMENT;
--1464


