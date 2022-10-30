package com.boost.manager;

import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.NewUserCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.boost.constants.ApiUrls.*;

@FeignClient(url = "${myapplication.feign.user}/user",name = "user-service-userprofile",decode404 = true)
public interface IUserManager {

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewUserCreateDto dto);
    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto);

    @PostMapping(ACTIVATESTATUSBYID)
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid);

}