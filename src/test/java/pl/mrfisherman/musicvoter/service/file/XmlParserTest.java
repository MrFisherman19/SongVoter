package pl.mrfisherman.musicvoter.service.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.mrfisherman.musicvoter.model.Category;
import pl.mrfisherman.musicvoter.model.Song;
import pl.mrfisherman.musicvoter.model.pojo.report.Report;
import pl.mrfisherman.musicvoter.model.pojo.report.SongReport;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class XmlParserTest {

    XmlParser xmlParser;

    @BeforeEach
    public void init() {
        xmlParser = new XmlParser();
    }

    @Test
    void shouldParseSongsFromPath() {
        //given
        Path path = Paths.get("src/test/resources/Songs2.xml");
        //when
        List<Song> songs = xmlParser.parseSongsFromPath(path);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(3);
        assertThat(songs).extracting("title")
                .contains("Living in a Ghost Town",
                        "You Should Be Sad",
                        "Imported");
    }

    @Test
    void shouldConvertSongReportToXmlDocument() {
        //given
        List<Song> ranking = songList().collect(Collectors.toList());
        Report report = new SongReport("Top 3 report", ranking);
        //when
        final String parse = xmlParser.parse(report);
        //then
        assertThat(parse).isNotNull();
        assertThat(parse).isNotEmpty();
        assertThat(parse).containsSubsequence("Living in a Ghost Town");
        assertThat(parse).containsSubsequence("You Should Be Sad");
        assertThat(parse).containsSubsequence("<votes>10</votes>");
    }

    private static Stream<Song> songList() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Living in a Ghost Town","The Rolling Stones","Honk",Category.ROCK,10));
        songs.add(new Song("You Should Be Sad","Halsey","Manic",Category.ALTERNATIVE,2));
        songs.add(new Song("Imported","Jessie Reyez","Before Love Came to Kill Us",Category.RB,6));
        return songs.stream();
    }

}