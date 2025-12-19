package streaming_app;

public class Song {

    private int songId;
    private String title;
    private String artist;
    private int durationSeconds;

    private static int nextId = 1;

    public Song(String title, String artist, int durationSeconds) {
        this.songId = nextId++;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public int getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        if (durationSeconds > 0) {
            this.durationSeconds = durationSeconds;
        } else {
            System.out.println("Ошибка: Длительность должна быть положительной.");
        }
    }

    public String getDurationFormatted() {
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;
        return String.format("%d:%02d", minutes, seconds); // Например, 3:25
    }

    @Override
    public String toString() {
        return "ID " + songId + ": \"" + title + "\" - " + artist +
                " (" + getDurationFormatted() + ")";
    }
}