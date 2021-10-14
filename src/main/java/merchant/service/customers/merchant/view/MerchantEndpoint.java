package merchant.service.customers.merchant.view;

import merchant.service.customers.merchant.application.impl.MerchantService;
import merchant.service.customers.merchant.domain.model.Login;
import merchant.service.customers.merchant.infrastructure.dto.client.ClientDTO;
import merchant.service.customers.merchant.infrastructure.dto.merchant.VerifyAuthenticationDTO;
import merchant.service.infrastructure.exceptions.ValidationException;
import merchant.service.transactions.domain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantEndpoint {

    @Autowired
    private MerchantService merchantService;

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VerifyAuthenticationDTO> auth(@RequestBody Login keys) throws ValidationException {
        return ResponseEntity.ok(this.merchantService.authenticate(keys));
    }

    @PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> client(@RequestBody Transaction transaction) throws ValidationException {
        return ResponseEntity.ok(this.merchantService.getClient(transaction));
    }
}
