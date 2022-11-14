package com.boost.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/fallback")
@RestController
public class FallBackController {


    @GetMapping("/authservice")
    public ResponseEntity<String> authServiceFallback() {


        return ResponseEntity.ok("Auth service suanda hizmet vermiyor");
    }


    @GetMapping("/userservice")
    public ResponseEntity<String> userServiceFallback() {


        return ResponseEntity.ok("User service suanda hizmet vermiyor");
    }


}
