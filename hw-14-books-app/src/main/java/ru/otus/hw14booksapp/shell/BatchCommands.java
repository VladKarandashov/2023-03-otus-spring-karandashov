package ru.otus.hw14booksapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final Job migrateAuthorsJob;

    private final JobLauncher jobLauncher;

    @SuppressWarnings("unused")
    @ShellMethod(value = "startAuthorsMigrationJob", key = "migrate-authors")
    public void startAuthorsMigrationJobLauncher() throws Exception {
        JobExecution jobExecution = jobLauncher.run(migrateAuthorsJob, new JobParameters());
    }
}