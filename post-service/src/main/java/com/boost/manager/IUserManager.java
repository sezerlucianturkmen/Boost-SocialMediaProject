package com.boost.manager;

import com.boost.dto.response.UserProfilePostResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${myapplication.feign.user}/user", name = "user-service-userprofile", decode404 = true)
public interface IUserManager {

    @GetMapping("/findbyautid/{id}")
    public ResponseEntity<UserProfilePostResponseDto> findbyAuthId(@PathVariable Long id);
}