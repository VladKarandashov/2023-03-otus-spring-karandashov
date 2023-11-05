package ru.otus.hw14booksapp.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import ru.otus.hw14booksapp.batch.AuthorItemReader;
import ru.otus.hw14booksapp.batch.AuthorItemWriter;
import ru.otus.hw14booksapp.entity.jpa.AuthorJpa;
import ru.otus.hw14booksapp.entity.mongo.AuthorMongo;
import ru.otus.hw14booksapp.repository.jpa.AuthorRepository;
import ru.otus.hw14booksapp.repository.mongo.AuthorMongoRepository;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    public static final String IMPORT_USER_JOB_NAME = "migrateAuthorJob";
    // специально установил поменьше, чтобы было наглядно
    // так как данных в БД пока маловато
    private static final int CHUNK_SIZE = 2;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMongoRepository authorMongoRepository;

    @Autowired
    private JobRepository jobRepository;

    @Bean
    public AuthorItemReader authorItemReader() {
        RepositoryItemReader<AuthorJpa> reader = new RepositoryItemReader<>();
        reader.setRepository(authorRepository);
        reader.setMethodName("findAll");
        reader.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        return new AuthorItemReader(reader);
    }

    @Bean
    public AuthorItemWriter authorItemWriter() {
        RepositoryItemWriter<AuthorMongo> writer = new RepositoryItemWriter<>();
        writer.setRepository(authorMongoRepository);
        writer.setMethodName("save");
        return new AuthorItemWriter(writer);
    }

    @Bean
    public Step migrateAuthorsStep(ItemReader<AuthorJpa> reader,
                                   ItemWriter<AuthorMongo> writer,
                                   ItemProcessor<AuthorJpa, AuthorMongo> itemProcessor) {
        return new StepBuilder("migrateAuthorsStep", jobRepository)
                .<AuthorJpa, AuthorMongo>chunk(CHUNK_SIZE, new ResourcelessTransactionManager())
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job migrateAuthorsJob(Step migrateAuthorsStep) {
        return new JobBuilder(IMPORT_USER_JOB_NAME, jobRepository)
                .start(migrateAuthorsStep)
                .build();
    }

    @Bean
    public JobRegistryBeanPostProcessor postProcessor(JobRegistry jobRegistry) {
        var processor = new JobRegistryBeanPostProcessor();
        processor.setJobRegistry(jobRegistry);
        return processor;
    }
}