package own.stu.springboot.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link ThreadPoolTaskExecutor} This class is also well suited for management and monitoring (e.g. through JMX),
 * providing several useful attributes: "corePoolSize", "maxPoolSize", "keepAliveSeconds" (all supporting updates at runtime);
 * "poolSize", "activeCount" (for introspection only).
 */
@RequestMapping("modify")
@RestController
public class ModifyThreadPoolController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("core-pool-size")
    public String modifyCorePoolSize(Integer corePoolSize) {
        Assert.notNull(corePoolSize, "num is null");
        ThreadPoolTaskExecutor executor = applicationContext.getBean("customer-executor", ThreadPoolTaskExecutor.class);
        int corePoolSizeCurrent = executor.getCorePoolSize();
        int maxPoolSize = executor.getMaxPoolSize();
        /*corePoolSizeCurrent = Math.max(corePoolSize, corePoolSizeCurrent);
        if (maxPoolSize < corePoolSizeCurrent) {
            executor.setMaxPoolSize(corePoolSizeCurrent);
        }
        executor.setCorePoolSize(corePoolSizeCurrent);*/

        if(corePoolSize > maxPoolSize){
            executor.setMaxPoolSize(corePoolSize);
            executor.setCorePoolSize(corePoolSize);
        }else {
            executor.setCorePoolSize(corePoolSize);
            executor.setMaxPoolSize(corePoolSize);
        }

        System.out.println("maxPoolSize: " + executor.getMaxPoolSize() + ", corePoolSize: " + executor.getCorePoolSize());
        return "modify-success";
    }
}
