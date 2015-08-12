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
   
--pos SIGNON 
SELECT MAX(th.entry_datetime), tm.module_id
  FROM transaction_history th, transaction_module tm
 WHERE th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND th.transaction_type_id = '4034'
 GROUP BY tm.module_id;
 --pos SIGN off
SELECT MAX(th.entry_datetime), tm.module_id
  FROM transaction_history th, transaction_module tm
 WHERE th.trx_id = tm.trx_id
   AND th.sequence = tm.sequence
   AND th.transaction_type_id = '4035'
 GROUP BY tm.module_id;
