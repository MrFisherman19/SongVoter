package pl.mrfisherman.music_voter.repository;

import pl.mrfisherman.music_voter.model.Category;

public interface CategoryGroupingProjection {

    Category getCategoryValue();
    int getVotesSum();

}
