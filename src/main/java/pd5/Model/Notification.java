package pd5.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Notification implements Sendable {
    protected final int id;
    protected String content;
    protected final String createdAt = LocalDate.now().toString();
    protected final AtomicBoolean sent = new AtomicBoolean(false);

    protected abstract void validateBeforeSend();

    protected abstract void performSend();

    @Override
    public void send() {
        if (sent.compareAndSet(false, true)) {
            validateBeforeSend();
            performSend();
        } else {
            throw new IllegalStateException("Notification already sent");
        }
    }
}
