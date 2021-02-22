package pl.mrfisherman.musicvoter.service;

import pl.mrfisherman.musicvoter.model.Song;
import pl.mrfisherman.musicvoter.model.pojo.report.CategoryReportStats;

import java.util.List;

public interface SongService {

    /**
     * Method search for Song by given title.
     * @param title
     * @return Song by title
     */
    Song findSongByTitle(String title);

    /**
     * Method saves song to database.
     * @param song
     */
    void saveSong(Song song);

    /**
     * Method returns song list sorted by votes number,
     * it is limited by limit param. For example
     * when limit = 3, method will return top three songs with
     * the highest number of votes
     * @param limit
     * @return List of Songs
     */
    List<Song> getSongsSortedByVotesWithLimit(int limit);

    /**
     * Method returns song list sorted by votes number.
     * @return List of Songs
     */
    List<Song> getAllSongsSortedByVotes();

    /**
     * Method returns list of CategoryReportStats.
     * CategoryReportStats group songs by category and
     * sum number of votes within the categories
     * @return List of CategoryReportStats
     */
    List<CategoryReportStats> getAllWithCountingCategoryVotes();

    /**
     * Method saves multiple Song objects.
     * @param songs
     */
    void saveSongs(List<Song> songs);

    /**
     * Method increases votes number by 1.
     * @param nextLineFromUser
     */
    void voteForSongByTitle(String nextLineFromUser);

    /**
     * Method clears votes number for given song title.
     * @param nextLineFromUser
     */
    void clearVotesForSongByTitle(String nextLineFromUser);

    /**
     * Method clears number of votes for every song.
     */
    void clearVotesForEverySong();

}
