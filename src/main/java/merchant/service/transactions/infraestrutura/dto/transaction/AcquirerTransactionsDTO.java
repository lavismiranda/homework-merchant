package merchant.service.transactions.infraestrutura.dto.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcquirerTransactionsDTO {

    private String nameOfAcquirer;
    private String code;
}
