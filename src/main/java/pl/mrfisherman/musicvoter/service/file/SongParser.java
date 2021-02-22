package pl.mrfisherman.musicvoter.service.file;

import pl.mrfisherman.musicvoter.model.Song;

import java.nio.file.Path;
import java.util.List;

public interface SongParser {

    /**
     * Should parse file from given path to the List of Songs
     * @param path to file
     * @return List of Songs extracted from file
     */
    List<Song> parseSongsFromPath(Path path);
}
