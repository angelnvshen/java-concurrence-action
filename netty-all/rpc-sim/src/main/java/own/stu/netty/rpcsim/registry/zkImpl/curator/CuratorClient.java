package own.stu.netty.rpcsim.registry.zkImpl.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import own.stu.netty.rpcsim.registry.zkImpl.Constant;

import java.util.List;

public class CuratorClient {
    private CuratorFramework client;

    public CuratorClient(String zkAddress) {
        client = CuratorClientFactory.INSTANCE.getClient(zkAddress);
    }

    public static void main(String[] args) throws Exception {
        CuratorClient client = new CuratorClient(Constant.ADDRESS);
        client.pathExists("/registry/xxxx");
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public boolean pathExists(String path) throws Exception {
        return client.checkExists().forPath(path) != null;
    }

    public void addConnectionStateListener(ConnectionStateListener connectionStateListener) {
        client.getConnectionStateListenable().addListener(connectionStateListener);
    }

    public String createEphemeralSequentialPathData(String path, byte[] data) throws Exception {
        return createPathData(path, data, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public String createPathData(String path, byte[] data) throws Exception {
        return createPathData(path, data, CreateMode.PERSISTENT);
    }

    public String createPathData(String path, byte[] data, CreateMode createMode) throws Exception {
        ACLBackgroundPathAndBytesable<String> byteAble = client.create().creatingParentsIfNeeded()
                .withMode(createMode);
        if (data == null)
            return byteAble.forPath(path);
        else
            return byteAble.forPath(path, data);
    }

    public void updatePathData(String path, byte[] data) throws Exception {
        client.setData().forPath(path, data);
    }

    public void deletePath(String path) throws Exception {
        client.delete().forPath(path);
    }

    public void watchNode(String path, Watcher watcher) throws Exception {
        client.getData().usingWatcher(watcher).forPath(path);
    }

    public byte[] getData(String path) throws Exception {
        return client.getData().forPath(path);
    }

    public List<String> getChildren(String path) throws Exception {
        return client.getChildren().forPath(path);
    }

    public void watchTreeNode(String path, TreeCacheListener listener) {
        TreeCache treeCache = new TreeCache(client, path);
        treeCache.getListenable().addListener(listener);
    }

    public void watchPathChildrenNode(String path, PathChildrenCacheListener listener) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        //BUILD_INITIAL_CACHE 代表使用同步的方式进行缓存初始化。
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        pathChildrenCache.getListenable().addListener(listener);
    }

    public void close() {
        client.close();
    }
}