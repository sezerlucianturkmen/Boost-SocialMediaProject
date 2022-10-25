package com.boost.manager;

import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.NewUserCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.boost.constants.ApiUrls.*;

@FeignClient(url = "http://localhost:8092/api/v1/user",name = "user-service-userprofile",decode404 = true)
public interface IUserManager {

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewUserCreateDto dto);

    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto);

    @PostMapping("/activate/{authid}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid);
}