package com.boost.manager;
import com.boost.dto.response.UserProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.boost.constants.ApiUrls.*;

@FeignClient(url = "${myapplication.feign.user}/user",name = "user-service-userprofile",decode404 = true)
public interface IUserManager {


    @GetMapping(GETALL)
    public ResponseEntity<List<UserProfileResponseDto>> findAll();
}