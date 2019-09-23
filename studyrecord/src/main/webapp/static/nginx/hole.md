### 1: mac重启nginx时报nginx.pid不存在的解决办法

在安装nginx后，重启时发现报

```
`nginx: [error] open() ``"/usr/local/var/run/nginx.pid"` `failed (2: No such file or directory)`
```

解决办法

```
sudo nginx -c /usr/local/etc/nginx/nginx.conf
```

### 2：Mac 配置nginx 简单过程

https://www.cnblogs.com/tandaxia/p/8810648.html

