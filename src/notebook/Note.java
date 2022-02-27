package Tasks.S2.notebook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Note {
    private final String topic;
    private final LocalDateTime localDateTime;
    private final String email;
    private final String message;

    public Note(String topic, LocalDateTime localDateTime, String email, String message) {
        this.topic = topic;
        this.localDateTime = localDateTime;
        this.email = email;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(topic, note.topic) &&
                Objects.equals(localDateTime, note.localDateTime) &&
                Objects.equals(email, note.email) &&
                Objects.equals(message, note.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, localDateTime, email, message);
    }

    @Override
    public String toString() {
        return "Note: " +
                "\n\btopic - " + topic +
                ", time : " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                ", email: " + email +
                ", message: " + message;
    }
}
