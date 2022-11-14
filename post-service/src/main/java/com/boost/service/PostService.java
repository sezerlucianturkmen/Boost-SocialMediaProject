package com.boost.service;

import com.boost.dto.request.CreatePostDto;
import com.boost.dto.request.DeletePostDto;
import com.boost.dto.request.PostUpdateDto;
import com.boost.dto.response.GetAllPost;
import com.boost.dto.response.GetOtherUserPost;
import com.boost.dto.response.UserProfilePostResponseDto;
import com.boost.exception.ErrorType;
import com.boost.exception.PostManagerException;
import com.boost.manager.IUserManager;
import com.boost.mapper.IPostMapper;
import com.boost.repository.IPostRepository;
import com.boost.repository.entity.Post;
import com.boost.utility.JwtTokenManager;
import com.boost.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PostService extends ServiceManager<Post, String> {


    private final IPostRepository postRepository;

    private final JwtTokenManager jwtTokenManager;

    private final IUserManager userManager;

    private final CommentService commentService;


    public PostService(IPostRepository postRepository, JwtTokenManager jwtTokenManager, IUserManager userManager, CommentService commentService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userManager = userManager;
        this.commentService = commentService;
    }

    public Post create(CreatePostDto dto) {

        return save(IPostMapper.INSTANCE.toPost(dto));

    }

    public List<GetAllPost> getMyPost(String token) {
        Optional<Long> authId = jwtTokenManager.getUserId(token);
        if (authId.isPresent()) {
            UserProfilePostResponseDto dto = userManager.findbyAuthId(authId.get()).getBody();
            List<Post> posts = postRepository.findAllByUserId(dto.getId());
            return getComment(posts);
        } else {

            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public List<GetAllPost> getOtherUserPost(GetOtherUserPost getOtherUserPost) {
        Optional<Long> authId = jwtTokenManager.getUserId(getOtherUserPost.getToken());
        if (authId.isPresent()) {
            List<Post> posts = postRepository.findAllByUsername(getOtherUserPost.getUsername());
            return getComment(posts);
        } else {
            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public List<GetAllPost> getComment(List<Post> posts) {
        List<GetAllPost> getAllPosts = new ArrayList<>();
        posts.stream().forEach(x -> {
            GetAllPost getAllPost = IPostMapper.INSTANCE.toGetAllPost(x);
            getAllPost.setCommentList(commentService.findAllByPostId(x.getId()).get());
            getAllPosts.add(getAllPost);
        });
        return getAllPosts;
    }

    public Boolean deletePost(DeletePostDto deletePostDto) {
        Optional<Long> authid = jwtTokenManager.getUserId(deletePostDto.getToken());
        if (authid.isPresent()) {
            UserProfilePostResponseDto dto = userManager.findbyAuthId(authid.get()).getBody();
            Optional<Post> post = postRepository.findByIdAndUserId(deletePostDto.getPostId(), dto.getId());
            if (post.isPresent()) {

                delete(post.get());
                return true;
            } else {
                throw new PostManagerException(ErrorType.POST_NOT_FOUND);
            }

        } else {
            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }
    }

    public Boolean updatePost(PostUpdateDto postUpdateDto) {
        Optional<Long> authId = jwtTokenManager.getUserId(postUpdateDto.getToken());

        if (authId.isPresent()) {
            UserProfilePostResponseDto dto = userManager.findbyAuthId(authId.get()).getBody();
            Optional<Post> post = postRepository.findByIdAndUserId(postUpdateDto.getId(), dto.getId());
            if (post.isPresent()) {
                post.get().setPostMediaUrl(postUpdateDto.getPostMediaUrl());
                post.get().setContent(postUpdateDto.getContent());
                post.get().setTitle(postUpdateDto.getTitle());
                save(post.get());
                return true;
            } else {

                throw new PostManagerException(ErrorType.POST_NOT_FOUND);
            }


        } else {

            throw new PostManagerException(ErrorType.INVALID_TOKEN);
        }

    }

}
