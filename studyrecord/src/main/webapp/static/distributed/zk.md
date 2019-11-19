####leader 选举

code analysis，paxos一致性前半段的实现 Procosor的选举过程

```java
QuorumMain.main(){
  // 获取指定的配置文件 eg: zoo.cfg
  uorumPeerConfig config = new QuorumPeerConfig();
  if (args.length == 1) {
    config.parse(args[0]);
  }
  // 快照及日志的清理
  DatadirCleanupManager purgeMgr = new DatadirCleanupManager(
    config.getDataDir(),config.getDataLogDir(),
    config.getSnapRetainCount(),config.getPurgeInterval());
  purgeMgr.start();
  if (args.length == 1 && config.isDistributed()) {// 有配置且多个节点启动集群
    runFromConfig(config);
  } else {
    LOG.warn("Either no config or no quorum defined in config, running "
             + " in standalone mode");
    // there is only server in the quorum -- run as standalone
    ZooKeeperServerMain.main(args);
  }
}
QuorumMain.runFromConfig(QuorumPeerConfig config){
  // readConfig from .cfg, setValue to config
  quorumPeer.initialize();  
  quorumPeer.start();
  quorumPeer.join();
}
// ---------
QuorumPeer.start(){
  loadDataBase();// 加载数据文件
  startServerCnxnFactory();// 建立socket，等待客户端连接，并处理请求
  try {
    adminServer.start();
  } catch (AdminServerException e) {
    LOG.warn("Problem starting AdminServer", e);
    System.out.println(e);
  }
  startLeaderElection();// leader选举前的初始化
  super.start(); // call run()
}
QuorumPeer.run(){
 // 其中的选举逻辑：
 // 1，生成一张投给自己的vote。 2，将vote发送给集群的其他节点。 3，接受其他节点的vote，pk(epoch, zxid, myid)。4，投票（根据pk结果）5，统计
 
  // 发生选举的时机有两种：1服务器集群启动时，没有leader，2新加入节点（包括，断线后重连）
  // 情况2,集群中有leader，当新节点加入进来时，收到其他节点的反馈信息，即可知道leader信息。
  // 情况1,所有节点都是looking状态，第一个节点生成vote(myid, zxid, epoch)。其他节点接受后pk,根据epoch，zxid,myid 的大小，来修改当前选票，并通知其他节点，当有集群中根据过半选举机制，找到leader后，其他节点会更新自己的状态leader,foller,observer。
  
  // 细节，每个节点会包含sendVote的map<myid, queue<vote>，sendWorkerMap和前一个map是对应的，和receivedVote的queue。
}

```

![image.png](/Users/my/IdeaProjects_own/core/studyrecord/src/main/webapp/picture/zookeeper-select-leader.jpg)

### 角色

##### leader

主要工作：
1：事务请求的唯一处理和调度者，保证集群类事务的顺序行。
2：集群内部各服务器的调度者。

##### follower

主要工作：
1：非事务请求的处理者，事务请求的转发者（转发的目的，事务必须有leader进行处理，保证分布式系统的一致性）
2：参与leader选举投票
3：参与事务请求proposal的投票

#####observer

主要工作，存在的意义在于在不影响集群事务处理能力的前提下，提升集群非事务处理的能力。
1：非事务请求的处理者，事务请求的转发者

#### 分布式锁

排它锁：利用临时节点，如果能够创建节点表示获取锁，其他节点wath该节点，如果删除，则创建，失败block，并注册监听。锁的释放，获取锁的节点，删除节点或者断开连接session_timeOut超时。

共享锁：利用临时的顺序节点，获取节点列表，如果是读请求，判断自己序号是否是最小的或者所有的都是读请求，是则占用锁，否则watch比自己小的最后一个写节点。如果是写请求，判断自己的序号最小，是则占用锁，否则watch比自己小的节点。?