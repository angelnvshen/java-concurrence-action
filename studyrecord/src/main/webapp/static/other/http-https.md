###https 原理

##### 1:明文传输

C =====（hack）======S (中间可被黑)

##### 2: 使用加密传输 

A:对称加密 【 f1(k, data) = x, f2(k, data) =x】。加密函数f1 ,  解密函数f2 , k 密钥。（因为 S 不可能为每个客户端生成一个k,所以公用一个k,很显然是不安全的）
	  C ======== x =========S, 此过程中，黑客可以拿到数据x,根据密钥解密

B:非对称加密【f(pk, data) =x, f(sk, x) = data | f(sk, data) = y, f(pk, y) = data】。公钥和私钥都是可以互相解密的。 （前提，**S 的pk是公开的**，C 发送数据时使用pk加密，只有 S的 sk可以解密，此时是安全的，但是 S 向C的发送数据时，f(sk, data) = Y 是不安全的，因为pk 也能被黑客知道，就可以解密。
C=======X(f(pk, data)) ====> S (safe)
C<=======Y(f(sk, data)) ====S (unsafe)

##### 3:对称 + 非对称

首先c和s非对称协商出对称密钥k，使用k加密传输。

1：c =====ask for pk ====> S
2:   c <====send to pk ==== S
3:  c ===== x(f(pk, key)) ====> S ( f(sk, x) = key) 得到对称加密的key
4:  c ===== x1(f(key, data)) ==== S, 使用对称加密进行传输
————————————————————————————————
1：c =====ask for pk     [===> hack ====ask for pk  ] ====> S  (pk 是 s的， pk1是 hack的)
2:   c <====send to pk1 [==== hack <===send to pk ] ===== S (c得到的是hack的 pk1, 误以为是s的pk)
3:  c ===== x(f(pk1, key))  [==== hack == x(f(pk, key))  ] ====> S (c 根据 pk1加密发送key,hack 根据 sk1 解密后，得到 key，伪C发送 f(pk, key) 到S ,S得到数据后，解密得到key，开始传输)
4:  c ===== x1(f(key, data))[===== x1(f(key, data))   ] ==== S
中间都会有hack的参与。根源在于第一步时c获取 s的pk问题。俗称中间人问题。

##### 4：加入CA

CA也有 cpk,csk。S首先生成证书，f(csk, pk) == license。C获取到S的license后，使用cpk解密获取到S的pk。以后进行3：对称+非对称传输。保证pk的安全。 因为c在获取cpk同样会有中间人问题，所以操作系统会嵌入大量ca机构的cpk。保证不会出现网络上获取pk的问题。




