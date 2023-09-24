package org.rsm.threads.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public class PackageReport {
    @Setter
    private long totalTime;
    private long successes;
    private long failures;
    private List<FileReport> fileReports;

    public PackageReport() {
        totalTime = 0;
        successes = -1;
        failures = -1;
        fileReports = Collections.synchronizedList(new ArrayList<>());
    }

    public long getSuccesses() {
        if (successes == -1) {
            successes = fileReports.parallelStream().filter(FileReport::isSuccess).count();
        }
        return successes;
    }

    public long getFailures() {
        if (failures == -1) {
            failures = fileReports.parallelStream().filter(Predicate.not(FileReport::isSuccess)).count();
        }
        return failures;
    }

    @Override
    public String toString() {
        return "\ntotalTime: " + totalTime + " ms" +
                "\nsuccesses: " + this.getSuccesses() +
                "\nfailures: " + this.getFailures() +
                "\nFileReports:\n\n" + fileReports.stream()
                .map(FileReport::toString)
                .collect(Collectors.joining("\n"));
    }
}
