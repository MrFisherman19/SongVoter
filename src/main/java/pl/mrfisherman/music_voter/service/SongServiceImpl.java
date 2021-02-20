package pl.mrfisherman.music_voter.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mrfisherman.music_voter.model.Song;
import pl.mrfisherman.music_voter.model.pojo.report.CategoryReportStats;
import pl.mrfisherman.music_voter.repository.SongRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Transactional
    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public List<Song> getSongsSortedByVotesWithLimit(int limit) {
        return songRepository.findAllOrderedByVotesWithLimit(limit);
    }

    @Override
    public List<Song> getAllSongsSortedByVotes() {
        return null;
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
