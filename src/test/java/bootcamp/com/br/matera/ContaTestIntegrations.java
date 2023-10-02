package bootcamp.com.br.matera;

import bootcamp.com.br.matera.domain.Conta;
import bootcamp.com.br.matera.dto.request.ContaRequest;
import bootcamp.com.br.matera.service.ContaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ContaTestIntegrations {

    @Autowired
    private ContaService service;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldCreateConta() throws Exception {
        ContaRequest conta = new ContaRequest();
        service.createConta(conta);

        mvc.perform(MockMvcRequestBuilders.post("/conta")
                        .content(objectMapper.writeValueAsString(conta))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldFindContaById() throws Exception {
        ContaRequest conta = new ContaRequest();
        service.createConta(conta);


        mvc.perform(MockMvcRequestBuilders
                        .get("/conta/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Conta resp = objectMapper.readValue(contentAsString, Conta.class);
                    assertThat(resp.getNumeroConta()).isEqualTo(conta.getNumeroConta());
                    assertThat(resp.getAgencia()).isEqualTo(conta.getAgencia());
                    assertThat(resp.getSaldo()).isEqualTo(conta.getSaldo());
                });
    }


    @Test
    public void shouldDeleteConta() throws Exception {
        ContaRequest conta = new ContaRequest();
        service.createConta(conta);

        mvc.perform(MockMvcRequestBuilders.delete("/conta/{id}", 1L)
                        .content(objectMapper.writeValueAsString(conta))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}
