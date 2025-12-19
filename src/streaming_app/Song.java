package streaming_app;

public class Song {
    // Приватные атрибуты для обеспечения инкапсуляции
    private int songId;
    private String title;
    private String artist;
    private int durationSeconds; // Длительность в секундах

    // Статический счетчик для автоматической генерации ID
    private static int nextId = 1;

    // Конструктор
    public Song(String title, String artist, int durationSeconds) {
        this.songId = nextId++; // Присваиваем ID и увеличиваем счетчик
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    // --- Геттеры (Чтение) ---
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

    // --- Сеттеры (Изменение - только там, где это логично) ---
    public void setDurationSeconds(int durationSeconds) {
        if (durationSeconds > 0) {
            this.durationSeconds = durationSeconds;
        } else {
            System.out.println("Ошибка: Длительность должна быть положительной.");
        }
    }

    // Метод для форматирования длительности (для удобства вывода)
    public String getDurationFormatted() {
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;
        return String.format("%d:%02d", minutes, seconds); // Например, 3:25
    }

    // Переопределение метода toString для удобного вывода объекта
    @Override
    public String toString() {
        return "ID " + songId + ": \"" + title + "\" - " + artist +
                " (" + getDurationFormatted() + ")";
    }
}