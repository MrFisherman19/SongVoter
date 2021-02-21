package pl.mrfisherman.music_voter.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.mrfisherman.music_voter.model.Category;
import pl.mrfisherman.music_voter.model.Song;

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final SongService songService;
    private final ReportService reportService;

    public DataLoader(SongService songService, ReportService reportService) {
        this.songService = songService;
        this.reportService = reportService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        songService.saveSong(
                new Song("Very bad song",
                        "Author 1",
                        "Disc 1",
                        Category.POP, 20));
        songService.saveSong(
                new Song("Very ok song",
                        "Author 2",
                        "Disc 3",
                        Category.ROCK, 50));
        songService.saveSong(
                new Song("Very good song",
                        "Author 3",
                        "Disc 3",
                        Category.METAL, 100));
        songService.saveSong(
                new Song("Very good song",
                        "Author 4",
                        "Disc 3",
                        Category.METAL, 120));
        songService.saveSong(
                new Song("Very good song",
                        "Author 5",
                        "Disc 3",
                        Category.METAL, 90));
    }
}
