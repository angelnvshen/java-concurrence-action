## 1 binlog 日志

二进制日志（binary log) 记录了对mysql数据库执行更新的所有操作，但不包括select和show这类操作。因为这类操作对数据库本身没有修改。但是操作本身并没有导致数据库发生变化，也可能写进二进制日志（如 update T set col = 1 where id = 2; 没有id=2的记录）。



二级制日志格式

	* Row: 记录表中行更新状况。
	* Statement: 记录的是逻辑的sql语句。
	* Mixed



这两种日志有以下三点不同。

1. redo log 是 InnoDB 引擎特有的；binlog 是 MySQL 的 Server 层实现的，所有引擎都可以使用。

2. redo log 是物理日志，记录的是“在某个数据页上做了什么修改”；binlog 是逻辑日志，记录的是这个语句的原始逻辑，比如“给 ID=2 这一行的 c 字段加 1 ”。

3. redo log 是循环写的，空间固定会用完；binlog 是可以追加写入的。“追加写”是指 binlog 文件写到一定大小后会切换到下一个，并不会覆盖以前的日志。

   

update 语句的执行流程图，图中浅色框表示是在 InnoDB 内部执行的，深色框表示是在执行器中执行的。

![2e5bff4910ec189fe1ee6e2ecc7b4bbe](/Users/ScorpionKing/IdeaProject_cl/core/studyrecord/src/main/webapp/picture/2e5bff4910ec189fe1ee6e2ecc7b4bbe.png)