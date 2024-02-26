package dev.guillermosg.in2.infrastructure.adapters.output.persistence.entity;

import dev.guillermosg.in2.infrastructure.adapters.output.persistence.repository.SpacecraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SpacecraftJpaEntityTest {

    @Autowired
    SpacecraftRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void should_find_no_spacecrafts_if_repository_is_empty(){
        Iterable<SpacecraftEntity> spacecrafts = repository.findAll();
        assertThat(spacecrafts).isEmpty();
    }

    @Test
    void should_create_a_spacecraft(){
        SpacecraftEntity c1 = new SpacecraftEntity();
        c1.setName("SpaceX");
        c1.setProductionType("Falcon 9");
        c1.setModel("Model 1");
        c1.setManufacturer("SpaceX");
        c1.setPassengers(7);

        SpacecraftEntity spacecraft = repository.save(c1);

        assertThat(spacecraft)
                .hasFieldOrPropertyWithValue("name", "SpaceX")
                .hasFieldOrPropertyWithValue("productionType", "Falcon 9")
                .hasFieldOrPropertyWithValue("model", "Model 1")
                .hasFieldOrPropertyWithValue("manufacturer", "SpaceX")
                .hasFieldOrPropertyWithValue("passengers", 7);
    }

}
