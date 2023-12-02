package ru.otus.hw15integrationapp.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import ru.otus.hw15integrationapp.config.ExamConfig;
import ru.otus.hw15integrationapp.service.ReportService;

import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class ExamFlowConfig {

    private final ExamConfig examConfig;
    private final ReportService reportService;

    @Bean
    public MessageChannel studentChannel() {
        return MessageChannels.queue(examConfig.getConcurrentStudentsNumber()).getObject();
    }

    @Bean
    public PublishSubscribeChannel reportChannel() {
        return MessageChannels.publishSubscribe().getObject();
    }

    @Bean
    public IntegrationFlow examFlow() {
        return IntegrationFlow.from(studentChannel())
                .channel(MessageChannels.executor(Executors.newFixedThreadPool(5)))
                .handle(reportService, "generateReport")
                .channel(reportChannel())
                .get();
    }
}