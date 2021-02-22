package pl.mrfisherman.musicvoter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.mrfisherman.musicvoter.controller.console.ConsoleMenu;

import java.util.Scanner;

@SpringBootApplication
public class MusicVoterApplication {

    /**
     * Main method of a program.
     * Take xml and csv files as arguments, run the context,
     * and pass the arguments to the ConsoleMenu
     *
     * @param args should be xml and csv paths
     */
    public static void main(String[] args) {
        final ConfigurableApplicationContext run =
                SpringApplication.run(MusicVoterApplication.class, args);
        final ConsoleMenu consoleMenu = (ConsoleMenu) run.getBean("consoleMenu");
        consoleMenu.run(args);
    }

    /**
     * Method that binds the java.util.Scanner to the Spring Context.
     *
     * @return Scanner bean
     */
    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
