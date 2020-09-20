package own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor.handler;

import com.lmax.disruptor.WorkHandler;
import lombok.Data;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.IdDataWrapper;

@Data
public abstract class IdMessageConsumer implements WorkHandler<IdDataWrapper> {

    protected String consumerId;

    public IdMessageConsumer(String consumerId) {
        this.consumerId = consumerId;
    }

    public IdMessageConsumer() {
    }
}