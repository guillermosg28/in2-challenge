package dev.guillermosg.in2.infrastructure.adapters.output.persistence.mapper;

import dev.guillermosg.in2.domain.model.Spacecraft;
import dev.guillermosg.in2.infrastructure.adapters.output.persistence.entity.SpacecraftEntity;
import org.mapstruct.Mapper;

/**
 * The interface Spacecraft persistence mapper.
 */

@Mapper
public interface SpacecraftPersistenceMapper {

    /**
     * To spacecraft entity spacecraft entity.
     *
     * @param spacecraft the spacecraft
     * @return the spacecraft entity
     */
    SpacecraftEntity toSpacecraftEntity(Spacecraft spacecraft);

    /**
     * To spacecraft spacecraft.
     *
     * @param spacecraftEntity the spacecraft entity
     * @return the spacecraft
     */
    Spacecraft toSpacecraft(SpacecraftEntity spacecraftEntity);
}
