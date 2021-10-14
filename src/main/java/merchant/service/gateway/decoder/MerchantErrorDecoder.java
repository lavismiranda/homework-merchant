package merchant.service.gateway.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import merchant.service.infrastructure.exceptions.FallibleException;
import merchant.service.infrastructure.exceptions.MerchantException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Date;

public class MerchantErrorDecoder implements ErrorDecoder {

  private ObjectMapper objectMapper;

  @Override
  public Exception decode(String s, Response response)
  {
    Request request = response.request();

    try {
      System.out.println(IOUtils.toString(response.body().asInputStream()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (response.status() == HttpStatus.NOT_FOUND.value()) {
      return new MerchantException(("Status 404 - returned not found"));
    }

    if (response.status() == HttpStatus.UNAUTHORIZED.value()){
      return new MerchantException(401,"Status 401 - UNAUTHORIZED");
    }

    if (response.status() == HttpStatus.BAD_REQUEST.value()){
      try{
        return new MerchantException((String.format("Status 400 - %s", IOUtils.toString(response.body().asInputStream()))));
      } catch (IOException e) {
        return new MerchantException(("Error reading responseBody status 400"));
      }
    }


    Exception exception = new Default().decode(s, response);

    if (response.status() >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      return new FallibleException(response.status(), exception.getMessage(), request.httpMethod(), exception, new Date(), request);
    }

    return exception;
  }

}
