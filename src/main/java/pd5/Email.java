package pd5;

public class Email extends Notification {
    private final String receiver;
    private final String topic;

    public Email(int id, String content, String createdAt, String receiver, String topic) {
        super(id, content, createdAt);
        this.receiver = receiver;
        this.topic = topic;
        if (receiver == null || receiver.isBlank()) {
            throw new IllegalArgumentException("Reciever cannot be empty.");
        }
        if (topic == null || topic.isBlank()) {
            throw new IllegalArgumentException("Topic cannot be empty.");
        }

    }

    @Override
    protected void validateBeforeSend() {
        if (!receiver.contains("@") || !receiver.contains(".")) {
            throw new IllegalArgumentException("Invalid email adress.");
        }

    }

    @Override
    protected void performSend() {
        System.out.println("E-mail send to: " + receiver);
    }
}
