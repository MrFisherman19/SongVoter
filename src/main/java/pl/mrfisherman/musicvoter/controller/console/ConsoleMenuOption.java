package pl.mrfisherman.musicvoter.controller.console;

import lombok.Getter;

@Getter
public enum ConsoleMenuOption {

    VOTE_FOR_SONG(1, "Vote for song", ConsoleMenuUtil::voteForSong),
    ADD_SONG(2, "Add new song", ConsoleMenuUtil::addSong),
    CLEAR_VOTES_BY_TITLE(3, "Clear votes for song by title", ConsoleMenuUtil::clearVotesByTitle),
    CLEAR_VOTES_FOR_ALL(4, "Clear votes for all songs", ConsoleMenuUtil::clearVotesForAll),
    GENERATE_AND_PRINT_TOP_3(5, "Generate and print top 3 songs report", ConsoleMenuUtil::generateAndPrintTop3Report),
    GENERATE_AND_PRINT_TOP_10(6, "Generate and print top 10 songs report", ConsoleMenuUtil::generateAndPrintTop10Report),
    GENERATE_AND_PRINT_FOR_ALL(7, "Generate and print all songs report", ConsoleMenuUtil::generateAndPrintReportForAll),
    GENERATE_AND_PRINT_CATEGORIES(8, "Generate and print category report", ConsoleMenuUtil::generateAndPrintCategoriesReport),
    EXIT(0, "Exit program", () -> System.exit(0));

    private final int optionNumber;
    private final String label;
    private final Runnable runnable;

    ConsoleMenuOption(int optionNumber, String label, Runnable runnable) {
        this.optionNumber = optionNumber;
        this.label = label;
        this.runnable = runnable;
    }
}
