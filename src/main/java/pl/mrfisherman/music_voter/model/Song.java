package pl.mrfisherman.music_voter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "song_authors",
            joinColumns = {@JoinColumn(name = "song_id",
                    insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "author_id",
                    insertable = false, updatable = false)})
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "song_discs",
            joinColumns = {@JoinColumn(name = "song_id",
                    insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "disc_id",
                    insertable = false, updatable = false)})
    private Set<Disc> discs;

    private Category category;

    //in bigger application this should be also another entity in relation @OneToMany
    private int votes;
}
