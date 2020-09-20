package own.stu.highConcurrence.disruptor.common;

import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import own.stu.highConcurrence.disruptor.model.SeriesDataEvent;

@Slf4j
public class SeriesDataEventHandler implements WorkHandler<SeriesDataEvent> {

    public void onEvent(SeriesDataEvent event) {
        if (event.getValue() == null || StringUtils.isEmpty(event.getValue().getDeviceInfoStr())) {
            log.warn("receiver series data is empty!");
        } else if ("hello world!".equals(event.getValue().getDeviceInfoStr())) {
            log.info(event.getValue().getDeviceInfoStr());
        } else {
            log.error("Hey baby, hello world!");
        }

    }
}
 
