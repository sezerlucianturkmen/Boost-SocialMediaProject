package com.boost.controller;

import com.boost.dto.request.CreatePostDto;
import com.boost.dto.response.GetAllPost;
import com.boost.exception.ErrorType;
import com.boost.exception.PostManagerException;
import com.boost.repository.entity.Post;
import com.boost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import  static com.boost.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(POST)
public class PostController {

    private final PostService postService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createPost(@RequestBody @Valid CreatePostDto dto){
        try{
            postService.create(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw new PostManagerException(ErrorType.POST_NOT_CREATED);
        }
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<Post>> findAll(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/getmypost/{token}")
    public ResponseEntity<List<GetAllPost>> getMyPost(@PathVariable String token) {


        return ResponseEntity.ok(postService.getMyPost(token));
    }


}
