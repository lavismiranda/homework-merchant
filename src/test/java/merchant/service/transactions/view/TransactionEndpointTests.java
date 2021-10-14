package merchant.service.transactions.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import merchant.service.customers.merchant.application.impl.MerchantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class TransactionEndpointTests {

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
    public void transaction_should_be_success() throws Exception{
        String body = objectMapper.writeValueAsString(getTransaction());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/transaction").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(jsonPath("$.fx").exists());

    }

    @Test
    public void transaction_should_be_return_error_without_auth() throws Exception{
        String body = objectMapper.writeValueAsString(getTransaction());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/transaction").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(40405)));

    }

    @Test
    public void report_should_be_success() throws Exception{
        String body = objectMapper.writeValueAsString(getReport());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/report").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(jsonPath("$.status",is("APPROVED")));

    }

    @Test
    public void report_should_be_return_error_without_auth() throws Exception{
        String body = objectMapper.writeValueAsString(getReport());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/report").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(40405)));
    }

    @Test
    public void report_should_be_return_not_found_with_invalid_body() throws Exception{
        String body = objectMapper.writeValueAsString(getReportNOK());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/report").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(40402)));
    }

    @Test
    public void list_should_be_success() throws Exception{
        String body = objectMapper.writeValueAsString(getFilterRangeOK());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/list").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());

        resultActions.andExpect(jsonPath("$.data").exists());

    }

    @Test
    public void list_should_be_return_not_found() throws Exception{
        String body = objectMapper.writeValueAsString(getFilterRangeNOK());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/list").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(40406)));

    }

    @Test
    public void list_should_be_return_error_without_auth() throws Exception{
        String body = objectMapper.writeValueAsString(getFilterRangeOK());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/list").content(body)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        resultActions.andExpect(jsonPath("$.code",is(40405)));
    }


}
