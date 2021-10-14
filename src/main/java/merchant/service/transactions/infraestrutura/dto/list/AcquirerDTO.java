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
public class AcquirerDTO implements Serializable {

    private int id;
    private String name;
    private String code;
    private String type;
}
