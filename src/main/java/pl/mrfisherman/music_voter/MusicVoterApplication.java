package pl.mrfisherman.music_voter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.mrfisherman.music_voter.controller.ConsoleMenu;

import java.util.Scanner;

@SpringBootApplication
public class MusicVoterApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(MusicVoterApplication.class, args);
        final ConsoleMenu consoleMenu = (ConsoleMenu) run.getBean("consoleMenu");
        consoleMenu.run(args);
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
