package Server;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(
        value = "/json/chat/{username}",
        encoders = { MessageEncoder.class },
        decoders = { MessageDecoder.class }
)
public class JsonChatServerEndpoint {

    private static Map<String, Session> sessions = new HashMap<>();

    private void broadcast(Message message) {
        sessions.forEach((username, session) -> {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        if (sessions.containsKey(username)) {
            throw new RuntimeException("Username already exists");
        }

        session.getUserProperties().put("username", username);
        sessions.put(username, session);

        Message msg = new Message("joined the chat", 0, username, sessions.size());
        broadcast(msg);
    }

    @OnMessage
    public void onMessage(Message message, Session session) {
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) {
        String username = (String) session.getUserProperties().get("username");
        sessions.remove(username);

        Message msg = new Message("left the chat", 1, username, sessions.size());
        broadcast(msg);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close session");
        }
    }
}
