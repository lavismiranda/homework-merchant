package merchant.service.customers.merchant.application.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import merchant.service.customers.merchant.application.MerchantServiceFacade;
import merchant.service.customers.merchant.domain.model.Login;
import merchant.service.customers.merchant.infrastructure.MerchantGateway;
import merchant.service.customers.merchant.infrastructure.dto.client.ClientDTO;
import merchant.service.customers.merchant.infrastructure.dto.merchant.VerifyAuthenticationDTO;
import merchant.service.infrastructure.exceptions.ValidationException;
import merchant.service.transactions.domain.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MerchantService implements MerchantServiceFacade {

    @Autowired
    private MerchantGateway merchantGateway;

    @SneakyThrows
    @Override
    public VerifyAuthenticationDTO authenticate(Login login) throws ValidationException {
        VerifyAuthenticationDTO verify = this.merchantGateway.authentication(login);
        if(!verify.getStatus().equals("APPROVED")){
            throw new ValidationException(40401,"Merchant User credentials is not valid");
        }
        return verify;
    }

    @Override
    public ClientDTO getClient(Transaction transaction) throws ValidationException {
        ClientDTO client = this.merchantGateway.getBy(transaction);
        if(client == null){
            throw new ValidationException(40404,"client not found");
        }
        return client;
    }

}
