package dev.guillermosg.in2.infrastructure.adapters.output.persistence.repository;

import dev.guillermosg.in2.infrastructure.adapters.output.persistence.entity.SpacecraftEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Price repository.
 */

@Repository
public interface SpacecraftRepository extends JpaRepository<SpacecraftEntity, Long> {
    Page<SpacecraftEntity> findByNameContaining(String name, Pageable pageable);
}
