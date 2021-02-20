package pl.mrfisherman.music_voter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mrfisherman.music_voter.model.pojo.report.ReportType;
import pl.mrfisherman.music_voter.model.Song;
import pl.mrfisherman.music_voter.service.ReportService;
import pl.mrfisherman.music_voter.service.SongService;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsoleMenuFacade{

    private final SongService songService;
    private final ReportService reportService;

    public void addSong(Song song) {
        songService.addSong(song);
    }

    public void generateReport(ReportType reportType) {
        reportService.generateReportToFile(reportType);
    }

    public void printReport(ReportType reportType) {
        reportService.generateReport(reportType);
        songService.getSongsSortedByVotesWithLimit(3).forEach(System.out::println);
    }

    public void voteForSongByName(String name) {

    }

    public void clearVotesForSongByName(String name) {

    }

    public void clearVotesForEverySong() {

    }

    public void run() {
        while (true) {

        }
    }

    public void printLoadedFilesOnConsole(String label) {
        System.out.println("Songs loaded from paths: " + label);
    }
}
