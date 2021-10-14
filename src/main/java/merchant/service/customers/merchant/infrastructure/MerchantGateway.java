package merchant.service.customers.merchant.infrastructure;

import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import merchant.service.customers.merchant.domain.model.Login;
import merchant.service.customers.merchant.infrastructure.dto.client.ClientDTO;
import merchant.service.customers.merchant.infrastructure.dto.merchant.VerifyAuthenticationDTO;
import merchant.service.gateway.decoder.MerchantErrorDecoder;
import merchant.service.infrastructure.config.FeignConfiguration;
import merchant.service.transactions.domain.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(contextId = "merchantClient", name="merchant", configuration = {
        FeignConfiguration.class,
        JacksonEncoder.class,
        JacksonDecoder.class,
        MerchantErrorDecoder.class}, url="${merchant.base.url}")
public interface MerchantGateway {

    @RequestMapping(method = RequestMethod.POST, value = "merchant/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    VerifyAuthenticationDTO authentication(@RequestBody Login login);

    @RequestMapping(method = RequestMethod.POST, value = "/client", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ClientDTO getBy(@RequestBody Transaction id);
}
