package streaming_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    // Вспомогательный класс для управления библиотекой и плейлистами
    private static class MusicStreamer {
        private List<Song> library = new ArrayList<>();
        private List<Playlist> playlists = new ArrayList<>();

        public void addSongToLibrary(Song song) {
            library.add(song);
        }

        public Playlist createPlaylist(String name) {
            Playlist newPlaylist = new Playlist(name);
            playlists.add(newPlaylist);
            return newPlaylist;
        }

        // Поиск плейлиста по имени
        public Playlist getPlaylistByName(String name) {
            for (Playlist p : playlists) {
                if (p.getName().equalsIgnoreCase(name)) {
                    return p;
                }
            }
            return null;
        }

        public void displayLibrary() {
            System.out.println("\n--- Общая Библиотека Песен (Всего: " + library.size() + ") ---");
            if (library.isEmpty()) {
                System.out.println("Библиотека пуста.");
                return;
            }
            for (Song song : library) {
                System.out.println(song);
            }
        }

        public void displayAllPlaylists() {
            System.out.println("\n--- Список Плейлистов ---");
            if (playlists.isEmpty()) {
                System.out.println("Плейлистов пока нет.");
                return;
            }
            for (int i = 0; i < playlists.size(); i++) {
                Playlist p = playlists.get(i);
                System.out.println((i + 1) + ". " + p.getName() +
                        " (" + p.getSongs().size() + " песен)");
            }
        }

        public void playSong(Song song) {
            if (song != null) {
                System.out.println("\n--------------------------------------------------");
                System.out.println("   ▶️ ВОСПРОИЗВЕДЕНИЕ: " + song.getTitle());
                System.out.println("   Исполнитель: " + song.getArtist());
                System.out.println("   Длительность: " + song.getDurationFormatted());
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("Песня не найдена.");
            }
        }
    } // Конец класса MusicStreamer

    // --- МЕТОД MAIN (Точка входа) ---
    public static void main(String[] args) {
        MusicStreamer streamer = new MusicStreamer();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Инициализация библиотеки тестовыми песнями
        streamer.addSongToLibrary(new Song("Starlight", "Muse", 300));
        streamer.addSongToLibrary(new Song("Bohemian Rhapsody", "Queen", 354));
        streamer.addSongToLibrary(new Song("Blinding Lights", "The Weeknd", 201));

        // Основной цикл меню
        while (choice != 0) {
            System.out.println("\n========== МЕНЮ СТРИМЕРА ==========");
            System.out.println("1. Показать всю библиотеку");
            System.out.println("2. Создать новый плейлист");
            System.out.println("3. Добавить песню в плейлист");
            System.out.println("4. Показать все плейлисты и их содержимое");
            System.out.println("5. Имитировать воспроизведение песни (по ID)");
            System.out.println("0. Выход");
            System.out.print(">>> Выберите действие: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера

                switch (choice) {
                    case 1:
                        streamer.displayLibrary();
                        break;
                    case 2:
                        System.out.print("Введите название нового плейлиста: ");
                        String pName = scanner.nextLine();
                        streamer.createPlaylist(pName);
                        break;
                    case 3:
                        addSongToPlaylist(streamer, scanner); // Вызываем вспомогательный метод
                        break;
                    case 4:
                        streamer.displayAllPlaylists();
                        for (Playlist p : streamer.playlists) {
                            p.displayPlaylist();
                        }
                        break;
                    case 5:
                        System.out.print("Введите ID песни для воспроизведения: ");
                        int songId = scanner.nextInt();
                        Song songToPlay = findSongById(streamer.library, songId);
                        streamer.playSong(songToPlay);
                        break;
                    case 0:
                        System.out.println("Программа завершена. Спасибо за использование!");
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите число.");
                scanner.nextLine(); // Очистка неверного ввода
                choice = -1; // Сброс
            }
        }
        scanner.close();
    } // Конец main

    // Вспомогательный метод для логики "Добавить песню в плейлист"
    private static void addSongToPlaylist(MusicStreamer streamer, Scanner scanner) {
        streamer.displayLibrary();
        System.out.print("Введите ID песни, которую хотите добавить: ");
        int songId = scanner.nextInt();
        scanner.nextLine(); // Очистка

        Song songToAdd = findSongById(streamer.library, songId);
        if (songToAdd == null) {
            System.out.println("❌ Песня с таким ID не найдена.");
            return;
        }

        streamer.displayAllPlaylists();
        System.out.print("Введите название плейлиста, куда добавить песню: ");
        String playlistName = scanner.nextLine();

        Playlist targetPlaylist = streamer.getPlaylistByName(playlistName);
        if (targetPlaylist != null) {
            targetPlaylist.addSong(songToAdd);
        } else {
            System.out.println("❌ Плейлист с названием \"" + playlistName + "\" не найден.");
        }
    }

    // Вспомогательный метод для поиска песни по ID
    private static Song findSongById(List<Song> library, int id) {
        for (Song song : library) {
            if (song.getSongId() == id) {
                return song;
            }
        }
        return null;
    }
}