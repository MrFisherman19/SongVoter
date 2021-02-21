package pl.mrfisherman.music_voter.service;

import pl.mrfisherman.music_voter.model.pojo.report.Report;
import pl.mrfisherman.music_voter.model.pojo.report.ReportType;

import java.nio.file.Path;

public interface ReportService {

    Path generateReportToFile(ReportType reportType, ReportExtension extension);

    Report generateReport(ReportType reportType);
}
