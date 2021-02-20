package pl.mrfisherman.music_voter.service;

import pl.mrfisherman.music_voter.model.Song;
import pl.mrfisherman.music_voter.model.pojo.report.CategoryReportStats;

import java.util.List;

public interface SongService {

    void addSong(Song song);

    List<Song> getSongsSortedByVotesWithLimit(int limit);

    List<Song> getAllSongsSortedByVotes();

    List<CategoryReportStats> getAllWithCountingCategoryVotes();
}
