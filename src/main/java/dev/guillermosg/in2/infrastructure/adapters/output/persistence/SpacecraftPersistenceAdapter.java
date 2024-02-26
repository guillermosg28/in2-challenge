package dev.guillermosg.in2.infrastructure.adapters.output.persistence;

import dev.guillermosg.in2.application.ports.output.SpacecraftOutputPort;
import dev.guillermosg.in2.domain.model.AllSpacecraftsResponse;
import dev.guillermosg.in2.domain.model.Spacecraft;
import dev.guillermosg.in2.infrastructure.adapters.output.persistence.entity.SpacecraftEntity;
import dev.guillermosg.in2.infrastructure.adapters.output.persistence.mapper.SpacecraftPersistenceMapper;
import dev.guillermosg.in2.infrastructure.adapters.output.persistence.repository.SpacecraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * The type Spacecraft persistence adapter.
 */
@Component
@RequiredArgsConstructor
public class SpacecraftPersistenceAdapter implements SpacecraftOutputPort {

    private final SpacecraftRepository spacecraftRepository;
    private final SpacecraftPersistenceMapper spacecraftPersistenceMapper;

    /**
     * @param name Nombre de la nave espacial (optional)
     * @param page Número de página (optional)
     * @return AllSpacecraftsResponse
     */
    @Override
    public AllSpacecraftsResponse _getAllSpacecrafts(String name, Integer page, Integer pageSize) {

        AllSpacecraftsResponse allSpacecraftsResponse = new AllSpacecraftsResponse();

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<SpacecraftEntity> spacecrafts = spacecraftRepository.findByNameContaining(name, pageable);

        allSpacecraftsResponse.setSpacecrafts(spacecrafts.getContent().stream().map(spacecraftPersistenceMapper::toSpacecraft).toList());
        allSpacecraftsResponse.setPage(spacecrafts.getNumber() + 1);
        allSpacecraftsResponse.setSize(spacecrafts.getSize());
        allSpacecraftsResponse.setTotalElements((int) spacecrafts.getTotalElements());
        allSpacecraftsResponse.setTotalPages(spacecrafts.getTotalPages());
        allSpacecraftsResponse.setLast(spacecrafts.isLast());
        allSpacecraftsResponse.setFirst(spacecrafts.isFirst());

        return allSpacecraftsResponse;
    }

    /**
     * @param id ID de la nave espacial (required)
     * @return Spacecraft
     */
    @Override
    public Spacecraft _getSpacecraftById(Integer id) {
        return spacecraftPersistenceMapper.toSpacecraft(spacecraftRepository.findById(Long.valueOf(id)).orElse(null));
    }

    /**
     * @param spacecraft (required)
     * @return Spacecraft
     */
    @Override
    public Spacecraft _createSpacecraft(Spacecraft spacecraft) {
        return spacecraftPersistenceMapper.toSpacecraft(spacecraftRepository.save(spacecraftPersistenceMapper.toSpacecraftEntity(spacecraft)));
    }

    /**
     * @param id         ID de la nave espacial (required)
     * @param spacecraft (required)
     * @return Spacecraft
     */
    @Override
    public Spacecraft _updateSpacecraftById(Integer id, Spacecraft spacecraft) {
        return spacecraftPersistenceMapper.toSpacecraft(spacecraftRepository.save(spacecraftPersistenceMapper.toSpacecraftEntity(spacecraft)));
    }

    /**
     * @param id ID de la nave espacial (required)
     */
    @Override
    public void _deleteSpacecraftById(Integer id) {
        spacecraftRepository.deleteById(Long.valueOf(id));
    }

    /**
     * @param id ID de la nave espacial (required)
     * @return Spacecraft
     */
    @Override
    public Optional<Spacecraft> findById(Integer id) {
        return spacecraftRepository.findById(Long.valueOf(id)).map(spacecraftPersistenceMapper::toSpacecraft);
    }
}
