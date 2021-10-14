package merchant.service.transactions.infraestrutura;

import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import merchant.service.gateway.decoder.MerchantErrorDecoder;
import merchant.service.infrastructure.config.FeignConfiguration;
import merchant.service.transactions.domain.model.Filter;
import merchant.service.transactions.domain.model.Report;
import merchant.service.transactions.domain.model.Transaction;
import merchant.service.transactions.infraestrutura.dto.list.ListDTO;
import merchant.service.transactions.infraestrutura.dto.report.ReportDTO;
import merchant.service.transactions.infraestrutura.dto.transaction.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(contextId = "transactionClient", name="transaction", configuration ={
        FeignConfiguration.class,
        JacksonEncoder.class,
        JacksonDecoder.class,
        MerchantErrorDecoder.class}, url="${merchant.base.url}")
public interface TransactionGateway {

    @RequestMapping(method = RequestMethod.POST, value = "/transactions/report",  produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ReportDTO report(@RequestBody Report report);

    @RequestMapping(method = RequestMethod.POST, value = "/transaction/list", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ListDTO list(@RequestBody Filter filter);

    @RequestMapping(method = RequestMethod.POST, value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TransactionDTO getBy(@RequestBody Transaction id);
}
