package pd5.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsultantNote {
    private final int id;
    private String content;
    private final String createdAt;
    private final String author;
    private boolean archived;

    public static ConsultantNote create(int id, String content, String createdAt, String author) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        ConsultantNote note = new ConsultantNote(id, createdAt, author);
        note.content = content;
        return note;
    }

    public void archive() {
        if (archived) {
            throw new IllegalArgumentException("Note is already archvied");
        }
        archived = true;
    }

    public void editContent(String newContent) {
        if (archived) {
            throw new IllegalStateException("Archived notes cannot be edited.");
        }
        if (newContent == null || newContent.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.content = newContent;
    }
}
