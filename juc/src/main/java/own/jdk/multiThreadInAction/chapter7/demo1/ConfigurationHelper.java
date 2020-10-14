package own.jdk.multiThreadInAction.chapter7.demo1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ConfigurationHelper implements ConfigEventListener {
    INSTANCE;

    final ConfigurationManager configurationManager;
    final ConcurrentHashMap<String, Configuration> cachedConfig;

    private ConfigurationHelper() {
        configurationManager = ConfigurationManager.INSTANCE;
        cachedConfig = new ConcurrentHashMap<>();
    }

    public Configuration getConfig(String name) {
        Configuration cfg;
        cfg = getCachedConfig(name);
        if (cfg == null) {
            synchronized (this) {
                cfg = getCachedConfig(name);
                if (cfg == null) {
                    cfg = configurationManager.load(name);
                    cachedConfig.put(name, cfg);
                }
            }
        }
        return cfg;
    }

    public Configuration getCachedConfig(String name) {
        return cachedConfig.get(name);
    }

    public ConfigurationHelper init() {
        // todo
        return this;
    }

    @Override
    public void onConfigLoaded(Configuration cfg) {
        cachedConfig.putIfAbsent(cfg.getName(), cfg);
    }

    @Override
    public void onConfigUpdated(String name, int newVersion, Map<String, String> properties) {
        Configuration cachedConfig = getCachedConfig(name);
        // 更新内容和版本这两个操作必须是原子操作
        synchronized (this) {
            if (cachedConfig != null) {
                cachedConfig.update(properties);
                cachedConfig.setVersion(newVersion);
            }
        }
    }
}
