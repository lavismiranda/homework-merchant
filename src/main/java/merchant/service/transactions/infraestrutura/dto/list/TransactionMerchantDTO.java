package merchant.service.transactions.infraestrutura.dto.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMerchantDTO implements Serializable {

    private String referenceNo;
    private String status;
    private String operation;
    private String message;
    private String created_at;
    private String transactionId;
}
