package com.boost.controller;
import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.LoginRequestDto;
import com.boost.dto.request.RegisterRequestDto;

import com.boost.dto.response.LoginResponseDto;
import com.boost.dto.response.RegisterResponseDto;
import com.boost.dto.response.RoleResponseDto;
import com.boost.repository.entity.Auth;
import com.boost.repository.enums.Roles;
import com.boost.service.AuthService;
import com.boost.utility.JwtTokenManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.boost.constants.ApiUrls.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private  final JwtTokenManager jwtTokenManager;

    @PostMapping(REGISTER)
    @Operation(summary = "Kullanıcı kayıt eden metot")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return  ResponseEntity.ok(authService.login(dto).get());
    }

    @GetMapping(GETTOKEN)
    public String getToken(Long id){
        return jwtTokenManager.createToken(id);
    }

    @GetMapping(GETIDBYTOKEN)
    public Long getId(String token){
        return jwtTokenManager.getUserId(token).get();
    }

    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody  ActivateRequestDto dto){
        return   ResponseEntity.ok(authService.activeteStatus(dto));
    }

    @GetMapping(GETALLAUTH)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }

    @GetMapping("/redis")
    @Cacheable(value = "redis_example1")
    public String redisExample(String value){
        return value;
    }

    @GetMapping("/findbyrole/{roles}")
    public ResponseEntity<List<RoleResponseDto>> findAllByRole(@PathVariable String roles){
        return ResponseEntity.ok(authService.findByRole(roles));
    }

}
