package merchant.service.transactions.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @NotBlank(message = "transaction id is required")
    private String transactionId;
}
