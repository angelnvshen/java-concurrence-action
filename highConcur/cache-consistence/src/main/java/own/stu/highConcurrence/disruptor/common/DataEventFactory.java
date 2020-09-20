package own.stu.highConcurrence.disruptor.common;

import own.stu.highConcurrence.disruptor.model.SeriesDataEvent;

public class DataEventFactory implements com.lmax.disruptor.EventFactory<SeriesDataEvent> {

    public SeriesDataEvent newInstance() {
        return new SeriesDataEvent();
    }
}
