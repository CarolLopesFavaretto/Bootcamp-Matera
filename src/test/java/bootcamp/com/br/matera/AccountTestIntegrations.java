package bootcamp.com.br.matera;

import bootcamp.com.br.matera.Data.AccountData;
import bootcamp.com.br.matera.domain.Account;
import bootcamp.com.br.matera.dto.request.PixRequest;
import bootcamp.com.br.matera.dto.response.PixResponse;
import bootcamp.com.br.matera.repository.AccountRepository;
import bootcamp.com.br.matera.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AccountTestIntegrations {


    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldCreateAccount() throws Exception {
        Account account = AccountData.getAccount(9L);
        repository.save(account);

        mvc.perform(MockMvcRequestBuilders.post("/account")
                        .content(objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldUpdateAccount() throws Exception {
        Account account = AccountData.getAccount(9L);
        repository.save(account);

        mvc.perform(MockMvcRequestBuilders.put("/account/{id}", account.getId())
                        .content(objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Account resp = objectMapper.readValue(contentAsString, Account.class);
                    assertThat(resp.getNumberAccount()).isEqualTo(account.getNumberAccount());
                    assertThat(resp.getAgency()).isEqualTo(account.getAgency());
                    assertThat(resp.getBalance()).isEqualTo(account.getBalance());
                });

    }

    @Test
    public void shouldDebitAccountById() throws Exception {
        Account account = AccountData.getAccount(99L);
        repository.save(account);


        mvc.perform(MockMvcRequestBuilders
                        .post("/account/entry/{id}/debit/{value}", account.getId(), 50)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Account resp = objectMapper.readValue(contentAsString, Account.class);
                    assertThat(resp.getNumberAccount()).isEqualTo(account.getNumberAccount());
                    assertThat(resp.getAgency()).isEqualTo(account.getAgency());
                    assertThat(resp.getBalance()).isEqualTo(BigDecimal.valueOf(50).setScale(2));
                });
    }

    @Test
    public void shouldCreditAccountById() throws Exception {
        Account account = AccountData.getAccount(1L);
        repository.save(account);

        mvc.perform(MockMvcRequestBuilders
                        .post("/account/entry/{id}/credit/{value}", account.getId(), account.getBalance())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Account resp = objectMapper.readValue(contentAsString, Account.class);
                    assertThat(resp.getNumberAccount()).isEqualTo(account.getNumberAccount());
                    assertThat(resp.getAgency()).isEqualTo(account.getAgency());
                    assertThat(resp.getBalance()).isEqualTo(BigDecimal.valueOf(200).setScale(2));
                });
    }

    @Test
    public void shouldSendPix() throws Exception {
        Account account = AccountData.getAccount(1L);
        repository.save(account);

//        TODO criar owner

        PixRequest request = PixRequest.builder()
                .originKey("123456789")
                .destinationKey("9876543210")
                .balance(BigDecimal.valueOf(100).setScale(2))
                .build();

        service.pix(request);

        mvc.perform(MockMvcRequestBuilders
                        .post("/account/entry/pix")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldFindAccountById() throws Exception {
        Account account = AccountData.getAccount(10L);
        repository.save(account);

        mvc.perform(MockMvcRequestBuilders
                        .get("/account/{id}", account.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Account resp = objectMapper.readValue(contentAsString, Account.class);
                    assertThat(resp.getNumberAccount()).isEqualTo(account.getNumberAccount());
                    assertThat(resp.getAgency()).isEqualTo(account.getAgency());
                    assertThat(resp.getBalance()).isEqualTo(account.getBalance().setScale(2));
                });
    }


    @Test
    public void shouldDeleteAccount() throws Exception {
        Account account = AccountData.getAccount(10L);
        repository.save(account);

        mvc.perform(MockMvcRequestBuilders.delete("/account/{id}", account.getId())
                        .content(objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
