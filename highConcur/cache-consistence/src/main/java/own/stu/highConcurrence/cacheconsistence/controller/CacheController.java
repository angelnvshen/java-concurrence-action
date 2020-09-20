package own.stu.highConcurrence.cacheconsistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.highConcurrence.cacheconsistence.common.cache.GlobalCache;

@RequestMapping("/cache")
@RestController
public class CacheController {

    @Autowired
    private GlobalCache cache;

    @RequestMapping("/get/{key}")
    public Long get(@PathVariable("key") String key) {
        return cache.get(key);
    }
}
