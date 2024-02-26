package dev.guillermosg.in2.infrastructure.adapters.output.integration;

import dev.guillermosg.in2.application.ports.output.SpacecraftOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class SpacecraftIntegrationAdapter {

    private final SpacecraftOutputPort spacecraftOutputPort;

    @KafkaListener(topics = "in2-topic-spacecraft-deletion")
    public void consumeSpacecraftDeletionEvent(String spacecraftId) {
        Long id = Long.parseLong(spacecraftId);
        spacecraftOutputPort._deleteSpacecraftById(id.intValue());
    }
}
