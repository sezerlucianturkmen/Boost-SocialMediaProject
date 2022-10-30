package com.boost.controller;



import com.boost.dto.response.UserProfileResponseDto;
import com.boost.mapper.IUserMapper;
import com.boost.repository.entity.UserProfile;
import com.boost.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import static com.boost.constants.ApiUrls.*;


@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
public class UserProfileController {


    private  final UserProfileService userProfileService;

    @GetMapping(GETALL)
    public Iterable<UserProfile> findAll(){
        return  userProfileService.findAll();
    }

    @PostMapping(CREATE)
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDto dto){

        return ResponseEntity.ok(userProfileService.
                save(IUserMapper.INSTANCE.toUserProfile(dto)));

    }

    @PutMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody  UserProfileResponseDto dto){

        return ResponseEntity.ok(userProfileService.
                save(IUserMapper.INSTANCE.toUserProfile(dto)));

    }


}