package streaming_app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private final String url = "jdbc:postgresql://localhost:5432/musicstreaming";
    private final String user = "postgres";
    private final String password = "ARymkhan2008";

    public void save(Song song) {
        String sql = "INSERT INTO songs (title, artist, duration_seconds) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getArtist());
            pstmt.setInt(3, song.getDurationSeconds());
            pstmt.executeUpdate();
            System.out.println("Песня сохранена в БД!");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public List<Song> findAll() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Создаем объект Song на основе данных из БД
                Song song = new Song(
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getInt("duration_seconds")
                );
                songs.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении: " + e.getMessage());
        }
        return songs;
    }

    public void updateDuration(int id, int newDuration) {
        String sql = "UPDATE songs SET duration_seconds = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newDuration);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Длительность обновлена!");
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM songs WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Песня удалена!");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении: " + e.getMessage());
        }
    }
}