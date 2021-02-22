package pl.mrfisherman.musicvoter.model.pojo.report;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public abstract class Report {

    private final String title;
    private final String created;
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public Report(String title) {
        this.title = title;
        this.created = getFormattedLocalDateTime();
    }

    private String getFormattedLocalDateTime() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return "Report{" +
                "title='" + title + '\'' +
                ", created=" + created +
                '}';
    }
}
