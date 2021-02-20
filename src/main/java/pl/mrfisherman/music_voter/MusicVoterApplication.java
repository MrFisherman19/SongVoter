package pl.mrfisherman.music_voter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.mrfisherman.music_voter.controller.ConsoleMenuFacade;
import pl.mrfisherman.music_voter.service.file.FileManager;

@SpringBootApplication
public class MusicVoterApplication implements CommandLineRunner {

    private final ConsoleMenuFacade consoleMenuFacade;
    private final FileManager fileManager;

    public MusicVoterApplication(ConsoleMenuFacade consoleMenuFacade, FileManager fileManager) {
        this.consoleMenuFacade = consoleMenuFacade;
        this.fileManager = fileManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicVoterApplication.class, args);
    }

    @Override
    public void run(String... args) {
        consoleMenuFacade.printLoadedFilesOnConsole(fileManager.extractSongsFilesAndSave(args).toString());


    }

}
