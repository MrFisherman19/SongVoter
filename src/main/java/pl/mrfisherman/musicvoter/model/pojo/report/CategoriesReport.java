package pl.mrfisherman.musicvoter.model.pojo.report;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriesReport extends Report {

    private final List<CategoryReportStats> categoriesStats;

    public CategoriesReport(String title, List<CategoryReportStats> categoriesStats) {
        super(title);
        this.categoriesStats = new ArrayList<>(categoriesStats);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + categoriesStats.stream()
                .map(categoryStats ->
                        "Category: " + categoryStats.getCategoryName() + "\n" +
                                "Votes in category: " + categoryStats.getSummaryVotes() + "\n" +
                                "Songs in category: " + categoryStats.getSongsInCategory().size() + "\n" +
                                "Songs list sorted by votes: " + categoryStats.getSongsInCategory() + "\n")
                .collect(Collectors.joining("\n"));
    }
}
