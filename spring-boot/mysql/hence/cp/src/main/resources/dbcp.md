####1:BasicDataSource
**核心基础配置**
username
password
url
driverClassName

**connection属性的配置**
defaultAutoCommit：是否默认自动提交
defaultReadOnly：是否为只读
defaultTransactionIsolation：默认的事务隔离级别
defaultCatalog：connection的默认路径

这部分配置的作用都是标记从池中获取的connection应该具备的属性，而它们是否生效，就要看具体的JDBC驱动是不是支持了。

**池属性的配置**
initialSize
maxActive:池中最多可容纳的活着的连接数量，它是整个连接池的边界，当池中活着的连接达到这个数值时，dbcp将不会再新建connection，而是等待获取其他线程释放的。

maxIdle:连接池最多可以保持的连接数，应用场景就是dbcp会定时回收池中那些空闲连接（已激活但未使用的连接）,直到池中的数量减少到maxIdle为止
minIdle
maxWait:定义了获取连接的等待时间，如果超过这个时间则抛出异常（默认配置）

**高可用属性的配置**

connectionInitSqls:是在一个connection被创建之后调用的一组sql，这组sql可用来记录日志，也可以用来初始化一些数据，总之，经过connectionInitSqls之后的connection一定是正常可用的。

testOnBorrow
testOnReturn
validationQuery
validationQueryTimeout

testOnBorrow和testOnReturn的关注点则在connection从池中“取出”和“归还”，这两个关键的动作上，当他们被设置为true时，在取出和归还connection时，都需要完成校验，如果校验不通过，这个connection将被销毁。校验的sql由validationQuery定义，且定义的sql语句必须是查询语句，而且查询至少一列。validationQueryTimeout定义的是校验查询时长，如果超过这个时间，则认定为校验失败。



dbcp在运行时还在内部维护了一个“清理器”（Eviction），主要用于销毁那些已被创建，但长时间未被使用的连接，Eviction在运行的时候，会用到下列属性：

testWhileIdle：清楚一个连接时是否需要校验
timeBetweenEvictionRunsMillis：Eviction运行的时间周期
numTestsPerEvictionRun：Eviction在运行时一次处理几个连接

PreparedStatements是可以缓存是，尤其在一些支持游标的数据库中（Oracle、SQL Server、DB2、Sybase），启用PreparedStatements缓存和不启用直接的性能可能相差一个数量级。dbcp配置PreparedStatements缓存主要用到以下两个配置。

poolPreparedStatements：是否缓存PreparedStatements
maxOpenPreparedStatements：缓存PreparedStatements的最大个数

![e781608a-8e5f-3ac6-b4da-79db7e1e9cd0](static/e781608a-8e5f-3ac6-b4da-79db7e1e9cd0.jpg)