### redis 

#### [sort](https://redis.readthedocs.io/en/2.6/key/sort.html)

#### 二进制

setbit , getbit ,bitcount(有个很神奇的算法 位统计算法), bitop 

#### redis 的事件：文件事件和时间事件

**文件事件**：
	redis的服务器是根据套接字和客户端进行连接。而文件事件就是redis对套接字操作的抽象。服务器和客户端的通讯会产生相应的文件事件，而服务器通过通过监听和处理这些事件来完成网络通讯操作。
	redis是基于reactor模式开发了自己的网络事件处理器。
**时间事件**：
	redis服务器中的一下操作(如 serverCorn函数)需要在给定的时间点进行执行，而时间事件就是服务器对这类定时操作的抽象。
	redis将所有的时间事件保存到一个无序的链表中，每当时间事件执行器运行时，会遍历整个链表，查找所有已到达的时间事件，并调用相应的事件处理器。
	无序列表并不影响性能，正常模式下的redis只有一个serverCorn事件。所以服务器几乎是将链表退化成一个指针来使用。

#### redis sentinel 

选择新的leader,使用Raft一致性算法，该算法描述的节点共有三种角色：leader, follower, candidate。正常运行时，只有leader,follower 两种。
redis sentinel: 10s的频率发送info命令到master（获取info信息，可以得到slave节点的情况），之后同样10s的频率发送info命令到slave;
				同时还会建立与master和slave的 _sentinel_:hello 的订阅；
				与同样监控master节点的sentinel节点也会有命令发送。

				主观下线：当sentinel以1s的频率向master,slave,或者sentinel发送 ping命令时，master在 配置的down_after_millseconds时间内，没有回复有效命令，sentinel则认为master下线。
				客观下线：判断master主观下线后，会想监控该mater的其他sentinel节点确认master是否已下线，如果获取到配置qurom数量后的
				sentinel确认消息，则认为客观下线。开始故障转移。
				is_down_by_addr 命令来向其他sentinel确认
				选举流程
				1、某个Sentinel认定master客观下线的节点后，该Sentinel会先看看自己有没有投过票，如果自己已经投过票给其他Sentinel了，在2倍故障转移的超时时间自己就不会成为Leader。相当于它是一个Follower。
				2、如果该Sentinel还没投过票，那么它就成为Candidate。
				3、和Raft协议描述的一样，成为Candidate，Sentinel需要完成几件事情
				1）更新故障转移状态为start
				2）当前epoch加1，相当于进入一个新term，在Sentinel中epoch就是Raft协议中的term。
				3）更新自己的超时时间为当前时间随机加上一段时间，随机时间为1s内的随机毫秒数。
				4）向其他节点发送is-master-down-by-addr命令请求投票。命令会带上自己的epoch。
				5）给自己投一票，在Sentinel中，投票的方式是把自己master结构体里的leader和leader_epoch改成投给的Sentinel和它的epoch。
				4、其他Sentinel会收到Candidate的is-master-down-by-addr命令。如果Sentinel当前epoch和Candidate传给他的epoch一样，说明他已经把自己master结构体里的leader和leader_epoch改成其他Candidate，相当于把票投给了其他Candidate。投过票给别的Sentinel后，在当前epoch内自己就只能成为Follower。
				5、Candidate会不断的统计自己的票数，直到他发现认同他成为Leader的票数超过一半而且超过它配置的quorum（quorum可以参考《redis sentinel设计与实现》）。Sentinel比Raft协议增加了quorum，这样一个Sentinel能否当选Leader还取决于它配置的quorum。
				6、如果在一个选举时间内，Candidate没有获得超过一半且超过它配置的quorum的票数，自己的这次选举就失败了。
				7、如果在一个epoch内，没有一个Candidate获得更多的票数。那么等待超过2倍故障转移的超时时间后，Candidate增加epoch重新投票。
				8、如果某个Candidate获得超过一半且超过它配置的quorum的票数，那么它就成为了Leader。
				9、与Raft协议不同，Leader并不会把自己成为Leader的消息发给其他Sentinel。其他Sentinel等待Leader从slave选出master后，检测到新的master正常工作后，就会去掉客观下线的标识，从而不需要进入故障转移流程。
	
				选举新的master: 排除已经下线的slave,和最近没有通讯（5s内没有回复)的slave, 根据priority，offset, runid 选出new_master。