SELECT * FROM user_tables t ORDER BY t.table_name;

SELECT t.table_name || '=' || t.table_name
  FROM user_tables t
 ORDER BY t.table_name;
 
 SELECT t.table_name || '=NA' FROM user_tables t
 ORDER BY t.table_name;

SELECT * FROM user_tables u WHERE u.table_name = 'USER_PROVIDER';
SELECT * FROM user_tab_columns;

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

SELECT * FROM all_tables a WHERE a.owner = 'ECCOK_30D_0702_04';

SELECT * FROM all_tables a WHERE a.owner = 'ECCIN_RD_45D_0706_03';

SELECT * FROM all_tab_columns;

analyze TABLE user_provider compute statistics;

SELECT * FROM all_tables a WHERE a.owner = 'ECCOK_30D_0702_04';

SELECT * FROM all_tables a WHERE a.owner = 'ECCIN_RD_45D_0706_03';

SELECT * FROM all_tab_columns a WHERE a.owner = 'ECCIN_RD_45D_0706_03';

------------------------------
DROP TABLE compare_result;
CREATE TABLE compare_result(group_name VARCHAR2(20) ,
                            ok_table_name VARCHAR2(50) ,
                            ok_table_column VARCHAR2(50) ,
                            ok_table_column_type VARCHAR2(50),
                            ok_table_column_length NUMBER ,
                            ok_table_column_index NUMBER,
                            
                            in_table_level Varchar2(5),
                            in_table_name VARCHAR2(50) ,
                            in_table_column VARCHAR2(50) ,
                            in_table_column_type VARCHAR2(50) ,
                            in_table_column_length NUMBER ,
                            in_table_column_index NUMBER,
                            
                            new_table_level Varchar2(5),
                            new_table_name VARCHAR2(50) ,
                            new_table_column VARCHAR2(50) ,
                            new_table_column_type VARCHAR2(50) ,
                            new_table_column_length NUMBER ,
                            new_table_column_index NUMBER,
                            
                            version_comment VARCHAR2(1000)                            
                            );
DROP TABLE compare_tables;
CREATE TABLE compare_tables(state VARCHAR2(2) NOT NULL,
    table_name VARCHAR2(50) NOT NULL,
                            table_column VARCHAR2(50) NOT NULL,
                            table_column_index NUMBER NOT NULL,
                            table_column_type VARCHAR2(50) NOT NULL,
                            table_column_length NUMBER NOT NULL,
                            table_records NUMBER DEFAULT 0 NOT NULL);

INSERT INTO compare_tables VALUES ('OK','tableName','column','column type',0,0);
SELECT * FROM compare_tables t WHERE t.state ='IN';
SELECT * FROM compare_tables t ORDER BY t.state, t.table_name,t.table_column_index;

SELECT * FROM compare_tables  t WHERE t.table_name ='' AND t.state ='OK';
SELECT * FROM compare_result;
SELECT * FROM compare_result t ORDER BY t.group_name,t.ok_table_name,t.ok_table_column_index,t.in_table_name,t.in_table_column_index;
SELECT * FROM compare_result t WHERE t.group_name ='NA' ORDER BY t.ok_table_name;


SELECT t.group_name,
       t.ok_table_name,
       t.ok_table_column,
       t.ok_table_column_type,
       t.ok_table_column_length,
       
       t.in_table_name,
       t.in_table_column,
       t.in_table_column_type,
       t.in_table_column_length,
       t.new_table_name,
       t.new_table_column,
       t.new_table_column_type,
       t.new_table_column_length,
       '',
       t.version_comment
  FROM compare_result t
  WHERE t.group_name ='STATIC_TBLS'
 ORDER BY t.group_name,
          t.ok_table_name,
          t.ok_table_column_index,
          t.in_table_name,
          t.in_table_column_index;

TRUNCATE TABLE compare_tables;
INSERT INTO compare_result VALUES ('NA','OK_table','ok_column','ok type',0,'IN_table','In_column','in type',0);

SELECT * FROM compare_result c WHERE c.ok_table_name ='' AND c.ok_table_column ='';

SELECT * FROM compare_result c WHERE c.in_table_name ='' AND c.in_table_column ='';
