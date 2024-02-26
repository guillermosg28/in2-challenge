# IN2 CHALLENGE 👨🏻‍💻

## Descripción 📃
<p style="text-align: justify;">
Esta aplicación Spring Boot con Arquitectura Hexagonal proporciona un servicio REST para mantenimiento CRUD
de naves espaciales de series y películas.</p>

<p>Tecnologías y herramientas usadas:</p>
1. SpringBoot<br/>
2. JPA<br/>
3. SpringData<br/>
4. Kafka<br/>
5. @Aspect<br/>
6. Cache<br/>
7. Gestión centralizada de excepciones.<br/>
8. Flyway<br/>
9. Lombok<br/>
10. OpenAPI<br/>
11. Karate<br/>
12. H2 Database<br/>
13. Docker<br/>

## Instrucciones ✏️

### Construcción del Proyecto
1. Clona este repositorio.
2. Aseguraese de tener Docker y Docker Compose instalados.
3. Abre una terminal y navega al directorio del proyecto.
2. Ejecuta el siguiente comando para construir e iniciar la aplicación:
```bash
docker compose up -d --build
```

### Ejecución de Tests 🔍
1. Pruebas Unitarias: Ejecuta las pruebas unitarias desde tu entorno de desarrollo o mediante el siguiente comando:
```bash
mvn test
```
2. Pruebas de Integración: Ejecuta las pruebas de integración con el siguiente comando:
``` bash
mvn test -Dtest=SpacecraftsRestAdapterIntegrationTest
```

3. Pruebas Funcionales: Ejecuta las pruebas funcionales(Karate) con el siguiente comando:
``` bash
mvn test -Dtest=KarateFunctionalTest
```

### Uso del Servicio 🚀
- Acceder al servicio REST a través de la URL: `http://localhost:5055/api/v1`

### Documentación de la API 📘
La API está documentada con OpenAPI y Swagger. Puedes acceder a la documentación de la API a través de la siguiente URL después de iniciar la aplicación: `http://localhost:9292/swagger-ui.html`
Además, puedes acceder a la colección de Postman en la carpeta src/resources/postman.