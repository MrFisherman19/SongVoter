package pl.mrfisherman.music_voter.service;

import pl.mrfisherman.music_voter.model.pojo.report.Report;
import pl.mrfisherman.music_voter.model.pojo.report.ReportType;

public interface ReportService {

    void generateReportToFile(ReportType reportType);

    Report generateReport(ReportType reportType);
}
