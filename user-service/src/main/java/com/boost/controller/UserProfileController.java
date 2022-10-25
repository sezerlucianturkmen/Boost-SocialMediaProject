package com.boost.controller;

import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.NewUserCreateDto;
import com.boost.dto.request.UpdateRequestDto;
import com.boost.exception.ErrorType;
import com.boost.exception.UserManagerException;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.boost.constants.ApiUrls.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;


    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewUserCreateDto dto){

        try {
            userProfileService.createUser(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw  new UserManagerException(ErrorType.USER_NOT_CREATED);
        }
    }
    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto){
        return ResponseEntity.ok(userProfileService.activateStatus(dto));
    }

    @PostMapping("/activate/{authid}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid){

        return ResponseEntity.ok(userProfileService.activateStatus(authid));

    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UpdateRequestDto dto){

        return ResponseEntity.ok(userProfileService.updateUser(dto)) ;
    }

    @GetMapping(("/findall"))
    public ResponseEntity<List<UserProfile>> getList (){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}
