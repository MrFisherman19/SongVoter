package pl.mrfisherman.musicvoter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SONGS")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    //TODO another entity Author in relation @ManyToMany
    private String author;
    //TODO another entity Album in relation @ManyToMany
    private String album;
    private Category category;
    //TODO another entity Vote in relation @OneToMany
    private int votes;

    public Song(String title, String author, String album, Category category, int votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(author, song.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "\nSong{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", album='" + album + '\'' +
                ", category=" + category +
                ", votes=" + votes +
                '}';
    }
}
