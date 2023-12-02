package ru.otus.hw15integrationapp.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import ru.otus.hw15integrationapp.service.ReportService;

@Configuration
@RequiredArgsConstructor
public class ExamFlowConfig {

    private final ReportService reportService;

    @Bean
    public QueueChannel examChannel() {
        return MessageChannels.queue(5).getObject();
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