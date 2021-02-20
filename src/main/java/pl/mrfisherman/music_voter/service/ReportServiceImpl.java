package pl.mrfisherman.music_voter.service;

import org.springframework.stereotype.Service;
import pl.mrfisherman.music_voter.model.pojo.report.Report;
import pl.mrfisherman.music_voter.model.pojo.report.ReportType;
import pl.mrfisherman.music_voter.model.pojo.report.CategoriesReport;
import pl.mrfisherman.music_voter.model.pojo.report.SongReport;

@Service
public class ReportServiceImpl implements ReportService {

    private final SongService songService;

    public ReportServiceImpl(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void generateReportToFile(ReportType reportType) {

    }

    @Override
    public Report generateReport(ReportType reportType) {
        Report report;
        switch (reportType) {
            case ALL_SONGS:
                report = new SongReport(createName(reportType), songService.getAllSongsSortedByVotes());
                break;
            case CATEGORIES:
                report = new CategoriesReport(createName(reportType), songService.getAllWithCountingCategoryVotes());
                break;
            case TOP_3_SONGS:
                report = new SongReport(createName(reportType), songService.getSongsSortedByVotesWithLimit(3));
                break;
            case TOP_10_SONGS:
                report = new SongReport(createName(reportType), songService.getSongsSortedByVotesWithLimit(10));
                break;
            default:
                throw new IllegalArgumentException("Illegal report type, try existing report type");
        }
        return report;
    }

    private String createName(ReportType reportType) {
        return String.format("---- REPORT Type: %s ----", reportType);
    }


}
