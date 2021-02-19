package pl.mrfisherman.music_voter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "discs", fetch = FetchType.EAGER)
    private List<Song> songs;
}
