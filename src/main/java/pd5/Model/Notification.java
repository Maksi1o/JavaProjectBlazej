package pd5.Model;

public abstract class Notification implements Sendable {
    protected final int id;
    protected String content;
    protected final String createdAt;
    protected boolean sent;

    public Notification(int id, String content, String createdAt) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.sent = false;
    }

    protected abstract void validateBeforeSend();

    protected abstract void performSend();

    @Override
    public void send() {
        if (sent) {
            throw new IllegalStateException("Notification already sent");
        }
        validateBeforeSend();
        performSend();

        sent = true;
    }
}
