package pl.mrfisherman.music_voter.service;

import pl.mrfisherman.music_voter.model.Song;

public interface Votable {

    void voteForSongByName(String name);

    void clearVotesForSongByName(String name);

    void clearVotesForEverySong();
}
