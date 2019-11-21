重置数据库表的 auto_increment:
ALTER TABLE tablename AUTO_INCREMENT = 1

查询数据库表的 auto_increment:
SELECT `AUTO_INCREMENT`
FROM  INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'test'
AND   TABLE_NAME   = 'dbcp';