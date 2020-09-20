package own.stu.highConcurrence.cacheconsistence.common.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import own.stu.highConcurrence.cacheconsistence.model.IdGene;
import own.stu.highConcurrence.cacheconsistence.service.IIdGeneService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class GlobalCache {

    @Autowired
    private IIdGeneService iIdGeneService;

    private LoadingCache<String, Long> idsCache;

    private final Long maxLen = 10000L;

    public GlobalCache() {
        this.idsCache = builtCache();
    }

    private LoadingCache<String, Long> builtCache() {
        idsCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(MY_LISTENER)
                .build(
                        new CacheLoader<String, Long>() {
                            public Long load(String key) throws Exception {
                                return createExpensiveId(key);
                            }
                        });

        return idsCache;
    }

    private Long createExpensiveId(String key) throws InterruptedException {
        // TimeUnit.SECONDS.sleep(2);
        assert key != null;
        Long result = null;

        IdGene ids = iIdGeneService.getByKey(key);
        if (ids == null) {

            result = 1L;

            ids = new IdGene();
            ids.setBusinessKey(key);
            ids.setLastMax(maxLen);
            ids.setType(1);
            iIdGeneService.save(ids);

        } else {
            result = ids.getLastMax();

            ids.setLastMax(ids.getLastMax() + maxLen);
            iIdGeneService.updateById(ids);
        }

        return result;
    }

    public Long get(String key) {
        Long id = -1L;
        try {
            synchronized (key) {
                id = idsCache.get(key);
                idsCache.put(key, id + 1);
            }
            return id;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return id;
    }
}
