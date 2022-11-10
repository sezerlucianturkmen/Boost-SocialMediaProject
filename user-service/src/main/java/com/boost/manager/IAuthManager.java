package com.boost.manager;

import com.boost.dto.response.RoleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${myapplication.feign.auth}/auth", name = "auth-service", decode404 = true)
public interface IAuthManager {


    @GetMapping("/findbyrole/{roles}")
    public ResponseEntity<List<RoleResponseDto>> findAllByRole(@PathVariable String roles);


    @GetMapping("/findbyid/{id}")
    public ResponseEntity<RoleResponseDto> findRoleByAuthId(@PathVariable Long id);
}