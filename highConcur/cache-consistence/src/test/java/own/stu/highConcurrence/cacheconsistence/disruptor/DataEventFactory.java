package own.stu.highConcurrence.cacheconsistence.disruptor;

import com.lmax.disruptor.EventFactory;

public class DataEventFactory implements EventFactory<DataEvent> {
    @Override
    public DataEvent newInstance() {
        return new DataEvent(); // 空的数据对象event
    }
}
