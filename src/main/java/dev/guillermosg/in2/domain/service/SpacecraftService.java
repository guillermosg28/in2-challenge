package dev.guillermosg.in2.domain.service;

import dev.guillermosg.in2.application.ports.input.SpacecraftUseCase;
import dev.guillermosg.in2.application.ports.output.SpacecraftOutputPort;
import dev.guillermosg.in2.domain.exception.SpacecraftFoundException;
import dev.guillermosg.in2.domain.model.AllSpacecraftsResponse;
import dev.guillermosg.in2.domain.model.Spacecraft;
import dev.guillermosg.in2.domain.model.SuccessResponse;
import dev.guillermosg.in2.infrastructure.adapters.config.PageSizeConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * The Spacecraft service.
 */

@Service
@AllArgsConstructor
@Slf4j
public class SpacecraftService implements SpacecraftUseCase {

    private final SpacecraftOutputPort spacecraftOutputPort;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private PageSizeConfig pageSizeConfig;

    /**
     * @param name Nombre de la nave espacial (optional)
     * @param page Número de página (optional)
     * @return AllSpacecraftsResponseDto
     */
    @Override
    @Cacheable("spacecraftsByName")
    public AllSpacecraftsResponse _getAllSpacecrafts(String name, Integer page) {
        return spacecraftOutputPort._getAllSpacecrafts(name, page, pageSizeConfig.getDefaultPageSize());
    }

    /**
     * @param id ID de la nave espacial (required)
     * @return Spacecraft
     */
    @Override
    public Spacecraft _getSpacecraftById(Integer id) {

        var spacecraftDomain = spacecraftOutputPort.findById(id).orElseThrow(SpacecraftFoundException::new);
        return spacecraftOutputPort._getSpacecraftById(Math.toIntExact(spacecraftDomain.getId()));
    }

    /**
     * @param spacecraft (required)
     * @return Spacecraft
     */
    @Override
    public Spacecraft _createSpacecraft(Spacecraft spacecraft) {
        return spacecraftOutputPort._createSpacecraft(spacecraft);
    }

    /**
     * @param id         ID de la nave espacial (required)
     * @param spacecraft (required)
     * @return Spacecraft
     */
    @Override
    public Spacecraft _updateSpacecraftById(Integer id, Spacecraft spacecraft) {

        var spacecraftDomain = spacecraftOutputPort.findById(id).orElseThrow(SpacecraftFoundException::new);

        spacecraft.setId(spacecraftDomain.getId());
        return spacecraftOutputPort._updateSpacecraftById(id, spacecraft);
    }

    /**
     * @param id ID de la nave espacial (required)
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse _deleteSpacecraftById(Integer id) {

        var spacecraftDomain = spacecraftOutputPort.findById(id).orElseThrow(SpacecraftFoundException::new);

        // Enviar mensaje a Kafka
        kafkaTemplate.send("in2-topic-spacecraft-deletion", String.valueOf(spacecraftDomain.getId()));

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Nave espacial eliminada correctamente");
        successResponse.setCode("000");

        return successResponse;
    }
}
