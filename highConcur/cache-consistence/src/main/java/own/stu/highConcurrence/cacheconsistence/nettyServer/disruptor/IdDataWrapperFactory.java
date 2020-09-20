package own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor;

import com.lmax.disruptor.EventFactory;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.IdDataWrapper;

public class IdDataWrapperFactory implements EventFactory<IdDataWrapper> {
    @Override
    public IdDataWrapper newInstance() {
        return new IdDataWrapper();
    }
}
