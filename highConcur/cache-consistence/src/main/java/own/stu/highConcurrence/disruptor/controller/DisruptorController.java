package own.stu.highConcurrence.disruptor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import own.stu.highConcurrence.disruptor.model.SeriesData;
import own.stu.highConcurrence.disruptor.common.SeriesDataEventQueueHelper;

@RestController
public class DisruptorController {

    @Autowired
    private SeriesDataEventQueueHelper seriesDataEventQueueHelper;

    @GetMapping("/api/test")
    public void demo() {
        seriesDataEventQueueHelper.publishEvent(new SeriesData(""));
        seriesDataEventQueueHelper.publishEvent(new SeriesData("hello world!"));
        seriesDataEventQueueHelper.publishEvent(new SeriesData("hello world!"));
        seriesDataEventQueueHelper.publishEvent(new SeriesData("hello world!"));

    }

    @GetMapping("/api/test2")
    public void demo2() {
        System.out.println("2223333!");
        seriesDataEventQueueHelper.publishEvent(new SeriesData(""));
        seriesDataEventQueueHelper.publishEvent(new SeriesData("hello world2!"));
        seriesDataEventQueueHelper.publishEvent(new SeriesData("hello world!"));
        seriesDataEventQueueHelper.publishEvent(new SeriesData("hello world23!"));

    }
}
 
