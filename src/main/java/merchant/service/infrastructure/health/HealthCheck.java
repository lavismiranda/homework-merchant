package merchant.service.infrastructure.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping(value = "/health")
    public ResponseEntity check(){
        return ResponseEntity.ok("up");
    }
}
