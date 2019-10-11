package own.spring.core.springmvc.controller.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

@Controller
public class WebAsyncController {

    @RequestMapping("/call")
    @ResponseBody
    public WebAsyncTask<String> asyncCall() {
        //借助mvcTaskExecutor在另外一个线程调用
        //此时Servlet容器线程已经释放,可以处理其他的请求

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "Callable result";
        };
        System.out.println("asyncCall()");
        return new WebAsyncTask<>(5500, callable);//允许指定timeout时间
    }
}
