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


        return ResponseEntity.ok("Auth service Suanda hizmet vermiyor");
    }


    @GetMapping("/userservice")
    public ResponseEntity<String> userServiceFallback() {


        return ResponseEntity.ok("User service Suanda hizmet vermiyor");
    }

    @GetMapping("/postservice")
    public ResponseEntity<String> postServiceFallback() {


        return ResponseEntity.ok("Post service Suanda hizmet vermiyor");
    }

    @GetMapping("/elasticservice")
    public ResponseEntity<String> elasticServiceFallback() {


        return ResponseEntity.ok("Elastic service Suanda hizmet vermiyor");
    }

    @GetMapping("/mailservice")
    public ResponseEntity<String> mailServiceFallback() {


        return ResponseEntity.ok("Mail service Suanda hizmet vermiyor");
    }


}