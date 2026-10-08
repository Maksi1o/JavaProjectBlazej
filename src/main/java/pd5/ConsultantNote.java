package pd5;

public class ConsultantNote {
    private final int id;
    private String content;
    private final String createdAt;
    private final String author;
    private boolean archive;

    public ConsultantNote(int id, String content, String createdAt, String author) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be empty.");
        }
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.archive = false;
    }

    public void archive() {
        archive = true;

    }

    public void editContent(String newContent) {
        if (archive) {
            throw new IllegalStateException("Archived notes cannot be edited.");
        }
        if (newContent == null || newContent.isBlank())
            throw new IllegalArgumentException("Content cannot be empty.");

        this.content = newContent;
    }
}
