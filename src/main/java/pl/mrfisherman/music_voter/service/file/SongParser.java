package pl.mrfisherman.music_voter.service.file;

import pl.mrfisherman.music_voter.model.Song;

import java.nio.file.Path;
import java.util.List;

public interface SongParser {

    List<Song> parseSongsFromPath(Path path);
}
