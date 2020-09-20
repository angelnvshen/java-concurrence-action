package own.stu.highConcurrence.cacheconsistence.disruptor;

import com.lmax.disruptor.EventHandler;

public class DataEventHandler implements EventHandler<DataEvent> {

    @Override
    public void onEvent(DataEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费： " + event);
    }
}
