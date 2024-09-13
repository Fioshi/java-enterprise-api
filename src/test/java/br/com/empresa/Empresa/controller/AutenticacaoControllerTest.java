package br.com.empresa.Empresa.controller;

import br.com.empresa.Empresa.domain.usuario.AutenticacaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AutenticacaoControllerTest {

    @MockBean
    private AutenticacaoService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornar400ComErro() throws Exception {
        //ARRANGE
        var json = "{}";
        //ACT
        var resposta = mockMvc.perform(
                post("/api/login").accept(json).contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //ASSERT
        Assertions.assertEquals(400, resposta.getStatus());
    }
}