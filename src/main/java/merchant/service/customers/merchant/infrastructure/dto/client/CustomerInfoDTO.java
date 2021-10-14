package merchant.service.customers.merchant.infrastructure.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO implements Serializable {

    private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;


}
