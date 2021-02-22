package pl.mrfisherman.musicvoter.model.pojo.report;

import lombok.Getter;
import pl.mrfisherman.musicvoter.model.Category;
import pl.mrfisherman.musicvoter.model.Song;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryReportStats {

    private final String categoryName;
    private final int summaryVotes;
    private final List<Song> songsInCategory;

    public CategoryReportStats(Category categoryValue, int votesCount, List<Song> allByCategory) {
        this.categoryName = categoryValue.name();
        this.summaryVotes = votesCount;
        this.songsInCategory = new ArrayList<>(allByCategory);
    }
}
