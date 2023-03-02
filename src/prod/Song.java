package prod;

import java.time.Duration;
import java.util.Objects;

public class Song {

    private final Duration duration;
    private final String name;
    private final String author;

    public Song(Duration duration, String name, String author) {
        this.author = author;
        this.name = name;
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        long minutes = duration.toMinutes();
        long seconds = duration.minus(Duration.ofMinutes(minutes)).toSeconds();
        return "Название песни: " + name + "\nНазвание исполнителя: " + author
                + "\nДлительность песни\nМинут: " + duration.toMinutes() + " секунд: "
                + seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (getDuration() != null ? !getDuration().equals(song.getDuration()) : song.getDuration() != null)
            return false;
        if (!Objects.equals(name, song.name)) return false;
        return Objects.equals(author, song.author);
    }

    @Override
    public int hashCode() {
        int result = getDuration() != null ? getDuration().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
