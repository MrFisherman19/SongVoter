package pl.mrfisherman.musicvoter.repository;

import pl.mrfisherman.musicvoter.model.Category;

public interface CategoryGroupingProjection {

    Category getCategoryValue();
    int getVotesSum();

}
