package pl.mrfisherman.music_voter.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mrfisherman.music_voter.model.Song;
import pl.mrfisherman.music_voter.model.pojo.report.CategoryReportStats;
import pl.mrfisherman.music_voter.repository.SongRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public Song findSongByTitle(String title) {
        return songRepository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("There is no such song"));
    }

    @Override
    public void saveSong(Song song) {
        final Optional<Song> byTitleAndAuthor = songRepository.findByTitleAndAuthor(song.getTitle(), song.getAuthor());
        if (byTitleAndAuthor.isPresent()) {
            byTitleAndAuthor.get().setVotes(byTitleAndAuthor.get().getVotes() + song.getVotes());
        } else {
            songRepository.save(song);
        }
    }

    @Override
    public void saveSongs(List<Song> songs) {
        songs.forEach(this::saveSong);
    }

    @Override
    public void voteForSongByTitle(String title) {
        songRepository.voteForSongByTitle(title);
    }

    @Override
    public void clearVotesForSongByTitle(String title) {
        songRepository.clearVotesBySongTitle(title);
    }

    @Override
    public void clearVotesForEverySong() {
        songRepository.clearVotes();
    }

    @Override
    public List<Song> getSongsSortedByVotesWithLimit(int limit) {
        return songRepository.findAllOrderedByVotesWithLimit(limit);
    }

    @Override
    public List<Song> getAllSongsSortedByVotes() {
        return songRepository.findAllByOrderByVotesDesc();
    }

    @Override
    public List<CategoryReportStats> getAllWithCountingCategoryVotes() {
        return songRepository.getSongsVotesGroupedCategories().stream()
                .map(groupingProjection ->
                        new CategoryReportStats(
                                groupingProjection.getCategoryValue(),
                                groupingProjection.getVotesSum(),
                                songRepository.findAllByCategoryOrderByVotesDesc(groupingProjection.getCategoryValue())))
                .collect(Collectors.toList());
    }
}
