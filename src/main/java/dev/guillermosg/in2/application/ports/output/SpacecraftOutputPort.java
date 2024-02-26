package dev.guillermosg.in2.application.ports.output;

import dev.guillermosg.in2.domain.model.AllSpacecraftsResponse;
import dev.guillermosg.in2.domain.model.Spacecraft;

import java.util.Optional;

/**
 * The interface Spacecraft output port.
 */

public interface SpacecraftOutputPort {

    /**
     * @param name Nombre de la nave espacial (optional)
     * @param page Número de página (optional)
     * @param pageSize Tamaño de la página
     * @return AllSpacecraftsResponseDto
     */
    AllSpacecraftsResponse _getAllSpacecrafts(String name, Integer page, Integer pageSize);

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
     */
    void _deleteSpacecraftById(Integer id);

    /**
     * @param id ID de la nave espacial (required)
     * @return Spacecraft
     */
    Optional<Spacecraft> findById(Integer id);
}
