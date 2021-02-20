package pl.mrfisherman.music_voter.service.file;

import org.springframework.stereotype.Component;
import pl.mrfisherman.music_voter.model.Song;

import java.nio.file.Path;
import java.util.List;

@Component
public class XMLSongParser implements SongParser {

    @Override
    public List<Song> saveSongsFromPath(Path path) {
        return null;
    }
}
