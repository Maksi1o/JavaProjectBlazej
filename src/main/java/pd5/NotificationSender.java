package pd5;

public class NotificationSender {
    public void sendAll (Sendable[] notifications) {
        for (Sendable notification : notifications) {
            notification.send();
        }
    }
}
