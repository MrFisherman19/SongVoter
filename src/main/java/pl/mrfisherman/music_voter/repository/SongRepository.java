package pl.mrfisherman.music_voter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mrfisherman.music_voter.model.Category;
import pl.mrfisherman.music_voter.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByCategory(Category category);
}
