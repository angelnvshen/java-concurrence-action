###redis数据结构

#### 字典（map)

![](/Users/ScorpionKing/IdeaProject_cl/core/redis-all/images/WX20190704-161310@2x.png){:height="20%" width="20%"}

![WX20190704-162603@2x](/Users/ScorpionKing/IdeaProject_cl/core/redis-all/images/WX20190704-162603@2x.png)

![WX20190704-162648@2x](/Users/ScorpionKing/IdeaProject_cl/core/redis-all/images/WX20190704-162648@2x.png)

![WX20190704-162908@2x](/Users/ScorpionKing/IdeaProject_cl/core/redis-all/images/WX20190704-162908@2x.png)

渐进式的rehash:不是一次性集中式的rehash，而是一个分多次，渐进式的完成rehash，字典表中维持一个rehashIdx，初始化为0，对每个索引值进行rehash，rehashIdx + 1，直至完成后 rehashidx 设置为-1。



####跳跃表