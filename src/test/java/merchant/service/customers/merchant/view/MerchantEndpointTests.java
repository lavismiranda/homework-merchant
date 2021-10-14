package merchant.service.customers.merchant.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import merchant.service.customers.merchant.application.impl.MerchantService;
import merchant.service.customers.merchant.domain.model.Login;
import merchant.service.transactions.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static merchant.service.ObjectTests.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchantEndpointTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ObjectMapper objectMapper;

    String token;

    @BeforeEach
    void setup() throws Exception{
        token = merchantService.authenticate(getLogin()).getToken();
    }

    @Test
    public void auth_should_be_success() throws Exception{
        String body = objectMapper.writeValueAsString(getLogin());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth").content(body)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void auth_should_be_return_error_with_invalid_credentials() throws Exception{
        String body = objectMapper.writeValueAsString(getFakeLogin());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(500)));
        resultActions.andExpect(jsonPath("$.token").doesNotExist());
    }

    @Test
    public void client_should_be_success() throws Exception{
        String body = objectMapper.writeValueAsString(getTransaction());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/client").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(jsonPath("$.customerInfo").exists());
    }

    @Test
    public void client_should_be_return_error_without_authorization() throws Exception{
        String body = objectMapper.writeValueAsString(getTransaction());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/client").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(40405)));
        resultActions.andExpect(jsonPath("$.customerInfo").doesNotExist());
    }

}
