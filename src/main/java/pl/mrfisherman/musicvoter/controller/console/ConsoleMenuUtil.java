package pl.mrfisherman.musicvoter.controller.console;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.mrfisherman.musicvoter.model.Category;
import pl.mrfisherman.musicvoter.model.Song;
import pl.mrfisherman.musicvoter.model.pojo.report.ReportType;
import pl.mrfisherman.musicvoter.service.ReportExtension;
import pl.mrfisherman.musicvoter.service.ReportService;
import pl.mrfisherman.musicvoter.service.SongService;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleMenuUtil implements InitializingBean {

    private static ConsoleMenuUtil consoleMenuUtil;
    private final ReportService reportService;
    private final SongService songService;
    private final Scanner scanner;
    private static final String DEFAULT_REPORT_EXTENSION = "xml";

    @Override
    public void afterPropertiesSet() throws Exception {
        consoleMenuUtil = this;
    }

    public static ConsoleMenuUtil getInstance() {
        return consoleMenuUtil;
    }

    public static void voteForSong() {
        try {
            System.out.println("Provide title of song that you want to vote on:");
            final String title = getNextLineFromUser();
            getInstance().songService.voteForSongByTitle(title);
            System.out.println("Songs votes after vote: " + getInstance().songService.findSongByTitle(title).getVotes());
        } catch (EntityNotFoundException e) {
            System.out.println("There is no such song with given title!");
        }
    }

    public static void addSong() {
        System.out.println("Please provide needed song details");
        System.out.println("Provide title:");
        String title = getInstance().scanner.nextLine();
        System.out.println("Provide author:");
        String author = getInstance().scanner.nextLine();
        System.out.println("Provide album:");
        String album = getInstance().scanner.nextLine();
        System.out.println("Provide category name | Allowed Categories: [" + Arrays.toString(Category.values()) + "]");
        Category category;
        try {
            category = Category.valueOf(getInstance().scanner.nextLine().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            System.out.println("There is no such category in system!");
            category = Category.UNKNOWN;
        }
        System.out.println("Provide initial votes number:");
        int initialVotes = getInstance().scanner.nextInt();
        getInstance().songService.saveSong(new Song(title, author, album, category, initialVotes));
    }

    public static void clearVotesByTitle() {
        System.out.println("Provide title of song for which you want to clear votes:");
        getInstance().songService.clearVotesForSongByTitle(getNextLineFromUser());
    }

    public static void clearVotesForAll() {
        System.out.println("Clearing votes for all songs...");
        getInstance().songService.clearVotesForEverySong();
    }

    public static void generateAndPrintTop3Report() {
        printReportAndSaveToFile(ReportType.TOP_3_SONGS);
    }

    public static void generateAndPrintTop10Report() {
        printReportAndSaveToFile(ReportType.TOP_10_SONGS);
    }

    public static void generateAndPrintReportForAll() {
        printReportAndSaveToFile(ReportType.ALL_SONGS);
    }

    public static void generateAndPrintCategoriesReport() {
        printReportAndSaveToFile(ReportType.CATEGORIES);
    }

    private static Path saveReport(ReportType categories) {
        String extension;
        Path path;
        try {
            extension = getInstance().scanner.nextLine().toUpperCase(Locale.ROOT);
            path = getInstance().reportService.generateReportToFile(categories, ReportExtension.valueOf(extension));
        } catch (IllegalArgumentException e) {
            extension = DEFAULT_REPORT_EXTENSION.toUpperCase(Locale.ROOT);
            System.out.println("There is no given file extension! File will be saved with " + extension + " extension!");
            path = getInstance().reportService.generateReportToFile(categories, ReportExtension.valueOf(extension));
        }
        return path;
    }

    private static void printReportAndSaveToFile(ReportType categories) {
        System.out.println("Please provide format of new report: " + Arrays.toString(ReportExtension.values()));
        System.out.println("Report path: " + saveReport(categories));
        System.out.println(getInstance().reportService.generateReport(categories));
    }

    private static String getNextLineFromUser() {
        return getInstance().scanner.nextLine();
    }
}
