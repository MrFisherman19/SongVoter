package pl.mrfisherman.music_voter.service.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mrfisherman.music_voter.model.Song;
import pl.mrfisherman.music_voter.model.pojo.report.CategoriesReport;
import pl.mrfisherman.music_voter.model.pojo.report.CategoryReportStats;
import pl.mrfisherman.music_voter.model.pojo.report.SongReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class SongFileManager {

    private final CsvParser CsvParser;
    private final XmlParser XmlParser;
    private static final String XML_FILE_EXTENSION = "xml";
    private static final String CSV_FILE_EXTENSION = "csv";
    private static final String HOME = System.getProperty("user.home");

    public List<Song> getSongsFromFiles(String[] args) {
        Map<String, List<String>> pathsByFileExtension = groupValidPathsByExtension(args);
        final List<Song> xmlSongs = extractSongsFromFile(pathsByFileExtension, XML_FILE_EXTENSION, XmlParser);
        final List<Song> csvSongs = extractSongsFromFile(pathsByFileExtension, CSV_FILE_EXTENSION, CsvParser);
        return Stream.concat(xmlSongs.stream(), csvSongs.stream()).collect(Collectors.toList());
    }

    private Map<String, List<String>> groupValidPathsByExtension(String[] args) {
        return Arrays.stream(args)
                .filter(this::isValid)
                .collect(groupingBy(this::getFileFormat));
    }

    private List<Song> extractSongsFromFile(Map<String, List<String>> pathsByFileExtension,
                                            String fileExtension, SongParser songParser) {
        if (pathsByFileExtension.containsKey(fileExtension)) {
            return pathsByFileExtension.get(fileExtension).stream()
                    .map(Paths::get)
                    .map(songParser::parseSongsFromPath)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
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

    public Path saveReportToFile(Object content, String name, String extension) {
        Path path = null;
        try {
            if (extension.equalsIgnoreCase(XML_FILE_EXTENSION)) {
                path = createPathInHomeDir(name, XML_FILE_EXTENSION);
                final Path file = Files.createFile(path);
                Files.writeString(file, XmlParser.parse(content));
            } else if (extension.equalsIgnoreCase(CSV_FILE_EXTENSION)) {
                path = createPathInHomeDir(name, CSV_FILE_EXTENSION);
                final Path file = Files.createFile(path);
                Files.writeString(file, getCsvStringBasedOnReportType(content));
            } else {
                throw new IllegalArgumentException("Invalid file extension!");
            }
        } catch (IOException e) {
            //TODO
        }
        return path;
    }

    private Path createPathInHomeDir(String name, String xmlFileExtension) {
        return Paths.get(HOME + "/" + name + "." + xmlFileExtension);
    }

    private String getCsvStringBasedOnReportType(Object content) {
        final StringBuilder stringBuilder = new StringBuilder();
        if (content instanceof SongReport) {
            stringBuilder.append("Title,Author,Album,Category,Votes").append("\n");
            for (Song song : ((SongReport) content).getSongs()) {
                stringBuilder.append(song.getTitle()).append(",")
                        .append(song.getAuthor()).append(",")
                        .append(song.getAlbum()).append(",")
                        .append(song.getCategory()).append(",")
                        .append(song.getVotes()).append("\n");
            }
        } else if (content instanceof CategoriesReport) {
            stringBuilder.append("Category name,Sum of votes,Songs in category").append("\n");
            for (CategoryReportStats stats : ((CategoriesReport) content).getCategoriesStats()) {
                stringBuilder.append(stats.getCategoryName()).append(",")
                        .append(stats.getSummaryVotes()).append(",")
                        .append(stats.getSongsInCategory().stream().map(this::cutSongInfo).collect(Collectors.joining("&")))
                        .append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private String cutSongInfo(Song x) {
        return x.getTitle() + "|" + x.getAuthor() + "|" + x.getVotes();
    }
}
