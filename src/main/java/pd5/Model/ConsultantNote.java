package pd5.Model;

public class ConsultantNote {
    private final int id;
    private String content;
    private final String createdAt;
    private final String author;
    private boolean archived;

    public ConsultantNote(int id, String content, String createdAt, String author) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.archived = false;
    }

    public void archive() {
        if(archived) {
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
