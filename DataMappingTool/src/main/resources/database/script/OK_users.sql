SELECT * FROM users u WHERE u.login = '0000';

SELECT DISTINCT th.transaction_type_id, tt.descr, tm.module_id, m.descr
  FROM transaction_history th,
       transaction_user    tu,
       users               u,
       transaction_type    tt,
       transaction_module  tm,
       module              m
 WHERE tu.user_id = u.user_id
   AND u.login = '0000'
      
   AND th.trx_id = tu.trx_id
   AND th.sequence = tu.sequence
   AND tt.transaction_type_id = th.transaction_type_id
      
   AND th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND tm.module_id = m.module_id;
   
   SELECT * FROM MODULE;
/*
4036
4034
202000

*/
SELECT * FROM transaction_type t WHERE t.transaction_type_id = '4034';

SELECT th.*, tu.*, u.*
  FROM transaction_history th,
       transaction_user    tu,
       users               u,
       transaction_type    tt
 WHERE tu.user_id = u.user_id
   AND u.login = '0000'
   AND th.trx_id = tu.trx_id
   AND th.sequence = tu.sequence
   AND tt.transaction_type_id = th.transaction_type_id
   AND tt.transaction_type_id = '202000'
 ORDER BY th.entry_datetime DESC;

SELECT th.*, tu.*, u.*
  FROM transaction_history th,
       transaction_user    tu,
       users               u,
       transaction_type    tt
 WHERE tu.user_id = u.user_id
   AND u.login = '0000'
   AND th.trx_id = tu.trx_id
   AND th.sequence = tu.sequence
   AND tt.transaction_type_id = th.transaction_type_id
   AND tt.transaction_type_id = '4034'
 ORDER BY th.entry_datetime DESC;

SELECT th.*, tu.*, u.*
  FROM transaction_history th,
       transaction_user    tu,
       users               u,
       transaction_type    tt
 WHERE tu.user_id = u.user_id
   AND u.login = '0000'
   AND th.trx_id = tu.trx_id
   AND th.sequence = tu.sequence
   AND tt.transaction_type_id = th.transaction_type_id
   AND tt.transaction_type_id = '4036'
 ORDER BY th.entry_datetime DESC;
 
 SELECT * FROM Transaction_History th WHERE th.trx_id='334120903';
 SELECT * FROM Transaction_Provider p WHERE p.trx_id='334120903';
 SELECT * FROM Transaction_User t WHERE t.trx_id='334120903';
 SELECT * FROM Transaction_Module t WHERE t.trx_id='334120903';
 SELECT * FROM USERS u WHERE u.user_id='16919';

SELECT * FROM provider p WHERE p.provider_id='15369';


 SELECT * FROM Transaction_History th WHERE th.trx_id='334115760';
 SELECT * FROM Transaction_Provider p WHERE p.trx_id='334115760';
 SELECT * FROM Transaction_User t WHERE t.trx_id='334115760';
 SELECT * FROM Transaction_Module t WHERE t.trx_id='334115760';
 SELECT * FROM USERS u WHERE u.user_id='3733';
 
 SELECT * FROM transaction_history th WHERE th.transaction_type_id IN (4034,4036) ORDER BY th.trx_id DESC;
  
 SELECT * FROM transaction_history th WHERE th.trx_id >= '334136761' ORDER BY th.trx_id;
 
 SELECT * FROM transaction_history th WHERE th.trx_id ='334133793';
 SELECT * FROM transaction_user tu WHERE tu.trx_id='334133793';
 SELECT * FROM Transaction_Module tm WHERE tm.trx_id='334133793';
 SELECT * FROM USERS u WHERE u.user_id='31';
 
SELECT MAX(th.entry_datetime), tm.module_id
  FROM transaction_history th, transaction_module tm
 WHERE th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND th.transaction_type_id = '4034'
 GROUP BY tm.module_id;
 
 SELECT MAX(th.entry_datetime), tm.module_id
  FROM transaction_history th, transaction_module tm
 WHERE th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND th.transaction_type_id = '4035'
 GROUP BY tm.module_id;
 
 SELECT MAX(th.entry_datetime), tm.module_id
  FROM transaction_history th, transaction_module tm
 WHERE th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND th.transaction_type_id = '4036'
 GROUP BY tm.module_id;
 
 SELECT MAX(th.entry_datetime), tm.module_id
  FROM transaction_history th, transaction_module tm
 WHERE th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND th.transaction_type_id = '202000'
 GROUP BY tm.module_id;
