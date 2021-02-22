package pl.mrfisherman.musicvoter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mrfisherman.musicvoter.model.pojo.report.CategoriesReport;
import pl.mrfisherman.musicvoter.model.pojo.report.Report;
import pl.mrfisherman.musicvoter.model.pojo.report.ReportType;
import pl.mrfisherman.musicvoter.model.pojo.report.SongReport;
import pl.mrfisherman.musicvoter.service.file.SongFileManager;

import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SongService songService;
    private final SongFileManager songFileManager;

    @Override
    public Path generateReportToFile(ReportType reportType, ReportExtension extension) {
        final String nameOfReport = reportType.toString() + UUID.randomUUID();
        return songFileManager.saveReportToFile(generateReport(reportType), nameOfReport, extension.toString());
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
