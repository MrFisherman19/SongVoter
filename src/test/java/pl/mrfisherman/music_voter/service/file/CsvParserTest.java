package pl.mrfisherman.music_voter.service.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class CsvParserTest {

    @Autowired
    SongParser CSVSongParser;


    @ParameterizedTest
    @CsvFileSource(resources = "/testSongs1.csv", numLinesToSkip = 1)
    void shouldParseSongsFromPath() {

    }
}