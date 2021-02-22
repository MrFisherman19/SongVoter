package pl.mrfisherman.musicvoter.controller.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mrfisherman.musicvoter.service.SongService;
import pl.mrfisherman.musicvoter.service.file.SongFileManager;

import java.util.Arrays;
import java.util.Scanner;

@Component
public final class ConsoleMenu implements CommandLineRunner {

    private final SongService songService;
    private final SongFileManager songFileManager;
    private final Scanner scanner;

    public ConsoleMenu(SongService songService, SongFileManager songFileManager, Scanner scanner) {
        this.songService = songService;
        this.songFileManager = songFileManager;
        this.scanner = scanner;
    }

    public void run(String... args) {
        saveSongsFromGivenArguments(args);
        while(true) {
            printMenuOptions();
            final int numberChoice = getNumericOptionFromUser();
            final var option = Arrays.stream(ConsoleMenuOption.values())
                    .filter(x -> x.getOptionNumber() == numberChoice)
                    .findFirst();
            option.ifPresentOrElse(x -> x.getRunnable().run(), () -> System.out.println("There is no such option!"));
        }
    }

    private void saveSongsFromGivenArguments(String[] args) {
        if (args != null) {
            System.out.println(Arrays.toString(args));
            songService.saveSongs(songFileManager.getSongsFromFiles(args));
        }
    }

    private void printMenuOptions() {
        Arrays.stream(ConsoleMenuOption.values())
                .map(option -> option.getOptionNumber() + " - " + option.getLabel())
                .forEach(System.out::println);
    }

    private int getNumericOptionFromUser() {
        final int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

}
