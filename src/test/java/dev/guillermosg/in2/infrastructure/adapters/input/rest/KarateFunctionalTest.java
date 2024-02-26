package dev.guillermosg.in2.infrastructure.adapters.input.rest;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KarateFunctionalTest {

    @LocalServerPort
    private int port;

    @Karate.Test
    Karate runAll() {
        System.setProperty("karate.env", "test");
        System.setProperty("karate.config.dir", "classpath:karate");
        System.setProperty("base_url", "http://localhost:" + port);
        return Karate.run("classpath:karate/TestFuncionales.feature").relativeTo(getClass());
    }
}
