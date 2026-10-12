package pd5.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Notification implements Sendable {
    protected final int id;
    protected String content;
    protected final String createdAt;
    protected boolean sent;

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
