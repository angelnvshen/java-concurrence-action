package own.stu.highConcurrence.netty.server;

import io.netty.channel.ChannelHandlerContext;
import own.stu.highConcurrence.netty.config.TypeData;
import own.stu.highConcurrence.netty.model.Model;

public class Server3Handler extends Middleware {

    public Server3Handler() {
        super("server");
    }

    @Override
    protected void handlerData(ChannelHandlerContext ctx, Object msg) {
        // TODO Auto-generated method stub
        Model model = (Model) msg;
        System.out.println("server 接收数据 ： " + model.toString());
        model.setType(TypeData.CUSTOMER);
        model.setBody("---------------");
        ctx.channel().writeAndFlush(model);
        System.out.println("server 发送数据： " + model.toString());
    }

    @Override
    protected void handlerReaderIdle(ChannelHandlerContext ctx) {
        // TODO Auto-generated method stub
        super.handlerReaderIdle(ctx);
        System.err.println(" ---- client " + ctx.channel().remoteAddress().toString() + " reader timeOut, --- close it");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.err.println(name + "  exception" + cause.toString());
    }
}
