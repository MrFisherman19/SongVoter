package pl.mrfisherman.music_voter.service.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class FileManager {

    private final SongParser CSVSongParser;
    private final SongParser XMLSongParser;
    private static final String XML_FILE_EXTENSION = "xml";
    private static final String CSV_FILE_EXTENSION = "csv";

    public Collection<List<String>> extractSongsFilesAndSave(String[] args) {
        Map<String, List<String>> pathsByFileExtension = groupValidPathsByExtension(args);
        saveSongsFromFiles(pathsByFileExtension, XML_FILE_EXTENSION, XMLSongParser);
        saveSongsFromFiles(pathsByFileExtension, CSV_FILE_EXTENSION, CSVSongParser);
        return pathsByFileExtension.values();
    }

    private Map<String, List<String>> groupValidPathsByExtension(String[] args) {
        return Arrays.stream(args)
                .filter(this::isValid)
                .collect(groupingBy(this::getFileFormat));
    }

    private void saveSongsFromFiles(Map<String, List<String>> pathsByFileExtension,
                                    String fileExtension, SongParser songParser) {
        if (pathsByFileExtension.containsKey(fileExtension)) {
            pathsByFileExtension.get(fileExtension).stream()
                    .map(Paths::get).forEach(songParser::saveSongsFromPath);
        }
    }

    private String getFileFormat(String pathString) {
        return pathString.substring(pathString.lastIndexOf(".") + 1).toLowerCase(Locale.ROOT);
    }

    private boolean isValid(String pathString) {
        Path path = Paths.get(pathString);
        boolean exists = Files.exists(path);
        boolean isReadable = Files.isReadable(path);
        boolean isNotDirectory = !Files.isDirectory(path);
        return exists && isReadable && isNotDirectory;
    }
}
