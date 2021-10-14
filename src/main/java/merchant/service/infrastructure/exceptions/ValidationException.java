package merchant.service.infrastructure.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends Exception{

    private int code;
    public ValidationException() {}

    public ValidationException(int code, String message)
    {
        super(message);
        this.code = code;
    }
}
