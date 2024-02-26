package dev.guillermosg.in2.infrastructure.adapters.input.rest.mapper;

import dev.guillermosg.in2.domain.model.SuccessResponse;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import org.mapstruct.Mapper;

/**
 * The interface SuccessResponseMapper
 */
@Mapper
public interface SuccessResponseMapper {

    /**
     * To dto success response dto.
     * @param successResponse
     * @return SuccessResponseDto
     */
    SuccessResponseDto successResponseToDto(SuccessResponse successResponse);

}
