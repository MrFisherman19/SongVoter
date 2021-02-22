package pl.mrfisherman.musicvoter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SongsWrapper {

    private List<Song> listOfSongs = new ArrayList<>();

    public SongsWrapper(List<Song> listOfSongs) {
        this.listOfSongs = listOfSongs;
    }

    public void add(Song song) {
        this.listOfSongs.add(song);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songs=" + listOfSongs +
                '}';
    }
}
