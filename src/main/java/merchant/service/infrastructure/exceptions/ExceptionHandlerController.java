package merchant.service.infrastructure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

  @ExceptionHandler(MerchantException.class)
  public ResponseEntity handleException(final MerchantException exception) {
    logError(exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorWrapper(exception.getCode(),exception.getMessage()));
  }

  @ExceptionHandler(FallibleException.class)
  public ResponseEntity handleException(final FallibleException exception) {
    logError(exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorWrapper(500,exception.getMessage()));
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity handleException(final ValidationException exception) {
    logError(exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
            .body(new ErrorWrapper(exception.getCode(),exception.getMessage()));
  }

  private void logError(String e) {
    log.error(e);
  }

}
