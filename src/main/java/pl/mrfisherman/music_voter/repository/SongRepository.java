package pl.mrfisherman.music_voter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mrfisherman.music_voter.model.Category;
import pl.mrfisherman.music_voter.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByCategoryOrderByVotesDesc(Category category);

    @Query(nativeQuery = true, value = "SELECT * FROM Songs s ORDER BY s.votes DESC LIMIT :limit")
    List<Song> findAllOrderedByVotesWithLimit(int limit);

    @Query(nativeQuery = true, value = "SELECT s.category AS categoryValue, sum(s.votes) as votesSum FROM Songs s GROUP BY s.category")
    List<CategoryGroupingProjection> getSongsVotesGroupedCategories();
}
