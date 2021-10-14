package merchant.service.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorWrapper {

    private int code;
    private String error;
}
