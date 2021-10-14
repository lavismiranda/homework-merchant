package merchant.service.infrastructure.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import merchant.service.infrastructure.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @SneakyThrows
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Boolean isLogin = requestTemplate.url().contains("login");
        String token = getBearerTokenHeader();
        if(!isLogin){
            if(token == null){
                throw new ValidationException(40405,"token not found");
            }
            requestTemplate.header(AUTHORIZATION_HEADER, token);
        }
    }
}
