package Server;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/text/chat")
public class TextChatServerEndpoint {

    private static Map<String, Session> sessions = new HashMap<>();

    private void broadcast(String msg) {
        sessions.forEach((id, ss) -> {
            try {
                ss.getBasicRemote().sendText(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session.getId(), session);
        broadcast("Someone joined the chat!");
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        broadcast(msg);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session.getId());
        broadcast("Someone left the chat!");
    }

    @OnError
    public void onError(Session session, Throwable t) {
        try { session.close(); } catch (IOException e) {}
    }
}
