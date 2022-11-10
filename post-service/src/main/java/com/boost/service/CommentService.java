package com.boost.service;

import com.boost.dto.request.CreateCommentDto;
import com.boost.mapper.ICommentMapper;
import com.boost.mapper.IPostMapper;
import com.boost.repository.ICommentRepository;
import com.boost.repository.entity.Comment;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment,String> {

    private final ICommentRepository commentRepository;


    public CommentService(ICommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }

    public Comment create(CreateCommentDto dto) {
        return save(ICommentMapper.INSTANCE.toComment(dto));
    }


    public Optional<List<Comment>> findAllByPostId(String id) {
        return commentRepository.findAllOptionalByPostId(id);
    }

}
