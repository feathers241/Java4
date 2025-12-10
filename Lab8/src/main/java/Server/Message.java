package Server;

public class Message {
    public String text;
    public int type;
    public String sender;
    public int count;

    public Message() {}

    public Message(String text, int type, String sender, int count) {
        this.text = text;
        this.type = type;
        this.sender = sender;
        this.count = count;
    }
}

