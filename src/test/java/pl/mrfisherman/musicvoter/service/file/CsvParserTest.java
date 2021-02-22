package pl.mrfisherman.musicvoter.service.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mrfisherman.musicvoter.model.Category;
import pl.mrfisherman.musicvoter.model.Song;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class CsvParserTest {

    CsvParser csvParser;

    @BeforeEach
    public void init() {
        csvParser = new CsvParser();
    }

    /**
     * Songs1.csv contains 3 songs objects
     */
    @Test
    void shouldParseSongsFromPath() {
        //given
        Path path = Paths.get("src/test/resources/Songs1.csv");
        //when
        List<Song> songs = csvParser.parseSongsFromPath(path);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(3);
        assertThat(songs).extracting("title")
                .contains("Living in a Ghost Town",
                        "You Should Be Sad",
                        "Imported");
    }

    /**
     * WrongCategorySong contains category with not exist in program
     */
    @Test
    void shouldReturnUnknownCategoryForNonExistingCategory() {
        //given
        Path path = Paths.get("src/test/resources/WrongCategorySong.csv");
        //when
        List<Song> songs = csvParser.parseSongsFromPath(path);
        //then
        assertThat(songs).isNotEmpty();
        assertThat(songs).hasSize(1);
        assertThat(songs).extracting("category")
                .containsOnly(Category.UNKNOWN);
    }


}