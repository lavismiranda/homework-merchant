package merchant.service.infrastructure.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MerchantException extends RuntimeException
{
    private HttpStatus httpStatus;
    private int code;
    public MerchantException(){}

    public MerchantException(HttpStatus httpStatus, String msg){
        super(msg);
        this.httpStatus = httpStatus;
    }

    public MerchantException(int code,String msg){
        super(msg);
        this.code = code;
    }

    public MerchantException(String msg){
        super(msg);
    }

    public MerchantException(Throwable t){
        super(t);
    }

    public MerchantException(String msg, Throwable t){
        super(msg, t);
    }
}
