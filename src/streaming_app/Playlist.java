package streaming_app;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
        System.out.println("🎶 Песня \"" + song.getTitle() + "\" добавлена в плейлист \"" + name + "\".");
    }

    public void removeSong(String title) {
        boolean removed = songs.removeIf(song -> song.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("🗑️ Песня \"" + title + "\" удалена из плейлиста \"" + name + "\".");
        } else {
            System.out.println("Песня \"" + title + "\" не найдена в плейлисте \"" + name + "\".");
        }
    }

    public int getTotalDurationSeconds() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDurationSeconds();
        }
        return total;
    }

    public void displayPlaylist() {
        System.out.println("\n--- Плейлист: " + name + " ---");
        if (songs.isEmpty()) {
            System.out.println("Плейлист пуст.");
            return;
        }
        for (Song song : songs) {
            System.out.println(song);
        }

        int totalDuration = getTotalDurationSeconds();
        int minutes = totalDuration / 60;
        int seconds = totalDuration % 60;
        System.out.printf("Общая длительность: %d:%02d%n", minutes, seconds);
    }
}