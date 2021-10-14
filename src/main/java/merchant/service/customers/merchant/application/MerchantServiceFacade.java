package merchant.service.customers.merchant.application;

import merchant.service.customers.merchant.domain.model.Login;
import merchant.service.customers.merchant.infrastructure.dto.client.ClientDTO;
import merchant.service.customers.merchant.infrastructure.dto.merchant.VerifyAuthenticationDTO;
import merchant.service.infrastructure.exceptions.ValidationException;
import merchant.service.transactions.domain.model.Transaction;

public interface MerchantServiceFacade {
     VerifyAuthenticationDTO authenticate(Login login) throws ValidationException;
     ClientDTO getClient(Transaction transaction) throws ValidationException;
}
