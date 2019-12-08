package own.stu.learn.withSpringBoot.websocket;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * copy from web
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    static Log log = LogFactory.getLog(WebSocketServer.class);

    //静态变量，用来记录当前在线连接数。
    private static AtomicLong onlineCount = new AtomicLong(0);

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<SocketInfo> webSocketSet = new CopyOnWriteArraySet<>();

    private SocketInfo socketInfo = null;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());

        socketInfo = new SocketInfo(session, sid);
        webSocketSet.add(socketInfo);     //加入set中
        try {
            session.getBasicRemote().sendText("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + session.getId() + "的信息:" + message);
        //群发消息
        for (SocketInfo item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    /*public void sendMessage(SocketInfo socketInfo, String message) throws IOException {
        Session session = socketInfo.getSession();
        if (session != null && session.isOpen())
            session.getBasicRemote().sendText(message);
    }*/


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (SocketInfo item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static long getOnlineCount() {
        return onlineCount.get();
    }

    public static void addOnlineCount() {
        onlineCount.getAndIncrement();
    }

    public static void subOnlineCount() {
        onlineCount.getAndDecrement();
    }

    @Data
    static class SocketInfo {
        //与某个客户端的连接会话，需要通过它来给客户端发送数据
        private Session session;

        //接收sid
        private String sid = "";

        public SocketInfo(Session session, String sid) {
            this.session = session;
            this.sid = sid;
        }

        public void sendMessage(String message) throws IOException {

            if (session != null && session.isOpen())
                session.getBasicRemote().sendText(message);
        }
    }
}