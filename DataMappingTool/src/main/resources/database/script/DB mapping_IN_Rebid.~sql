SELECT t1.table_name,
       t2.column_name,
       t2.data_type,
       t2.data_length,
       t2.column_id,
       t1.num_rows,
       t3.comments    table_comments,
       t4.comments    column_comments
  FROM user_tables       t1,
       user_tab_columns  t2,
       user_tab_comments t3,
       user_col_comments t4
 WHERE t1.table_name = t2.table_name
   AND t1.table_name = t3.table_name
   AND t2.column_name = t4.column_name
   AND t2.table_name = t4.table_name
 ORDER BY t1.table_name, t2.column_id;

SELECT 'select * from ' || t.table_name || ';'
  FROM user_tables t
 ORDER BY t.table_name;
SELECT 'analyze table ' || t.table_name || ' compute STATISTICS;'
  FROM user_tables t
 ORDER BY t.table_name;
SELECT * FROM user_tables u WHERE u.table_name = 'ACCESS_STATUS';
SELECT * FROM access_status;
SELECT * FROM ACCESS_STATUS;

SELECT * FROM user_tables u WHERE TRIM(u.num_rows) IS NOT NULL;

SELECT * FROM user_tables u ORDER BY u.table_name;
SELECT * FROM ACCOUNT_STATUS;
analyze TABLE access_status compute statistics;

SELECT * FROM payment_period p WHERE p.payment_date > SYSDATE -60 ORDER BY p.payment_date;

SELECT * FROM user_constraints;


 SELECT * FROM transaction_history th, Transaction_Provider tp WHERE th.trx_id = tp.trx_id
 AND th.sequence= tp.sequence
 AND th.transaction_type_id IN ()
 AND th.entry_datetime >=to_date('2015-06-01','YYYY-MM-DD') AND
 th.entry_datetime < to_date('2015-07-01','YYYY-MM-DD');
 
