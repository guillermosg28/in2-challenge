package dev.guillermosg.in2.infrastructure.adapters.input.rest.mapper;

import dev.guillermosg.in2.domain.model.AllSpacecraftsResponse;
import dev.guillermosg.in2.domain.model.Spacecraft;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.AllSpacecraftsResponseDto;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.SpacecraftDto;
import org.mapstruct.Mapper;

/**
 * The interface SpacecraftRestMapper
 */
@Mapper
public interface SpacecraftRestMapper {

    /**
     * @param model (required)
     * @return AllSpacecraftsResponseDto
     */
    AllSpacecraftsResponseDto toAllSpacecraftsResponseDto(AllSpacecraftsResponse model);

    /**
     * @param model (required)
     * @return Spacecraft
     */
    SpacecraftDto toSpacecraftDto(Spacecraft model);

    /**
     * @param dto (required)
     * @return SpacecraftDto
     */
    Spacecraft toSpacecraft(SpacecraftDto dto);

}
