package dev.guillermosg.in2.infrastructure.adapters.input.rest;

import dev.guillermosg.in2.application.ports.input.SpacecraftUseCase;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.AllSpacecraftsResponseDto;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.SpacecraftDto;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.mapper.SpacecraftRestMapper;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.mapper.SuccessResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Spacecraft rest adapter.
 */

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class SpacecraftRestAdapter implements SpacecraftsApi{

    private final SpacecraftUseCase spacecraftUseCase;
    private final SpacecraftRestMapper spacecraftRestMapper;
    private final SuccessResponseMapper successResponseMapper;

    /**
     * @param spacecraftDto (required)
     * @return SpacecraftDto
     */
    @Override
    public ResponseEntity<SpacecraftDto> _createSpacecraft(SpacecraftDto spacecraftDto) {
        return ResponseEntity.ok().body(spacecraftRestMapper.toSpacecraftDto(spacecraftUseCase._createSpacecraft(spacecraftRestMapper.toSpacecraft(spacecraftDto))));
    }

    /**
     * @param id ID de la nave espacial (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _deleteSpacecraftById(Integer id) {
        return ResponseEntity.ok().body(successResponseMapper.successResponseToDto(spacecraftUseCase._deleteSpacecraftById(id)));
    }

    /**
     * @param name Nombre de la nave espacial (optional)
     * @param page Número de página (optional)
     * @return AllSpacecraftsResponseDto
     */
    @Override
    public ResponseEntity<AllSpacecraftsResponseDto> _getAllSpacecrafts(String name, Integer page) {
        return ResponseEntity.ok().body(spacecraftRestMapper.toAllSpacecraftsResponseDto(spacecraftUseCase._getAllSpacecrafts(name, page)));
    }

    /**
     * @param id ID de la nave espacial (required)
     * @return SpacecraftDto
     */
    @Override
    public ResponseEntity<SpacecraftDto> _getSpacecraftById(Integer id) {
        return ResponseEntity.ok().body(spacecraftRestMapper.toSpacecraftDto(spacecraftUseCase._getSpacecraftById(id)));
    }

    /**
     * @param id            ID de la nave espacial (required)
     * @param spacecraftDto (required)
     * @return SpacecraftDto
     */
    @Override
    public ResponseEntity<SpacecraftDto> _updateSpacecraftById(Integer id, SpacecraftDto spacecraftDto) {
        return ResponseEntity.ok().body(spacecraftRestMapper.toSpacecraftDto(spacecraftUseCase._updateSpacecraftById(id, spacecraftRestMapper.toSpacecraft(spacecraftDto))));
    }
}
