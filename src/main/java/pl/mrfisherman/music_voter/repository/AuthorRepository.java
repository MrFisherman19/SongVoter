package pl.mrfisherman.music_voter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mrfisherman.music_voter.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
