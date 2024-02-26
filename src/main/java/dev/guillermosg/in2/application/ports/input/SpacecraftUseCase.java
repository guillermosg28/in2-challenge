package dev.guillermosg.in2.application.ports.input;

import dev.guillermosg.in2.domain.model.AllSpacecraftsResponse;
import dev.guillermosg.in2.domain.model.Spacecraft;
import dev.guillermosg.in2.domain.model.SuccessResponse;

/**
 * The Spacecraft use case.
 */
public interface SpacecraftUseCase {

    /**
     * @param name Nombre de la nave espacial (optional)
     * @param page Número de página (optional)
     * @return AllSpacecraftsResponseDto
     */
    AllSpacecraftsResponse _getAllSpacecrafts(String name, Integer page);

    /**
     * @param id ID de la nave espacial (required)
     * @return Spacecraft
     */
    Spacecraft _getSpacecraftById(Integer id);

    /**
     * @param spacecraft (required)
     * @return Spacecraft
     */
    Spacecraft _createSpacecraft(Spacecraft spacecraft);

    /**
     * @param id            ID de la nave espacial (required)
     * @param spacecraft (required)
     * @return Spacecraft
     */
    Spacecraft _updateSpacecraftById(Integer id, Spacecraft spacecraft);

    /**
     * @param id ID de la nave espacial (required)
     * @return Spacecraft
     */
    SuccessResponse _deleteSpacecraftById(Integer id);
}
