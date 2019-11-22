**simple multiDatasource** 见 commit: 282e040725f1b0352674e1497b11f8bfa8395293

**multiDataSource** 基于 **aop** 主要逻辑是，abstractRoutingDataSource ，配置了，key->datasource的映射。
当使用connection时，需要使用对应的key来指定dataSource，dataSource.getConnection()；
springboot + mybatis 配置时注意，mapperScanners 扫描多个目录下的mapper，DynamicDataSourceAop切面的顺序要先与transaction的切面，防止切到错误的dataSources上。

多数据源使用场景可以使主从复制，也可以是一个项目配置多个数据库。

