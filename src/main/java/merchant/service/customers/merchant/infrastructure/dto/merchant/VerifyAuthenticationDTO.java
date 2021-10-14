package merchant.service.customers.merchant.infrastructure.dto.merchant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties
@NoArgsConstructor
public class VerifyAuthenticationDTO implements Serializable {

    String token;
    String status;

}
