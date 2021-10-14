package merchant.service.transactions.infraestrutura.dto.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO implements Serializable {

    private FxDTO fx;
    private CustomerInfoDTO customerInfo;
    private Merchant merchant;
    private AcquirerTransactionsDTO acquirerTransactions;
    private MerchantTransactionsDTO merchantTransactions;

}
