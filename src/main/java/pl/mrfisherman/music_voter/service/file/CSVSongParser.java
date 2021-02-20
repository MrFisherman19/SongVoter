package pl.mrfisherman.music_voter.service.file;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import pl.mrfisherman.music_voter.model.Category;
import pl.mrfisherman.music_voter.model.Song;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class CSVSongParser implements SongParser {

    @Override
    public List<Song> saveSongsFromPath(Path path) {
        final List<Song> songs = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            csvParser.getRecords().forEach(record -> songs.add(extractSong(record)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    private Song extractSong(CSVRecord record) {
        String title = record.get("Title");
        String author = record.get("Author");
        String album = record.get("Album");
        Category category;
        try {
            category = Category.valueOf(record.get("Category")
                    .toUpperCase(Locale.ROOT)
                    .replaceAll("[^a-zA-Z0-9]", ""));
        } catch (IllegalArgumentException e) {
            category = Category.UNKNOWN;
        }
        int votes = Integer.parseInt(record.get("Votes"));
        return new Song(title, author, album, category, votes);
    }
}
