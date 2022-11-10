package com.boost.controller;

import com.boost.dto.request.DeleteFollowDto;
import com.boost.dto.request.FollowCreateDto;
import com.boost.dto.response.FollowListResponseDto;
import com.boost.repository.entity.UserProfile;
import com.boost.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.boost.constants.ApiUrls.*;
@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createFollow(@RequestBody FollowCreateDto dto) {
        followService.create(dto);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping(UNFOLLOW)
    public ResponseEntity<Boolean> unfollow(@RequestBody DeleteFollowDto dto){
        return ResponseEntity.ok(followService.deleteFollow(dto));
    }

    @GetMapping("/findfollows/{token}")
    public ResponseEntity<List<UserProfile>> findFollowsById(@PathVariable String token) {

        return ResponseEntity.ok(followService.findFollowById(token));
    }


}