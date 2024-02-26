package dev.guillermosg.in2.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.guillermosg.in2.application.ports.output.SpacecraftOutputPort;
import dev.guillermosg.in2.domain.exception.SpacecraftFoundException;
import dev.guillermosg.in2.domain.model.AllSpacecraftsResponse;
import dev.guillermosg.in2.domain.model.Spacecraft;
import dev.guillermosg.in2.domain.model.SuccessResponse;
import dev.guillermosg.in2.infrastructure.adapters.config.PageSizeConfig;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SpacecraftService.class})
@ExtendWith(SpringExtension.class)
class SpacecraftServiceTest {
    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    private PageSizeConfig pageSizeConfig;

    @MockBean
    private SpacecraftOutputPort spacecraftOutputPort;

    @Autowired
    private SpacecraftService spacecraftService;

    /**
     * Method under test:
     * {@link SpacecraftService#_getAllSpacecrafts(String, Integer)}
     */
    @Test
    void test_getAllSpacecrafts() {
        // Arrange
        when(pageSizeConfig.getDefaultPageSize()).thenReturn(3);
        AllSpacecraftsResponse.AllSpacecraftsResponseBuilder sizeResult = AllSpacecraftsResponse.builder()
                .first(true)
                .last(true)
                .page(1)
                .size(3);
        AllSpacecraftsResponse buildResult = sizeResult.spacecrafts(new ArrayList<>())
                .totalElements(1)
                .totalPages(1)
                .build();
        when(spacecraftOutputPort._getAllSpacecrafts(Mockito.<String>any(), Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(buildResult);

        // Act
        spacecraftService._getAllSpacecrafts("Name", 1);

        // Assert
        verify(spacecraftOutputPort)._getAllSpacecrafts(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Integer>any());
        verify(pageSizeConfig).getDefaultPageSize();
    }

    /**
     * Method under test:
     * {@link SpacecraftService#_getAllSpacecrafts(String, Integer)}
     */
    @Test
    void test_getAllSpacecrafts2() {
        // Arrange
        when(pageSizeConfig.getDefaultPageSize()).thenReturn(3);
        when(spacecraftOutputPort._getAllSpacecrafts(Mockito.<String>any(), Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenThrow(new SpacecraftFoundException());

        // Act and Assert
        assertThrows(SpacecraftFoundException.class, () -> spacecraftService._getAllSpacecrafts("Name", 1));
        verify(spacecraftOutputPort)._getAllSpacecrafts(Mockito.<String>any(), Mockito.<Integer>any(),
                Mockito.<Integer>any());
        verify(pageSizeConfig).getDefaultPageSize();
    }

    /**
     * Method under test: {@link SpacecraftService#_getSpacecraftById(Integer)}
     */
    @Test
    void test_getSpacecraftById() {
        // Arrange
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        when(spacecraftOutputPort._getSpacecraftById(Mockito.<Integer>any())).thenReturn(buildResult);
        Spacecraft buildResult2 = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        Optional<Spacecraft> ofResult = Optional.of(buildResult2);
        when(spacecraftOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        spacecraftService._getSpacecraftById(1);

        // Assert
        verify(spacecraftOutputPort)._getSpacecraftById(Mockito.<Integer>any());
        verify(spacecraftOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SpacecraftService#_getSpacecraftById(Integer)}
     */
    @Test
    void test_getSpacecraftById2() {
        // Arrange
        when(spacecraftOutputPort._getSpacecraftById(Mockito.<Integer>any())).thenThrow(new SpacecraftFoundException());
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        Optional<Spacecraft> ofResult = Optional.of(buildResult);
        when(spacecraftOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(SpacecraftFoundException.class, () -> spacecraftService._getSpacecraftById(1));
        verify(spacecraftOutputPort)._getSpacecraftById(Mockito.<Integer>any());
        verify(spacecraftOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SpacecraftService#_createSpacecraft(Spacecraft)}
     */
    @Test
    void test_createSpacecraft() {
        // Arrange
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        when(spacecraftOutputPort._createSpacecraft(Mockito.<Spacecraft>any())).thenReturn(buildResult);

        // Act
        spacecraftService._createSpacecraft(new Spacecraft());

        // Assert
        verify(spacecraftOutputPort)._createSpacecraft(Mockito.<Spacecraft>any());
    }

    /**
     * Method under test: {@link SpacecraftService#_createSpacecraft(Spacecraft)}
     */
    @Test
    void test_createSpacecraft2() {
        // Arrange
        when(spacecraftOutputPort._createSpacecraft(Mockito.<Spacecraft>any())).thenThrow(new SpacecraftFoundException());

        // Act and Assert
        assertThrows(SpacecraftFoundException.class, () -> spacecraftService._createSpacecraft(new Spacecraft()));
        verify(spacecraftOutputPort)._createSpacecraft(Mockito.<Spacecraft>any());
    }

    /**
     * Method under test:
     * {@link SpacecraftService#_updateSpacecraftById(Integer, Spacecraft)}
     */
    @Test
    void test_updateSpacecraftById() {
        // Arrange
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        when(spacecraftOutputPort._updateSpacecraftById(Mockito.<Integer>any(), Mockito.<Spacecraft>any()))
                .thenReturn(buildResult);
        Spacecraft buildResult2 = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        Optional<Spacecraft> ofResult = Optional.of(buildResult2);
        when(spacecraftOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        Spacecraft spacecraft = new Spacecraft();

        // Act
        spacecraftService._updateSpacecraftById(1, spacecraft);

        // Assert
        verify(spacecraftOutputPort)._updateSpacecraftById(Mockito.<Integer>any(), Mockito.<Spacecraft>any());
        verify(spacecraftOutputPort).findById(Mockito.<Integer>any());
        assertEquals(1L, spacecraft.getId().longValue());
    }

    /**
     * Method under test:
     * {@link SpacecraftService#_updateSpacecraftById(Integer, Spacecraft)}
     */
    @Test
    void test_updateSpacecraftById2() {
        // Arrange
        when(spacecraftOutputPort._updateSpacecraftById(Mockito.<Integer>any(), Mockito.<Spacecraft>any()))
                .thenThrow(new SpacecraftFoundException());
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        Optional<Spacecraft> ofResult = Optional.of(buildResult);
        when(spacecraftOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(SpacecraftFoundException.class, () -> spacecraftService._updateSpacecraftById(1, new Spacecraft()));
        verify(spacecraftOutputPort)._updateSpacecraftById(Mockito.<Integer>any(), Mockito.<Spacecraft>any());
        verify(spacecraftOutputPort).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link SpacecraftService#_deleteSpacecraftById(Integer)}
     */
    @Test
    void test_deleteSpacecraftById() {
        // Arrange
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        Optional<Spacecraft> ofResult = Optional.of(buildResult);
        when(spacecraftOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new CompletableFuture<>());

        // Act
        SuccessResponse actual_deleteSpacecraftByIdResult = spacecraftService._deleteSpacecraftById(1);

        // Assert
        verify(spacecraftOutputPort).findById(Mockito.<Integer>any());
        verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
        assertEquals("000", actual_deleteSpacecraftByIdResult.getCode());
        assertEquals("Nave espacial eliminada correctamente", actual_deleteSpacecraftByIdResult.getMessage());
    }

    /**
     * Method under test: {@link SpacecraftService#_deleteSpacecraftById(Integer)}
     */
    @Test
    void test_deleteSpacecraftById2() {
        // Arrange
        Spacecraft buildResult = Spacecraft.builder()
                .id(1L)
                .manufacturer("Manufacturer")
                .model("Model")
                .name("Name")
                .passengers(1)
                .productionType("Production Type")
                .build();
        Optional<Spacecraft> ofResult = Optional.of(buildResult);
        when(spacecraftOutputPort.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenThrow(new SpacecraftFoundException());

        // Act and Assert
        assertThrows(SpacecraftFoundException.class, () -> spacecraftService._deleteSpacecraftById(1));
        verify(spacecraftOutputPort).findById(Mockito.<Integer>any());
        verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
    }
}
