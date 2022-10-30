package com.boost.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CodeGenerator {

    public static String generateCode(String value){
        String[] data=value.split("-");
        StringBuilder newCode=new StringBuilder();

        for(String s:data){
            newCode.append(s.charAt(0));
        }

        return newCode.toString();
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
