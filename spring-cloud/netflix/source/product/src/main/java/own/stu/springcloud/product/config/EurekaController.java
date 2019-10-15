package own.stu.springcloud.product.config;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eureka")
public class EurekaController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("server-config")
    public void getEurekaConfigInfo(){
        ApplicationInfoManager applicationInfoManager = applicationContext.getBean(ApplicationInfoManager.class);
        EurekaInstanceConfig eurekaInstanceConfig = applicationInfoManager.getEurekaInstanceConfig();

        System.out.println("----");
        System.out.println(eurekaInstanceConfig);
    }

    @RequestMapping(value = "/offline", method = RequestMethod.GET)
    public void offLine(){

        ApplicationInfoManager applicationInfoManager = applicationContext.getBean(ApplicationInfoManager.class);

        DiscoveryManager.getInstance().shutdownComponent();
    }
}
