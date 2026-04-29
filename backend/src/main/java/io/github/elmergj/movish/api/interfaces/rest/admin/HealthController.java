package io.github.elmergj.movish.api.interfaces.rest.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {

    @GetMapping
    public ResponseEntity<String> healthStatus(){
        log.info("Incoming request for /health received");
        return ResponseEntity.ok("Hi!, All good from Movish Server");
    }
}
