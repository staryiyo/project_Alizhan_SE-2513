import streaming_app.Song;
import streaming_app.SongRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SongRepository repository = new SongRepository();

        System.out.println("--- 1. Добавление данных (Write) ---");
        Song newSong = new Song("Liar", "Imagine Dragons", 130);
        repository.save(newSong);

        System.out.println("\n--- 2. Чтение данных (Read) ---");
        List<Song> songs = repository.findAll();
        for (Song s : songs) {
            System.out.println("Из базы получено: " + s);
        }

        if (!songs.isEmpty()) {
            int firstId = songs.get(0).getSongId();

            System.out.println("\n--- 3. Обновление (Update) ---");
            repository.updateDuration(firstId, 200);

            System.out.println("\n--- 4. Удаление (Delete) ---");
            // repository.delete(firstId); // Раскомментируй, если хочешь проверить удаление
            // System.out.println("Песня с ID " + firstId + " удалена.");
        }
    }
}