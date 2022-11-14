package com.boost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailServiceApplication {
    //    final
//    EmailSenderService service;
//
//    public MailServiceApplication(EmailSenderService service) {
//        this.service = service;
//    }

    public static void main(String[] args) {

        SpringApplication.run(MailServiceApplication.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public  void SendMail(){
//        ActivateReguestDto dto=ActivateReguestDto.builder().email("musty1406@gmail.com").activatedCode("xx-yyy").build();
//        service.sendActivateCode(dto);
//    }

}