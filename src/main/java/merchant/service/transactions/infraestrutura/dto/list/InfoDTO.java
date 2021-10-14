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
public class InfoDTO implements Serializable{

    private FxDTO fx;
    private CustomerInfoDTO customerInfo;
    private Merchant merchant;
    private IpnDTO ipn;
    private TransactionDTO transaction;
    private AcquirerDTO acquirerDTO;
    private Boolean refundable;
}
