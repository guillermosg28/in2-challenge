package dev.guillermosg.in2.infrastructure.adapters.input.rest;

import dev.guillermosg.in2.application.ports.dto.ErrorDTO;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.AllSpacecraftsResponseDto;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.SpacecraftDto;
import dev.guillermosg.in2.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Spacecrafts rest adapter integration test.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class SpacecraftsRestAdapterIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test create spacecraft.
     */
    @Test
    @Order(1)
    void testCreateSpacecraft() {
        SpacecraftDto spacecraftDto = new SpacecraftDto();
        spacecraftDto.setName("SpaceX");
        spacecraftDto.setProductionType("Falcon 9");
        spacecraftDto.setModel("Model 1");
        spacecraftDto.setManufacturer("SpaceX");
        spacecraftDto.setPassengers(7);
        ResponseEntity<SpacecraftDto> response = restTemplate.postForEntity("/api/v1/spacecrafts", spacecraftDto, SpacecraftDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test alls.
     */
    @Test
    @Order(2)
    void testAlls() {
        ResponseEntity<AllSpacecraftsResponseDto> response = restTemplate.getForEntity("/api/v1/spacecrafts?page=1&name=", AllSpacecraftsResponseDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test search by name.
     */
    @Test
    @Order(3)
    void testSearchByName() {
        ResponseEntity<SpacecraftDto> response = restTemplate.getForEntity("/api/v1/spacecrafts?page=1&name=wing", SpacecraftDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test get by id.
     */
    @Test
    @Order(4)
    void testGetById() {
        ResponseEntity<SpacecraftDto> response = restTemplate.getForEntity("/api/v1/spacecrafts/1", SpacecraftDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test negative get by id.
     */
    @Test
    @Order(5)
    void testNegativeGetById() {
        ResponseEntity<ErrorDTO> response = restTemplate.getForEntity("/api/v1/spacecrafts/-1", ErrorDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Nave espacial no encontrada.", response.getBody().getDesResponse());
    }

    /**
     * Test update spacecraft.
     */
    @Test
    @Order(6)
    void testUpdateSpacecraft() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SpacecraftDto spacecraftDto = new SpacecraftDto();
        spacecraftDto.setName("SpaceX");
        spacecraftDto.setProductionType("Falcon 9");
        spacecraftDto.setModel("Model 1");
        spacecraftDto.setManufacturer("SpaceX");
        spacecraftDto.setPassengers(7);

        HttpEntity<SpacecraftDto> requestEntity = new HttpEntity<>(spacecraftDto, headers);


        ResponseEntity<SpacecraftDto> response = restTemplate.exchange("/api/v1/spacecrafts/1", HttpMethod.PUT, requestEntity, SpacecraftDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
