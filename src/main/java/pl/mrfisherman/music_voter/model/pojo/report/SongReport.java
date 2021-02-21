package pl.mrfisherman.music_voter.model.pojo.report;

import lombok.Getter;
import pl.mrfisherman.music_voter.model.Song;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SongReport extends Report {

    private final List<Song> songs;

    public SongReport(String title, List<Song> songs) {
        super(title);
        this.songs = new ArrayList<>(songs);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "SongReport{" + "songs=" + songs + '}' + "\n";
    }
}
