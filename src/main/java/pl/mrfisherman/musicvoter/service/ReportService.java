package pl.mrfisherman.musicvoter.service;

import pl.mrfisherman.musicvoter.model.pojo.report.Report;
import pl.mrfisherman.musicvoter.model.pojo.report.ReportType;

import java.nio.file.Path;

public interface ReportService {

    /**
     * Method generate given report by type to the file.
     * @param reportType is enum value of ReportType.java
     * @param extension is enum value of ReportExtension.java
     * @return Path to generated file
     */
    Path generateReportToFile(ReportType reportType, ReportExtension extension);

    /**
     * Method generate report but not save it to the file.
     * @param reportType is enum value of ReportType.java
     * @return Report object
     */
    Report generateReport(ReportType reportType);
}
