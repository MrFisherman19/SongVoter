package pl.mrfisherman.musicvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mrfisherman.musicvoter.model.Category;
import pl.mrfisherman.musicvoter.model.Song;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByCategoryOrderByVotesDesc(Category category);

    @Query(nativeQuery = true, value = "SELECT * FROM Songs s ORDER BY s.votes DESC LIMIT :limit")
    List<Song> findAllOrderedByVotesWithLimit(int limit);

    @Query(nativeQuery = true, value = "SELECT s.category AS categoryValue, sum(s.votes) as votesSum FROM Songs s GROUP BY s.category ORDER BY votesSum DESC")
    List<CategoryGroupingProjection> getSongsVotesGroupedCategories();

    Optional<Song> findByTitleAndAuthor(String title, String author);

    Optional<Song> findByTitle(String title);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Songs SET votes = 0")
    void clearVotes();

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Songs SET votes = 0 WHERE title = :title")
    void clearVotesBySongTitle(String title);

    List<Song> findAllByOrderByVotesDesc();

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE Songs SET votes = votes + 1 WHERE title = :title")
    void voteForSongByTitle(String title);


}
