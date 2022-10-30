package com.boost.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDto {
    @NotBlank
    @Size(min = 3, max = 20 ,message = "Kullanýcý adý en az 3 karakter en fazla 20 karakter olabilir")

    private String username;
    @NotBlank
    @Size(min = 8, max = 32 ,message = "Þifre adý en az 8 karakter en fazla 32 karakter olabilir")
    private String password;
    @Email(message = "E mail formata uygun deðil")
    @NotBlank
    private String email;

    private  String adminCode;


}
