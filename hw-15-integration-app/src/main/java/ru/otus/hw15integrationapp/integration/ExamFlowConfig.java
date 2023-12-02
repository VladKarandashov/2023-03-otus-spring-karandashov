package ru.otus.hw15integrationapp.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.hw15integrationapp.config.ExamConfig;
import ru.otus.hw15integrationapp.service.ReportService;

import static org.springframework.integration.scheduling.PollerMetadata.DEFAULT_POLLER;

@Configuration
@RequiredArgsConstructor
public class ExamFlowConfig {

    private final ExamConfig examConfig;
    private final ReportService reportService;

    @Bean
    public QueueChannel examChannel() {
        return MessageChannels.queue(examConfig.getConcurrentStudentsNumber()).getObject();
    }

    @Bean(name = DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).getObject();
    }

    @Bean
    public PublishSubscribeChannel reportChannel() {
        return MessageChannels.publishSubscribe().getObject();
    }

    @Bean
    public IntegrationFlow examFlow() {
        return IntegrationFlow.from(examChannel())
                .handle(reportService, "generateReport")
                .channel(reportChannel())
                .get();
    }
}