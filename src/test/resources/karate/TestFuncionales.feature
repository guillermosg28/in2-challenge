Feature: Pruebas de la API

  Background:
    * url baseUrl

  Scenario: Create Spacecraft
    Given path '/api/v1/spacecrafts'
    And request
    """
    {
    "name": "Test store",
    "production_type": "Series",
    "model": "Model store",
    "manufacturer": "Manufacturer store",
    "passengers": 15
    }
    """
    When method post
    Then status 200

  Scenario: Get All Spacecrafts
    Given path '/api/v1/spacecrafts'
    And param page = 1
    And param name = ''
    When method get
    Then status 200

  Scenario: Search Spacecraft by Name
    Given path '/api/v1/spacecrafts'
    And param page = 1
    And param name = 'wing'
    When method get
    Then status 200

  Scenario: Get Spacecraft by ID
    Given path '/api/v1/spacecrafts/1'
    When method get
    Then status 200

  Scenario: Get Negative Spacecraft by ID
    Given path '/api/v1/spacecrafts/-1'
    When method get
    Then status 400
    And match response == {"cod_response":"001","des_response":"Nave espacial no encontrada."}

  Scenario: Update Spacecraft
    Given path '/api/v1/spacecrafts/1'
    And request
    """
    {
    "name": "wing",
    "production_type": "Series",
    "model": "Model1 update",
    "manufacturer": "Manufacturer1",
    "passengers": 5
    }
    """
    And header Content-Type = 'application/json'
    When method put
    Then status 200

