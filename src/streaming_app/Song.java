package streaming_app;

public class Song {
    private int songId;
    private String title;
    private String artist;
    private int durationSeconds;

    public Song(String title, String artist, int durationSeconds) {
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public Song(int songId, String title, String artist, int durationSeconds) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public int getSongId() { return songId; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDurationSeconds() { return durationSeconds; }

    @Override
    public String toString() {
        return "ID " + songId + ": \"" + title + "\" - " + artist + " (" + durationSeconds + " сек.)";
    }
}