package own.stu.highConcurrence.cacheconsistence.nettyServer.channelHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import own.stu.highConcurrence.cacheconsistence.nettyServer.disruptor.IdDataMessageQueueHelper;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.RequestData;
import own.stu.highConcurrence.cacheconsistence.nettyServer.model.Result;

@Component
@Scope("prototype")
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private IdDataMessageQueueHelper helper;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         TranslatorData request = (TranslatorData)msg;
         System.err.println("Sever端: id= " + request.getId()
         + ", name= " + request.getName()
         + ", message= " + request.getMessage());
         //数据库持久化操作 IO读写 ---> 交给一个线程池 去异步的调用执行
         TranslatorData response = new TranslatorData();
         response.setId("resp: " + request.getId());
         response.setName("resp: " + request.getName());
         response.setMessage("resp: " + request.getMessage());
         //写出response响应信息:
         ctx.writeAndFlush(response);
         */
        RequestData<String> request = (RequestData<String>) msg;
        System.out.println(msg + " received from client");
        helper.publishEvent(new Result(request.getData()), ctx);
    }

}