package pd5;

public class Sms extends Notification {
    private final String phoneNumber;

    public Sms(int id, String content, String createdAt, String phoneNumber) {
        super(id, content, createdAt);
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() != 9) {
            throw new IllegalArgumentException("Polska gurom");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void validateBeforeSend() {
        if (content.length() > 160) {
            throw new IllegalArgumentException("Content cannot exceed above 160 chars.");
        }
    }

    @Override
    protected void performSend() {
        System.out.println("Message sent to: " + phoneNumber);
    }

}
