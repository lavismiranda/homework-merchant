package merchant.service.infrastructure.exceptions;

import feign.Request;
import feign.RetryableException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class FallibleException extends RetryableException
{
    private HttpStatus httpStatus;

    public FallibleException(int status, String message, Request.HttpMethod httpMethod, Throwable cause,
                             Date retryAfter, Request request)
    {
        super(status, message, httpMethod, cause, retryAfter, request);
    }

    public FallibleException(int status, String message, Request.HttpMethod httpMethod, Date retryAfter,
                             Request request)
    {
        super(status, message, httpMethod, retryAfter, request);
    }
}
