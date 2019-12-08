package simple.webSocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/hello")
public class HelloWebSocket {

    @OnOpen
    public void open(Session session) {
        System.out.println(session.getId() + " open ");
    }

    @OnMessage
    public void onTextMessage(Session session, String message) {
        try {
            if (session.isOpen()) {

                System.out.println(message);
                session.getBasicRemote().sendText("server : " + message);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println(session.getId() + " closed ");
    }

}
