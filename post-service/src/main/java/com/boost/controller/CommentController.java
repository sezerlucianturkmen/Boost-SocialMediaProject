package com.boost.controller;

import com.boost.dto.request.CreateCommentDto;
import com.boost.dto.request.CreatePostDto;
import com.boost.exception.ErrorType;
import com.boost.exception.PostManagerException;
import com.boost.repository.entity.Comment;
import com.boost.repository.entity.Post;
import com.boost.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.boost.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
public class CommentController {
    private final CommentService commentService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createPost(@RequestBody @Valid CreateCommentDto dto){
        try{
            commentService.create(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw new PostManagerException(ErrorType.COMMENT_NOT_CREATED);
        }
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Comment>> findAll(){
        return ResponseEntity.ok(commentService.findAll());
    }


}
