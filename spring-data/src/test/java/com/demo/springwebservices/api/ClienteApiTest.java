package com.demo.springwebservices.api;

import com.demo.springdata.dto.ClienteDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteApiTest {

    @LocalServerPort
    private Integer port = 0;

    private String BASEURL= "http://localhost";
    private static final String BASERESTURL = "/v1/api/cliente";
    private WebTestClient webTestClient = null;

    @BeforeEach
    void setUp(){
        BASEURL = BASEURL.concat(":").concat(port.toString()).concat(BASERESTURL);
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl(BASEURL)
                .responseTimeout(Duration.ofSeconds(3600))
                .build();
    }

    @Test
    void obtenerTodosLosClientes() {
        List<ClienteDto> clienteDtoList = this.webTestClient
                .get()
                .uri("/all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ClienteDto.class)
                .returnResult()
                .getResponseBody();

        System.out.println("clientesDtos" + clienteDtoList);
        assertFalse(clienteDtoList.isEmpty());
        assertTrue(clienteDtoList.get(0).getNombre() != null);
    }
}