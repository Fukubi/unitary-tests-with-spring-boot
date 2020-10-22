package com.api.unitarytests;

import com.api.unitarytests.models.Contato;
import com.api.unitarytests.resources.ContatoResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ContatoTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ContatoResource contatoResource;
    
    @Test
    public void listarContatosDeveRetornarOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/contatos"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void tamanhoDeListarContatosDeveSerMenorQueZero() throws Exception {
        List<Contato> contato = contatoResource.listarContatos();
        assertThat(contato.size(), Matchers.greaterThanOrEqualTo(0));
    }
    
    @Test
    public void listarContatoUnicoDeveRetornarOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/contato/0"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void listarContatoUnicoNaoDeveSerNull() throws Exception {
        Contato contato = contatoResource.listarContato(0);
        Assertions.assertNotNull(contato);
    }
    
    @Test
    public void adicionarContatoDeveRetornarOk() throws Exception {
        Contato contato = new Contato();
        contato.setIdade(99);
        contato.setNome("Test-Unitario");
        contato.setNumero("(99) 99999-9999");
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/contato")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(contato)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void adicionarContatoDeveRetornarCorpo() throws Exception {
        Contato contato = new Contato();
        contato.setIdade(99);
        contato.setNome("Test-Unitario");
        contato.setNumero("(99) 99999-9999");
        
        
        
        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/contato")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(contato)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        assertThat(body.length(), Matchers.greaterThan(2));
    }
}
