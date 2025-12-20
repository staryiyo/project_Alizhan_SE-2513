import streaming_app.Playlist;
import streaming_app.Song;

public class Main{
    public static void main(String[] args){
        Song liar = new Song("Liar", "Imagine Dragon", 130);

        Song redroom = new Song("Red room", "21 Savage", 110);

        Song manoftheyeah = new Song("Man Of The Yeah", "Bawo", 174);

        Song blindinglights = new Song("Blinding Lights", "The weekend", 200);

        Playlist List1 = new Playlist("Hip Hop");
        List1.addSong(redroom);
        List1.addSong(manoftheyeah);

        Playlist List2 = new Playlist("Pop");
        List2.addSong(liar);
        List2.addSong(blindinglights);


    }
};