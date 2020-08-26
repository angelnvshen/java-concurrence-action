package own.stu.highConcurrence.cacheconsistence.nettyClient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.Result;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            Result response = (Result) msg;
            System.out.println("Client端: id= " + response.getId()
                    + ", name= " + response.getKey()
                    + ", message= " + response.getStatus());
        } finally {
            //一定要注意 用完了缓存 要进行释放
            ReferenceCountUtil.release(msg);
        }
    	/*Result response = (Result)msg;
    	String producerId = "code:seesionId:002";
    	MessageProducer messageProducer = RingBufferWorkerPoolFactory.getInstance().getMessageProducer(producerId);
    	messageProducer.onData(response, ctx);*/


    }
}
