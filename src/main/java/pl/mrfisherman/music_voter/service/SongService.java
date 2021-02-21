package pl.mrfisherman.music_voter.service;

import pl.mrfisherman.music_voter.model.Song;
import pl.mrfisherman.music_voter.model.pojo.report.CategoryReportStats;

import java.util.List;

public interface SongService {

    Song findSongByTitle(String title);

    void saveSong(Song song);

    List<Song> getSongsSortedByVotesWithLimit(int limit);

    List<Song> getAllSongsSortedByVotes();

    List<CategoryReportStats> getAllWithCountingCategoryVotes();

    void saveSongs(List<Song> songs);

    void voteForSongByTitle(String nextLineFromUser);

    void clearVotesForSongByTitle(String nextLineFromUser);

    void clearVotesForEverySong();

}
