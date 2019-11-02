#### redis 主从复制配置

**不生效原因**
1：修改 bind 0.0.0.0 ; 
2： Slave即的db save失败，因为没有写权限 

**程序检查主从复制是否写入磁盘的逻辑**

<img src="../../picture/WeChat1f3ee898bb372a02e191b8746679829f.png" width = "100%" height = "100%" alt="图片名称" align=center />